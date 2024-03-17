package cloud.polars.animations.api.animation;

import java.util.List;

public class AnimationFrame {
    private final int tick;
    private final List<AnimationFrameEntry> nodes;

    public AnimationFrame(
            int tick,
            List<AnimationFrameEntry> nodes
    ) {
        this.tick = tick;
        this.nodes = nodes;
    }

    public int getTick() {
        return tick;
    }

    public List<AnimationFrameEntry> getNodes() {
        return nodes;
    }
}
