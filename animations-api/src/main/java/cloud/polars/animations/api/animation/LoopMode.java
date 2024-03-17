package cloud.polars.animations.api.animation;

public enum LoopMode {
    /**
     * The animation will play once
     */
    ONCE,

    /**
     * The animation will play once but will stay on the last frame.
     */
    HOLD,

    /**
     * The animation will loop until instructed not to.
     */
    LOOP;

    public static LoopMode fromJson(String value) {
        return valueOf(value.toUpperCase());
    }

}
