package cloud.polars.animations.api.bone;

import org.jetbrains.annotations.NotNull;

public interface BoneType<T extends Bone> {
    @NotNull String getName();

    @NotNull Class<T> getType();

    @NotNull BoneFactory<T> getBoneFactory();
}
