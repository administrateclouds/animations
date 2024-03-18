package cloud.polars.animations.api.parser;

import cloud.polars.animations.api.bone.Bone;
import cloud.polars.animations.api.rig.type.Animation;
import cloud.polars.animations.api.rig.type.Skeleton;

import java.util.List;

public interface Parser {
    Animation parse();

    Skeleton createRig();

    List<Bone> getBones();
}
