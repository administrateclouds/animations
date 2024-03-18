package cloud.polars.animations.api.parser;

import cloud.polars.animations.api.bone.Bone;
import cloud.polars.animations.api.rig.type.Animation;
import cloud.polars.animations.api.rig.type.Skeleton;

import java.io.File;
import java.util.List;

public class GenericParser implements Parser {

    private ParserType parserType;

    public GenericParser(ParserType parserType) {
        this.parserType = parserType;
    }

    @Override
    public Animation parse(File file) {
        switch (parserType) {
            case ANIMATED_JAVA -> {
                return new AJRigParser().parse(file);
            }
            default -> {
                return new BlenderRigParser().parse(file);
            }
        }
    }

    @Override
    public Skeleton createRig() {
        return null;
    }

    @Override
    public List<Bone> getBones() {
        return null;
    }
}
