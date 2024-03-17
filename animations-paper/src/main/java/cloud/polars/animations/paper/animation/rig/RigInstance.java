package cloud.polars.animations.paper.animation.rig;

import cloud.polars.animations.api.animation.AnimationFrameEntry;
import cloud.polars.animations.paper.Animations;
import cloud.polars.animations.paper.animation.AnimationHandler;
import cloud.polars.animations.paper.animation.Variant;
import cloud.polars.animations.paper.animation.node.Bone;
import cloud.polars.animations.paper.animation.node.Node;
import org.bukkit.Location;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RigInstance {

    protected final Rig rig;
    private final String id;
    private final Map<UUID, ItemDisplay> displayEntities = new HashMap<>();
    private final AnimationHandler animationHandler;

    private Location location;

    protected RigInstance(Rig rig, Location location, String id) {
        this.rig = rig;
        this.location = location;
        this.id = id;

        boolean first = true;
        for (Map.Entry<UUID, Node> entry : rig.getNodeMap().entrySet()) {
            UUID nodeId = entry.getKey();
            Node node = entry.getValue();

            if (node instanceof Bone bone) {
                ItemDisplay display = location.getWorld().spawn(location, ItemDisplay.class, e -> {
                    e.setItemStack(this.getItem(bone.getCustomModelData()));
                    e.setPersistent(false);
                    e.setInterpolationDuration(2);
                    e.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.HEAD);
                    e.setMetadata("rig_instance", new FixedMetadataValue(Animations.getInstance(), this));
                });
                if (!first) {
                    display.setMetadata("easyarmorstands_ignore", new FixedMetadataValue(Animations.getInstance(), true));
                }
                first = false;
                displayEntities.put(nodeId, display);
            }
        }

        for (AnimationFrameEntry frameEntry : rig.getDefaultPose()) {
            this.applyPose(frameEntry);
        }

        this.animationHandler = new AnimationHandler(this);
    }

    private ItemStack getItem(int modelData) {
        ItemStack itemStack = new ItemStack(rig.getItemMaterial());
        itemStack.editMeta(meta -> meta.setCustomModelData(modelData));
        return itemStack;
    }

    public void applyVariant(Variant variant) {
        Map<UUID, Integer> modelData = variant == null ? Collections.emptyMap() : variant.models();

        for (Node value : rig.getNodeMap().values()) {
            if (value instanceof Bone bone) {
                ItemDisplay display = this.displayEntities.get(bone.getUuid());
                if (display != null) {
                    if (modelData.containsKey(bone.getUuid())) {
                        display.setItemStack(this.getItem(modelData.get(bone.getUuid())));
                    } else {
                        display.setItemStack(this.getItem(bone.getCustomModelData()));
                    }
                }
            }
        }
    }

    public void applyVariant(String id) {
        if (id.equals("default")) {
            this.applyVariant((Variant) null);
            return;
        }
        Variant variant = this.rig.getVariantMap().get(id);
        if (variant == null) {
            throw new IllegalArgumentException("The rig doesn't have a variant with the id: " + id);
        }
        this.applyVariant(variant);
    }

    public void applyPose(AnimationFrameEntry entry) {
        Node node = this.rig.getNodeMap().get(entry.uuid());
        if (node instanceof Bone) {
            ItemDisplay display = displayEntities.get(entry.uuid());
            display.setInterpolationDelay(0);
            display.setTransformationMatrix(entry.matrix());
        }
    }

    public Rig getRig() {
        return rig;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location.clone();
    }

    public void setLocation(Location location) {
        this.location = location;
        for (ItemDisplay entity : displayEntities.values()) {
            entity.teleport(location);
        }
    }

    public void remove() {
        this.displayEntities.values().forEach(ItemDisplay::remove);
        this.displayEntities.clear();
        this.animationHandler.cancel();
    }

    public boolean isValid() {
        for (ItemDisplay entity : displayEntities.values()) {
            if (!entity.isValid()) {
                return false;
            }
        }
        return true;
    }

    public AnimationHandler getAnimationHandler() {
        return animationHandler;
    }

}
