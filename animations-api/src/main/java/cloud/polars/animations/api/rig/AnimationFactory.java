package cloud.polars.animations.api.rig;

import cloud.polars.animations.api.rig.type.Animation;
import cloud.polars.animations.api.rig.type.Skeleton;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface AnimationFactory {
    Animation create(@NotNull Iterable<@NotNull Skeleton> skeletons);

    default Animation create(@NotNull Skeleton... skeletons) {
        return create(List.of(skeletons));
    }
}
