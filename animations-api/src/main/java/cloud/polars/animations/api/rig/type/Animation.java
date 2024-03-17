package cloud.polars.animations.api.rig.type;

public interface Animation {
    int getFrame();

    void setFrame(int frame);

    boolean next();

    int getDuration();
}
