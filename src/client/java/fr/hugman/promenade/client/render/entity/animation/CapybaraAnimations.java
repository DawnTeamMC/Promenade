package fr.hugman.promenade.client.render.entity.animation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.AnimationDefinition;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class CapybaraAnimations {
    public static final AnimationDefinition EAR_WIGGLE = AnimationDefinition.Builder.create(0.2f)
            .addBoneAnimation(EntityModelPartNames.RIGHT_EAR, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.1f, AnimationHelper.createRotationalVector(-13.5f, -15f, 13f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.2f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_EAR, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.1f, AnimationHelper.createRotationalVector(-13.5f, 15f, -13f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.2f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .build();

    public static final AnimationDefinition WALKING = AnimationDefinition.Builder.create(1.5f).looping()
            .addBoneAnimation(EntityModelPartNames.BODY, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 2.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -2.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(0f, 0f, 2.5f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(-27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(-27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(-27.5f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(-27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(-27.5f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(-27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC))).build();

    public static final AnimationDefinition FALL_TO_SLEEP = AnimationDefinition.Builder.create(1.9167667f)
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5834333f, AnimationHelper.createTranslationalVector(-12f, -5f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(0f, 0f, -10f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5834333f, AnimationHelper.createRotationalVector(0f, 0f, -90f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.375f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.4583433f, AnimationHelper.createScalingVector(0.8f, 1.1f, 1.1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5834333f, AnimationHelper.createScalingVector(1.2f, 0.9f, 0.9f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.6766667f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.3433333f, AnimationHelper.createRotationalVector(0f, 0f, -60f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.625f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.7916767f, AnimationHelper.createRotationalVector(-7.387326251354352f, -1.2987564674722307f, 7.583846147258555f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.9167667f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.8343334f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.9583434f, AnimationHelper.createScalingVector(1f, 0.8f, 1f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0834333f, AnimationHelper.createScalingVector(1f, 0.8f, 1f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.1676667f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(0f, 0f, 40f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.1676667f, AnimationHelper.createRotationalVector(-0.8329438344855409f, -2.0100084957616673f, -31.292852606551207f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.625f, AnimationHelper.createRotationalVector(-20f, 0f, 5f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.8343334f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.9583434f, AnimationHelper.createScalingVector(1f, 0.8f, 1f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0834333f, AnimationHelper.createScalingVector(1f, 0.8f, 1f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.1676667f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.3433333f, AnimationHelper.createRotationalVector(0f, 0f, -77.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.625f, AnimationHelper.createRotationalVector(22.480717026093316f, 0.9564494572955482f, 20.19008655601783f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.7916767f, AnimationHelper.createRotationalVector(22.018164129528486f, 4.751129561951075f, 10.924817551732758f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.9167667f, AnimationHelper.createRotationalVector(22.480717026093316f, 0.9564494572955482f, 20.19008655601783f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(0f, 0f, 42.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.1676667f, AnimationHelper.createRotationalVector(5.989807308760465f, 9.302389729048627f, -28.590097057579058f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.625f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.7083433f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(4.990462591650612f, 4.99522900802458f, -20.218651201461853f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, 27.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.7083433f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC))).build();

    public static final AnimationDefinition SLEEP = AnimationDefinition.Builder.create(4.5f).looping()
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(-12f, -5f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -90f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0.75f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.25f, AnimationHelper.createTranslationalVector(0.75f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.25f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.5f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(-20f, 0f, 5f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.25f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createRotationalVector(22.30257854223737f, -3.0529741491914137f, 29.898054778260757f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.25f, AnimationHelper.createRotationalVector(22.30257854223737f, -3.0529741491914137f, 29.898054778260757f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createRotationalVector(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.5f, AnimationHelper.createRotationalVector(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.5416767f, AnimationHelper.createRotationalVector(-5f, 0f, -5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.5416765f, AnimationHelper.createRotationalVector(-5f, 0f, -5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.7083435f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.5f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.BODY, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.25f, AnimationHelper.createTranslationalVector(0.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.BODY, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createScalingVector(1.1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.25f, AnimationHelper.createScalingVector(1.1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.5f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC))).build();

    public static final AnimationDefinition FART = AnimationDefinition.Builder.create(3.8343335f)
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.08343333f, AnimationHelper.createTranslationalVector(0f, 5f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.08343333f, AnimationHelper.createScalingVector(1f, 1.2f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0416765f, AnimationHelper.createTranslationalVector(0.63f, 1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.375f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createRotationalVector(-7.5f, 0f, -17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(-7.5f, 0f, -17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.1676665f, AnimationHelper.createRotationalVector(0.2727354654598457f, 0.4131058282196136f, -6.089277436131233f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.375f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.7083435f, AnimationHelper.createTranslationalVector(-0.12f, 1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0416765f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.375f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.7916765f, AnimationHelper.createRotationalVector(-5.111516362223829f, -0.446597721821945f, 6.992121938251076f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0416765f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.2083435f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.5f, AnimationHelper.createTranslationalVector(0.63f, 1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.8343335f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createRotationalVector(0f, 0f, -17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(0f, 0f, -17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.2083435f, AnimationHelper.createRotationalVector(0f, 0f, -17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.625f, AnimationHelper.createRotationalVector(0.2727354654598457f, 0.4131058282196136f, -6.089277436131233f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9583435f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.8343335f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.LINEAR),
                    new Keyframe(3.1676665f, AnimationHelper.createTranslationalVector(-0.12f, 1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createRotationalVector(0f, 0f, 17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(0f, 0f, 17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.8343335f, AnimationHelper.createRotationalVector(0f, 0f, 17.5f), Transformation.Interpolations.LINEAR),
                    new Keyframe(3.25f, AnimationHelper.createRotationalVector(-5.111516362223829f, -0.446597721821945f, 6.992121938251076f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.BODY, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createRotationalVector(10f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(10f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.1676665f, AnimationHelper.createRotationalVector(2.2307925039942713f, -0.21576122102964312f, -7.927957140726775f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.4167665f, AnimationHelper.createRotationalVector(4.294867444937954f, 2.2606154253199566f, -6.0592613319625706f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.625f, AnimationHelper.createRotationalVector(1.7645231481382142f, 1.1309400733304573f, 0.14523321293696423f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.875f, AnimationHelper.createRotationalVector(6.178001369910271f, -0.19388043339675276f, 5.467675351455965f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createTranslationalVector(0f, -1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createTranslationalVector(0f, -1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9167665f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.16766666f, AnimationHelper.createRotationalVector(2.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(2.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0834335f, AnimationHelper.createRotationalVector(49.89121214627585f, -3.3756177280956763f, 3.6906601938271706f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.375f, AnimationHelper.createRotationalVector(62.26769532549997f, 10.515127085314361f, 31.44641266227063f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.7083435f, AnimationHelper.createRotationalVector(62.54663949450434f, -8.432200276274816f, -49.78158893940414f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3f, AnimationHelper.createRotationalVector(58.19080366360111f, 20.578159182988493f, 18.790040178069503f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.2083435f, AnimationHelper.createRotationalVector(34.363668641116874f, 11.307647518170633f, 12.797722379234328f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5f, AnimationHelper.createRotationalVector(-7.379261962134369f, 2.46597558997084f, 2.922121806087871f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.7916765f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC))).build();

    public static final AnimationDefinition WAKE_UP = AnimationDefinition.Builder.create(5.791677f)
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(-12f, -5f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9583435f, AnimationHelper.createTranslationalVector(-12f, -5f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.125f, AnimationHelper.createTranslationalVector(-12f, -5f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.7083435f, AnimationHelper.createTranslationalVector(-14f, -5f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.375f, AnimationHelper.createTranslationalVector(-9f, -3f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.291677f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -90f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9583435f, AnimationHelper.createRotationalVector(0f, 0f, -90f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.125f, AnimationHelper.createRotationalVector(0f, 0f, -92.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.7083435f, AnimationHelper.createRotationalVector(0f, 0f, -117.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.375f, AnimationHelper.createRotationalVector(0f, 0f, -70f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5f, AnimationHelper.createRotationalVector(0f, 0f, 7.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.291677f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0.75f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createTranslationalVector(0.75f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5f, AnimationHelper.createTranslationalVector(1f, 0.25f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.125f, AnimationHelper.createTranslationalVector(0.75f, 1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.291677f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(-7.5f, 0f, 17.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createRotationalVector(-7.366127295935403f, -1.4184144280073452f, 6.660634475402492f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.9583433f, AnimationHelper.createRotationalVector(-7.408694655995223f, 1.1699920891777946f, 26.424248239231403f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0416765f, AnimationHelper.createRotationalVector(-7.49745918025943f, 0.19576730469809256f, 18.98717307987583f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.2083435f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.125f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.7083435f, AnimationHelper.createRotationalVector(-6.041112393472904f, 4.452922991257692f, 53.76491217464347f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.375f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5f, AnimationHelper.createRotationalVector(0.24117231125816033f, -0.09448572501914895f, -16.85553579291023f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.291677f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(3.125f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5416765f, AnimationHelper.createTranslationalVector(-1f, -0.75f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.125f, AnimationHelper.createTranslationalVector(-0.78f, 0.42000000000000004f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.291677f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(-20f, 0f, 5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9583435f, AnimationHelper.createRotationalVector(-20f, 0f, 5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.125f, AnimationHelper.createRotationalVector(-20f, 0f, 5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5416765f, AnimationHelper.createRotationalVector(-12.279770389181849f, 12.007062843212225f, 47.98109178875055f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.291677f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(3.5416765f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.9583435f, AnimationHelper.createScalingVector(1f, 0.6f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.583433f, AnimationHelper.createScalingVector(1f, 0.6f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.75f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createTranslationalVector(1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.834333f, AnimationHelper.createTranslationalVector(1f, 0.25f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.958343f, AnimationHelper.createTranslationalVector(0.75f, 1f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.125f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createRotationalVector(22.30257854223737f, -3.0529741491914137f, 29.898054778260757f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(20.091305344004923f, 1.1699920891774127f, 26.424248239231474f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createRotationalVector(-4.866127295935403f, -1.4184144280073452f, 6.660634475402492f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.9583433f, AnimationHelper.createRotationalVector(-7.408694655995223f, 1.1699920891777946f, 26.424248239231403f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.0416765f, AnimationHelper.createRotationalVector(-7.49745918025943f, 0.19576730469809256f, 18.98717307987583f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.2083435f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9583435f, AnimationHelper.createRotationalVector(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5416765f, AnimationHelper.createRotationalVector(-6.041112393472904f, 4.452922991257692f, 53.76491217464347f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.208343f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.834333f, AnimationHelper.createRotationalVector(0.24117231125816033f, -0.09448572501914895f, -16.85553579291023f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.125f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9583435f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.2083435f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.625f, AnimationHelper.createTranslationalVector(-1f, -0.75f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.208343f, AnimationHelper.createTranslationalVector(-0.78f, 0.42000000000000004f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.375f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createRotationalVector(7.14232996078681f, 4.599015978825719f, -8.889268973502567f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.9583433f, AnimationHelper.createRotationalVector(-2.475429931501239f, 4.3960867006031545f, -3.8970580610586154f), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.9583435f, AnimationHelper.createRotationalVector(-2.475429931501239f, 4.3960867006031545f, -3.8970580610586154f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.2083435f, AnimationHelper.createRotationalVector(-2.475429931501239f, 4.3960867006031545f, -3.8970580610586154f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.625f, AnimationHelper.createRotationalVector(-12.279770389181849f, 12.007062843212225f, 47.98109178875055f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.375f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(3.625f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.041677f, AnimationHelper.createScalingVector(1f, 0.6f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.583433f, AnimationHelper.createScalingVector(1f, 0.6f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.75f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.BODY, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createTranslationalVector(0.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.125f, AnimationHelper.createTranslationalVector(0.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.5416765f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.BODY, new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createScalingVector(1.1f, 1f, 1f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583434f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.7083433f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createTranslationalVector(1.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.2916765f, AnimationHelper.createTranslationalVector(1.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.625f, AnimationHelper.createTranslationalVector(0.25f, 0f, 0f), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 20f), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583434f, AnimationHelper.createRotationalVector(0f, 0f, 20f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.7083433f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.875f, AnimationHelper.createRotationalVector(0f, 0f, 57.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0834335f, AnimationHelper.createRotationalVector(0f, 0f, 57.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.2916765f, AnimationHelper.createRotationalVector(17.5f, 0f, 57.5f), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.625f, AnimationHelper.createRotationalVector(16.14973182841186f, 6.279671924441573f, 33.25231220461228f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4f, AnimationHelper.createRotationalVector(11.23738518791506f, 12.240295486395354f, 3.4386635769687928f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.25f, AnimationHelper.createRotationalVector(7.106591348469683f, 14.290604655089956f, -14.422213143878762f), Transformation.Interpolations.CUBIC),
                    new Keyframe(4.676667f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5f, AnimationHelper.createRotationalVector(0f, 0f, 15f), Transformation.Interpolations.CUBIC),
                    new Keyframe(5.291677f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC))).build();
}
