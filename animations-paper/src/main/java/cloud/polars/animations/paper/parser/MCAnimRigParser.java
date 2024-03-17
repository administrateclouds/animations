package cloud.polars.animations.paper.parser;

import cloud.polars.animations.api.animation.AnimationFrame;
import cloud.polars.animations.api.animation.AnimationFrameEntry;
import cloud.polars.animations.api.animation.LoopMode;
import cloud.polars.animations.paper.animation.AnimationImpl;
import cloud.polars.animations.paper.animation.node.Bone;
import cloud.polars.animations.paper.animation.rig.Rig;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.joml.Matrix4d;
import org.joml.Matrix4f;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author 56738, Polar
 */
public class MCAnimRigParser {
    private static final byte[] HEADER = "MCANIM01".getBytes(StandardCharsets.UTF_8);
    private final DataInput input;

    private MCAnimRigParser(DataInput input) {
        this.input = input;
    }

    public static Rig parse(
            JsonObject metadata,
            String file
    ) {
        Material material = Material.matchMaterial(metadata.get("material").getAsString());
        Map<String, Integer> customModelData = new HashMap<>();

        for (
                Map.Entry<String, JsonElement> entry : metadata.get("custom_model_data").getAsJsonArray().getAsJsonObject().entrySet()
        ) {
            customModelData.put(entry.getKey(), entry.getValue().getAsInt());
        }

        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            return new MCAnimRigParser(input).readRig(file, material, customModelData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int readInt() throws IOException {
        return input.readInt();
    }

    private double readDouble() throws IOException {
        return input.readDouble();
    }

    private String readString() throws IOException {
        int length = input.readShort();
        byte[] bytes = new byte[length];
        input.readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private Matrix4d readMatrix() throws IOException {
        return new Matrix4d(
                readDouble(), readDouble(), readDouble(), readDouble(),
                readDouble(), readDouble(), readDouble(), readDouble(),
                readDouble(), readDouble(), readDouble(), readDouble(),
                readDouble(), readDouble(), readDouble(), readDouble());
    }

    private void readHeader() throws IOException {
        byte[] header = new byte[HEADER.length];
        input.readFully(header);
        if (!Arrays.equals(header, HEADER)) {
            throw new IOException("Incorrect header");
        }
    }

    private Element readElement() throws IOException {
        String name = readString();
        Map<String, Channel> channels = readChannels();
        return new Element(UUID.randomUUID(), name, channels);
    }

    private Map<String, Element> readElements() throws IOException {
        return readMap(this::readElement);
    }

    private Channel readChannel() throws IOException {
        String name = readString();
        String type = readString();
        return new Channel(name, type, new ArrayList<>());
    }

    private Rig readRig(String id, Material itemMaterial, Map<String, Integer> customModelData) throws IOException {
        readHeader();

        BlenderAnimation animation = readAnimation();

        Rig rig = new Rig(id, itemMaterial);

        for (Element element : animation.elements().values()) {
            rig.getNodeMap().put(element.id(), new Bone(
                    element.name(),
                    element.id(),
                    customModelData.getOrDefault(element.name(), 0)));
        }

        Map<Integer, List<AnimationFrameEntry>> frameEntries = new HashMap<>();
        for (int i = 0; i < animation.frameCount(); i++) {
            frameEntries.put(i, new ArrayList<>());
        }

        for (Element element : animation.elements().values()) {
            Channel channel = element.channels().get("matrix");

            // use first frame as the default pose
            rig.getDefaultPose().add(new AnimationFrameEntry(
                    element.id(),
                    new Matrix4f(channel.frames().get(0)),
                    false));

            for (int i = 0; i < channel.frames().size(); i++) {
                frameEntries.get(i).add(new AnimationFrameEntry(
                        element.id(),
                        new Matrix4f(channel.frames().get(i)),
                        true));
            }
        }

        Map<Integer, AnimationFrame> frames = new HashMap<>();
        for (Map.Entry<Integer, List<AnimationFrameEntry>> entry : frameEntries.entrySet()) {
            int tick = entry.getKey();
            frames.put(tick, new AnimationFrame(tick, entry.getValue()));
        }

        // there is only one animation
        rig.getAnimations().put("animation", new AnimationImpl(
                LoopMode.LOOP,
                0,
                0,
                animation.frameCount(),
                List.of(),
                false,
                frames));

        return rig;
    }

    private Map<String, Channel> readChannels() throws IOException {
        return readMap(this::readChannel);
    }

    private int readFrames(Iterable<Element> elements) throws IOException {
        int frameCount = readInt();
        for (int frame = 0; frame < frameCount; frame++) {
            for (Element element : elements) {
                for (Channel channel : element.channels().values()) {
                    channel.frames().add(readMatrix());
                }
            }
        }
        return frameCount;
    }

    private BlenderAnimation readAnimation() throws IOException {
        Map<String, Element> elements = readElements();
        int frameCount = readFrames(elements.values());
        return new BlenderAnimation(elements, frameCount);
    }

    private <T extends Named> Map<String, T> readMap(Reader<T> reader) throws IOException {
        int count = readInt();
        Map<String, T> result = new LinkedHashMap<>(count);
        for (int i = 0; i < count; i++) {
            T value = reader.read();
            result.put(value.name(), value);
        }
        return result;
    }

    private interface Reader<T> {
        T read() throws IOException;
    }

    private interface Named {
        String name();
    }

    private record Element(UUID id, String name, Map<String, Channel> channels) implements Named {
    }

    private record Channel(String name, String type, List<Matrix4d> frames) implements Named {
        private Channel {
            if (!type.equals("mat4")) {
                throw new IllegalArgumentException("Unsupported channel type: " + type);
            }
        }
    }

    private record BlenderAnimation(Map<String, Element> elements, int frameCount) {}
}
