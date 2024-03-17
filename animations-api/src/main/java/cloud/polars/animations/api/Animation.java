package cloud.polars.animations.api;

import cloud.polars.animations.api.animation.AnimationFrame;

public interface Animation {
    /**
     * Returns the current frame
     * @return a frame
     */
    AnimationFrame getFrame(int frame);

}
