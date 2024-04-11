package fr.hugman.animation_api.render.entity.animation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.animation_api.codec.AnimationCodecs;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.util.dynamic.Codecs;
import org.joml.Vector3f;

import java.util.*;

public record CustomAnimation(float fullLength, boolean loop, Map<String, Map<String, BoneFrame>> frames) {
    public static final Codec<CustomAnimation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("length").forGetter(CustomAnimation::fullLength),
            Codec.BOOL.optionalFieldOf("loop", false).forGetter(CustomAnimation::loop),
            //TODO: have a StringIdentifiable codec for float key of the first map below
            Codec.unboundedMap(Codec.STRING, Codec.unboundedMap(Codec.STRING, BoneFrame.CODEC)).fieldOf("frames").forGetter(CustomAnimation::frames)
    ).apply(instance, CustomAnimation::new));

    public Animation toVanillaAnimation() {
        Map<String, List<Transformation>> boneAnimations = new HashMap<>();

        for (Map.Entry<String, Map<String, BoneFrame>> entry : frames.entrySet()) {
            float timestamp = Float.parseFloat(entry.getKey());
            for (Map.Entry<String, BoneFrame> boneEntry : entry.getValue().entrySet()) {
                String boneName = boneEntry.getKey();
                BoneFrame boneFrame = boneEntry.getValue();

                boneAnimations.computeIfAbsent(boneName, k -> new ArrayList<>());
                var transformations = boneAnimations.get(boneName);
                boneFrame.translate().ifPresent(translate -> {
                    var keyframe = new Keyframe(timestamp, translationToVanilla(translate), boneFrame.interpolation());
                    var transformation = transformations.stream().filter(t -> t.target() == Transformation.Targets.TRANSLATE).findFirst().orElse(new Transformation(Transformation.Targets.TRANSLATE));

                    Keyframe[] oldKeyframes = transformation.keyframes();
                    Keyframe[] newKeyframes = new Keyframe[oldKeyframes.length + 1];

                    System.arraycopy(oldKeyframes, 0, newKeyframes, 0, oldKeyframes.length);
                    newKeyframes[oldKeyframes.length] = keyframe;

                    transformations.remove(transformation);
                    transformations.add(new Transformation(Transformation.Targets.TRANSLATE, newKeyframes));
                });
                boneFrame.rotate().ifPresent(rotate -> {
                    var keyframe = new Keyframe(timestamp, rotationToVanilla(rotate), boneFrame.interpolation());
                    var transformation = transformations.stream().filter(t -> t.target() == Transformation.Targets.ROTATE).findFirst().orElse(new Transformation(Transformation.Targets.ROTATE));

                    Keyframe[] oldKeyframes = transformation.keyframes();
                    Keyframe[] newKeyframes = new Keyframe[oldKeyframes.length + 1];

                    System.arraycopy(oldKeyframes, 0, newKeyframes, 0, oldKeyframes.length);
                    newKeyframes[oldKeyframes.length] = keyframe;

                    transformations.remove(transformation);
                    transformations.add(new Transformation(Transformation.Targets.ROTATE, newKeyframes));
                });
                boneFrame.scale().ifPresent(scale -> {
                    var keyframe = new Keyframe(timestamp, scaleToVanilla(scale), boneFrame.interpolation());
                    var transformation = transformations.stream().filter(t -> t.target() == Transformation.Targets.SCALE).findFirst().orElse(new Transformation(Transformation.Targets.SCALE));

                    Keyframe[] oldKeyframes = transformation.keyframes();
                    Keyframe[] newKeyframes = new Keyframe[oldKeyframes.length + 1];

                    System.arraycopy(oldKeyframes, 0, newKeyframes, 0, oldKeyframes.length);
                    newKeyframes[oldKeyframes.length] = keyframe;

                    transformations.remove(transformation);
                    transformations.add(new Transformation(Transformation.Targets.SCALE, newKeyframes));
                });
            }
        }

        return new Animation(fullLength, loop, boneAnimations);
    }

    public static CustomAnimation fromVanillaAnimation(Animation animation) {
        Map<String, Map<String, BoneFrame>> frames = new HashMap<>();

        for (Map.Entry<String, List<Transformation>> entry : animation.boneAnimations().entrySet()) {
            String boneName = entry.getKey();
            List<Transformation> transformations = entry.getValue();

            for (Transformation transformation : transformations) {
                for (Keyframe keyframe : transformation.keyframes()) {
                    float timestamp = keyframe.timestamp();
                    BoneFrame boneFrame = frames.computeIfAbsent(Float.toString(timestamp), k -> new HashMap<>()).computeIfAbsent(boneName, k -> new BoneFrame(keyframe.interpolation(), Optional.empty(), Optional.empty(), Optional.empty()));

                    if (transformation.target() == Transformation.Targets.TRANSLATE) {

                        boneFrame = boneFrame.withTranslate(translationFromVanilla(keyframe.target()));
                    } else if (transformation.target() == Transformation.Targets.ROTATE) {
                        boneFrame = boneFrame.withRotate(rotationFromVanilla(keyframe.target()));
                    } else if (transformation.target() == Transformation.Targets.SCALE) {
                        boneFrame = boneFrame.withScale(scaleFromVanilla(keyframe.target()));
                    }

                    frames.get(timestamp).put(boneName, boneFrame);
                }
            }
        }

        return new CustomAnimation(animation.lengthInSeconds(), animation.looping(), frames);
    }

    public static Vector3f translationToVanilla(Vector3f vector) {
        return AnimationHelper.createTranslationalVector(vector.x(), vector.y(), vector.z());
    }

    public static Vector3f rotationToVanilla(Vector3f vector) {
        return AnimationHelper.createRotationalVector(vector.x(), vector.y(), vector.z());
    }

    public static Vector3f scaleToVanilla(Vector3f vector) {
        return AnimationHelper.createScalingVector(vector.x(), vector.y(), vector.z());
    }

    public static Vector3f translationFromVanilla(Vector3f vector) {
        return new Vector3f(vector.x(), -vector.y(), vector.z());
    }

    public static Vector3f rotationFromVanilla(Vector3f vector) {
        return new Vector3f(vector.x() / ((float) Math.PI / 180), vector.y() / ((float) Math.PI / 180), vector.z() / ((float) Math.PI / 180));
    }

    public static Vector3f scaleFromVanilla(Vector3f vector) {
        return new Vector3f(vector.x() + 1.0f, vector.y() + 1.0f, vector.z() + 1.0f);
    }

    record BoneFrame(Transformation.Interpolation interpolation, Optional<Vector3f> translate,
                     Optional<Vector3f> rotate, Optional<Vector3f> scale) {
        public static final Codec<BoneFrame> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                AnimationCodecs.TRANSFORMATION_INTERPOLATION.optionalFieldOf("interpolation", Transformation.Interpolations.CUBIC).forGetter(BoneFrame::interpolation),
                Codecs.VECTOR_3F.optionalFieldOf("translate").forGetter(BoneFrame::translate),
                Codecs.VECTOR_3F.optionalFieldOf("rotate").forGetter(BoneFrame::rotate),
                Codecs.VECTOR_3F.optionalFieldOf("scale").forGetter(BoneFrame::scale)
        ).apply(instance, BoneFrame::new));

        public BoneFrame withTranslate(Vector3f translate) {
            return new BoneFrame(interpolation, Optional.of(translate), rotate, scale);
        }

        public BoneFrame withRotate(Vector3f rotate) {
            return new BoneFrame(interpolation, translate, Optional.of(rotate), scale);
        }

        public BoneFrame withScale(Vector3f scale) {
            return new BoneFrame(interpolation, translate, rotate, Optional.of(scale));
        }
    }
}
