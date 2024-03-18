package cloud.polars.animations.api.rig.type;

import cloud.polars.animations.api.bone.Bone;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4d;

import java.util.Collection;
import java.util.function.Predicate;

public interface Skeleton {
    @NotNull
    Bone getRootBone();

    @NotNull
    Matrix4d getPosition();

    double getRange();

    void setRange(double range);

    @Nullable
    Predicate<Player> getVisibilityPredicate();

    void setVisibilityPredicate(@Nullable Predicate<Player> predicate);

    void setViewers(@Nullable Collection<Player> viewers);

    void spawn();

    void remove();
}
