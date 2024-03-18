package cloud.polars.animations.api.rig;

import cloud.polars.animations.api.rig.type.Skeleton;
import org.jetbrains.annotations.NotNull;

public interface SkeletonFactory {
    @NotNull Skeleton createSkeleton();
}
