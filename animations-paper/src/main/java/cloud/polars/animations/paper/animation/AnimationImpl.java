package cloud.polars.animations.paper.animation;

import cloud.polars.animations.api.Animation;
import cloud.polars.animations.api.animation.AnimationFrame;
import cloud.polars.animations.api.animation.LoopMode;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AnimationImpl implements Animation {
    private LoopMode loopMode;

    private final int startDelay;
    private final int loopDelay;
    private final int duration;

    private final List<UUID> affectedBones;
    private final boolean affectedBonesIsWhitelist;
    private final Map<Integer, AnimationFrame> frames;

    public AnimationImpl(
            LoopMode loopMode,
            int startDelay,
            int loopDelay,
            int duration,
            List<UUID> affectedBones,
            boolean affectedBonesIsWhitelist,
            Map<Integer, AnimationFrame> frames
    ) {
        this.loopMode = loopMode;
        this.startDelay = startDelay;
        this.loopDelay = loopDelay;
        this.duration = duration;
        this.affectedBones = affectedBones;
        this.affectedBonesIsWhitelist = affectedBonesIsWhitelist;
        this.frames = frames;
    }

    @Override
    public AnimationFrame getFrame(int frame) {
        return this.frames.get(frame);
    }

    public boolean isAffected(UUID bone) {
        if (affectedBonesIsWhitelist) {
            return affectedBones.contains(bone);
        } else {
            return !affectedBones.contains(bone);
        }
    }

    public LoopMode getLoopMode() {
        return loopMode;
    }

    public int getStartDelay() {
        return startDelay;
    }

    public int getLoopDelay() {
        return loopDelay;
    }

    public int getDuration() {
        return duration;
    }
}
