package cloud.polars.animations.paper.animation;

import cloud.polars.animations.api.animation.AnimationFrame;
import cloud.polars.animations.api.animation.AnimationFrameEntry;
import cloud.polars.animations.api.animation.LoopMode;
import cloud.polars.animations.paper.Animations;
import cloud.polars.animations.paper.animation.rig.RigInstance;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class AnimationHandler extends BukkitRunnable {

    private final RigInstance instance;

    private final Map<String, AnimationImpl> animationMap;

    private boolean paused = false;
    private int currentFrame = 0;
    private AnimationImpl activeAnimation;

    public AnimationHandler(
            RigInstance instance
    ) {
        this.instance = instance;
        this.animationMap = instance.getRig().getAnimations();
        this.runTaskTimer(Animations.getInstance(), 0L, 1L);
    }

    public void play(String id) {
        AnimationImpl animation = this.animationMap.get(id);
        if (animation == null) {
            throw new RuntimeException("There is no animation on the given rig with an ID of " + id);
        }

        this.activeAnimation = animation;
        this.currentFrame = -animation.getStartDelay();
        this.paused = false;
    }

    public void stop() {
        this.activeAnimation = null;

        for (AnimationFrameEntry node : instance.getRig().getDefaultPose()) {
            instance.applyPose(node);
        }
    }

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

    @Override
    public void run() {
        if (activeAnimation != null && !paused) {
            LoopMode loopMode = activeAnimation.getLoopMode();

            if (currentFrame == 0) {
                int duration = activeAnimation.getDuration();

                if (loopMode == LoopMode.LOOP) {
                    duration += activeAnimation.getLoopDelay();
                }

                AnimationFrame frame = activeAnimation.getFrame(currentFrame);

                if (frame != null) {
                    for (AnimationFrameEntry node : frame.getNodes()) {
                        if (activeAnimation.isAffected(node.uuid())) {
                            instance.applyPose(node);
                        }
                    }
                }

                if (currentFrame >= duration) {
                    finishAnimation();
                }
            }

            currentFrame++;
        }
    }

    private void finishAnimation() {
        switch (this.activeAnimation.getLoopMode()) {
            case HOLD -> activeAnimation = null;
            case LOOP -> currentFrame = 0;
            case ONCE -> {
                activeAnimation = null;
                for (AnimationFrameEntry node : instance.getRig().getDefaultPose()) {
                    instance.applyPose(node);
                }
            }
        }
    }
}
