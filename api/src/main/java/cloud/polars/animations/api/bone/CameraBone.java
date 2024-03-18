package cloud.polars.animations.api.bone;

import cloud.polars.animations.api.bone.Bone;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface CameraBone extends Bone {
    void addViewer(@NotNull Player viewer);

    void removeViewer(@NotNull Player viewer);
}
