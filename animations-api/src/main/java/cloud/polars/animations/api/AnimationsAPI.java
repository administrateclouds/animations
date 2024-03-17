package cloud.polars.animations.api;

import cloud.polars.animations.api.parser.GenericRigParser;
import cloud.polars.animations.api.rig.SkeletonFactory;
import cloud.polars.animations.api.rig.type.Skeleton;
import org.bukkit.World;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface AnimationsAPI {

    @NotNull
    Skeleton createSkeleton(@NotNull World world);

    @NotNull
    SkeletonFactory createSkeletonFactory(@NotNull World world);

    @NotNull GenericRigParser getParser();

    final class Provider {
        private static AnimationsAPI instance;

        public Provider() {}

        @NotNull
        public static AnimationsAPI getInstance() {
            return instance;
        }

        @ApiStatus.Internal
        public static void setInstance(AnimationsAPI instance) {
            Provider.instance = instance;
        }
    }
}
