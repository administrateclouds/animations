package cloud.polars.animations.api.bone;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public interface BoneFactory<T extends Bone> {
    @NotNull T createBone(@NotNull NamespacedKey key);
}
