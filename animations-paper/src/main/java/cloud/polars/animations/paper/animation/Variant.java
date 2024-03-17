package cloud.polars.animations.paper.animation;

import java.util.Map;
import java.util.UUID;

public record Variant(
    String name,
    UUID uuid,
    Map<UUID, Integer> models
) { }