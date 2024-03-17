package cloud.polars.animations.api.bone;

import cloud.polars.animations.api.bone.Bone;
import org.bukkit.inventory.ItemStack;

public interface ItemDisplayBone extends Bone {
    ItemStack getItem();

    void setItem(ItemStack item);
}
