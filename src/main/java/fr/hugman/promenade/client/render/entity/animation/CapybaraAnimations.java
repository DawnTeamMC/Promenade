package fr.hugman.promenade.client.render.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

public class CapybaraAnimations {
	public static final Animation EAR_WIGGLE = Animation.Builder.create(0.2f)
			.addBoneAnimation(EntityModelPartNames.RIGHT_EAR, new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
					new Keyframe(0.1f, AnimationHelper.createRotationalVector(-13.5f, -15f, 13f), Transformation.Interpolations.CUBIC),
					new Keyframe(0.2f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.LEFT_EAR, new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
					new Keyframe(0.1f, AnimationHelper.createRotationalVector(-13.5f, 15f, -13f), Transformation.Interpolations.CUBIC),
					new Keyframe(0.2f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.build();

	public static final Animation WALKING = Animation.Builder.create(1.5f).looping()
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

	public static final Animation FALL_OVER = Animation.Builder.create(1.9167667f)
			.addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.TRANSLATE,
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
			.addBoneAnimation(EntityModelPartNames.LEFT_FRONT_LEG, new Transformation(Transformation.Targets.TRANSLATE,
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
			.addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8343334f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(0f, 0f, 42.5f), Transformation.Interpolations.CUBIC),
					new Keyframe(1.1676667f, AnimationHelper.createRotationalVector(5.989807308760465f, 9.302389729048627f, -28.590097057579058f), Transformation.Interpolations.CUBIC),
					new Keyframe(1.625f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
					new Keyframe(1.7083433f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(4.990462591650612f, 4.99522900802458f, -20.218651201461853f), Transformation.Interpolations.CUBIC),
					new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, 27.5f), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
					new Keyframe(1.7083433f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC))).build();

	public static final Animation SLEEP = Animation.Builder.create(4.5f).looping()
			.addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0f, AnimationHelper.createTranslationalVector(-2f, -5f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.ROOT, new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -90f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.RIGHT_FRONT_LEG, new Transformation(Transformation.Targets.TRANSLATE,
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
			.addBoneAnimation(EntityModelPartNames.RIGHT_HIND_LEG, new Transformation(Transformation.Targets.TRANSLATE,
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
			.addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.LEFT_HIND_LEG, new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0f, AnimationHelper.createRotationalVector(27.5f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0f, AnimationHelper.createTranslationalVector(-1f, 0f, 0f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.HEAD, new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5416767f, AnimationHelper.createRotationalVector(-5f, 0f, -5f), Transformation.Interpolations.CUBIC),
					new Keyframe(2.5416765f, AnimationHelper.createRotationalVector(-5f, 0f, -5f), Transformation.Interpolations.CUBIC),
					new Keyframe(3.7083435f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC)))
			.addBoneAnimation(EntityModelPartNames.BODY, new Transformation(Transformation.Targets.TRANSLATE,
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
}
