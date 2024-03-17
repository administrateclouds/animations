package cloud.polars.animations.api.event.bone;

import cloud.polars.animations.api.bone.Bone;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerBoneInteractEvent extends PlayerEvent {
    @NotNull
    private Bone bone;
    private boolean attack;

    public PlayerBoneInteractEvent(@NotNull Player who, @NotNull Bone bone, boolean attack) {
        super(who);
        this.bone = bone;
        this.attack = attack;
    }

    @NotNull
    public Bone getBone() {
        return bone;
    }

    public boolean isAttack() {
        return attack;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
