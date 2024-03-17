package cloud.polars.animations.api.bone;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4d;
import org.joml.Matrix4dc;

public interface Bone {
    @NotNull
    Matrix4d getTransform();

    @NotNull
    Matrix4d getPosition();

    void add(@NotNull Bone bone);

    void remove(@NotNull Bone bone);

    void update(@NotNull Matrix4dc position);

    @Nullable
    Bone findBone(@NotNull NamespacedKey key);

    void spawn(@NotNull World world);

    void remove();
}
