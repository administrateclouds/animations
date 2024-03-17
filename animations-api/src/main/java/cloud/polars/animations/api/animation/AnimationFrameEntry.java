package cloud.polars.animations.api.animation;

import org.joml.Matrix4f;

import java.util.UUID;

public record AnimationFrameEntry(
    UUID uuid,
    Matrix4f matrix,
    boolean interpolation
) { }