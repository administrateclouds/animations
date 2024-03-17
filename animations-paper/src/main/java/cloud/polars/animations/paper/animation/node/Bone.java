package cloud.polars.animations.paper.animation.node;

import java.util.UUID;

public class Bone extends Node {

    private final int customModelData;

    public Bone(String name, UUID uuid, int customModelData) {
        super(name, uuid);
        this.customModelData = customModelData;
    }

    public int getCustomModelData() {
        return customModelData;
    }

}