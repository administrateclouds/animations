package cloud.polars.animations.paper.animation.node;

import java.util.UUID;

public abstract class Node {

    private final String name;
    private final UUID uuid;

    public Node(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

}