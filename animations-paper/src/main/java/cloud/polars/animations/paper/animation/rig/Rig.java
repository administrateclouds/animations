package cloud.polars.animations.paper.animation.rig;

import cloud.polars.animations.api.animation.AnimationFrameEntry;
import cloud.polars.animations.paper.animation.AnimationImpl;
import cloud.polars.animations.paper.animation.Variant;
import cloud.polars.animations.paper.animation.node.Node;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Rig {

    private final String id;
    private final Material itemMaterial;
    private final List<AnimationFrameEntry> defaultPose = new ArrayList<>();
    private final Map<UUID, Node> nodeMap = new HashMap<>();
    private final Map<String, AnimationImpl> animations = new HashMap<>();
    private final Map<String, Variant> variantMap = new HashMap<>();

    public Rig(String id, Material itemMaterial) {
        this.id = id;
        this.itemMaterial = itemMaterial;
    }

    public String getId() {
        return id;
    }

    public Material getItemMaterial() {
        return itemMaterial;
    }

    public List<AnimationFrameEntry> getDefaultPose() {
        return defaultPose;
    }

    public Map<UUID, Node> getNodeMap() {
        return nodeMap;
    }

    public Map<String, AnimationImpl> getAnimations() {
        return animations;
    }

    public Map<String, Variant> getVariantMap() {
        return variantMap;
    }
}
