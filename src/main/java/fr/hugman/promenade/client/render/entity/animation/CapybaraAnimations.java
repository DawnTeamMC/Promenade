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
}
