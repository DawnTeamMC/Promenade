package fr.hugman.promenade.client.render.entity.animation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.geom.PartNames;

public class CapybaraAnimations {
    public static final AnimationDefinition EAR_WIGGLE = AnimationDefinition.Builder.withLength(0.2f)
            .addAnimation(PartNames.RIGHT_EAR, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1f, KeyframeAnimations.degreeVec(-13.5f, -15f, 13f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_EAR, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1f, KeyframeAnimations.degreeVec(-13.5f, 15f, -13f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .build();

    public static final AnimationDefinition WALKING = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation(PartNames.BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();

    public static final AnimationDefinition FALL_TO_SLEEP = AnimationDefinition.Builder.withLength(1.9167667f)
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5834333f, KeyframeAnimations.posVec(-12f, -5f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(0f, 0f, -10f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(0f, 0f, -90f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.375f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.4583433f, KeyframeAnimations.scaleVec(0.8f, 1.1f, 1.1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5834333f, KeyframeAnimations.scaleVec(1.2f, 0.9f, 0.9f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6766667f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(0f, 0f, -60f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.625f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7916767f, KeyframeAnimations.degreeVec(-7.387326251354352f, -1.2987564674722307f, 7.583846147258555f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.9167667f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.8343334f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9583434f, KeyframeAnimations.scaleVec(1f, 0.8f, 1f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0834333f, KeyframeAnimations.scaleVec(1f, 0.8f, 1f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1676667f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(0f, 0f, 40f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1676667f, KeyframeAnimations.degreeVec(-0.8329438344855409f, -2.0100084957616673f, -31.292852606551207f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.625f, KeyframeAnimations.degreeVec(-20f, 0f, 5f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.8343334f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9583434f, KeyframeAnimations.scaleVec(1f, 0.8f, 1f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0834333f, KeyframeAnimations.scaleVec(1f, 0.8f, 1f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1676667f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(0f, 0f, -77.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.625f, KeyframeAnimations.degreeVec(22.480717026093316f, 0.9564494572955482f, 20.19008655601783f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7916767f, KeyframeAnimations.degreeVec(22.018164129528486f, 4.751129561951075f, 10.924817551732758f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.9167667f, KeyframeAnimations.degreeVec(22.480717026093316f, 0.9564494572955482f, 20.19008655601783f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(0f, 0f, 42.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1676667f, KeyframeAnimations.degreeVec(5.989807308760465f, 9.302389729048627f, -28.590097057579058f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.625f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7083433f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(4.990462591650612f, 4.99522900802458f, -20.218651201461853f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 27.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7083433f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM))).build();

    public static final AnimationDefinition SLEEP = AnimationDefinition.Builder.withLength(4.5f).looping()
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(-12f, -5f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -90f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.posVec(0.75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25f, KeyframeAnimations.posVec(0.75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.5f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-20f, 0f, 5f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.degreeVec(22.30257854223737f, -3.0529741491914137f, 29.898054778260757f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25f, KeyframeAnimations.degreeVec(22.30257854223737f, -3.0529741491914137f, 29.898054778260757f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.degreeVec(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.5f, KeyframeAnimations.degreeVec(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5416767f, KeyframeAnimations.degreeVec(-5f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5416765f, KeyframeAnimations.degreeVec(-5f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.7083435f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.5f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.BODY, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.posVec(0.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25f, KeyframeAnimations.posVec(0.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.BODY, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.scaleVec(1.1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25f, KeyframeAnimations.scaleVec(1.1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM))).build();

    public static final AnimationDefinition FART = AnimationDefinition.Builder.withLength(3.8343335f)
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.08343333f, KeyframeAnimations.posVec(0f, 5f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.08343333f, KeyframeAnimations.scaleVec(1f, 1.2f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0416765f, KeyframeAnimations.posVec(0.63f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.375f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(-7.5f, 0f, -17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(-7.5f, 0f, -17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.1676665f, KeyframeAnimations.degreeVec(0.2727354654598457f, 0.4131058282196136f, -6.089277436131233f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.375f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.7083435f, KeyframeAnimations.posVec(-0.12f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0416765f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.375f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.7916765f, KeyframeAnimations.degreeVec(-5.111516362223829f, -0.446597721821945f, 6.992121938251076f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0416765f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.2083435f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.5f, KeyframeAnimations.posVec(0.63f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.8343335f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(0f, 0f, -17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(0f, 0f, -17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.2083435f, KeyframeAnimations.degreeVec(0f, 0f, -17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.625f, KeyframeAnimations.degreeVec(0.2727354654598457f, 0.4131058282196136f, -6.089277436131233f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9583435f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.8343335f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.1676665f, KeyframeAnimations.posVec(-0.12f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(0f, 0f, 17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(0f, 0f, 17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.8343335f, KeyframeAnimations.degreeVec(0f, 0f, 17.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.25f, KeyframeAnimations.degreeVec(-5.111516362223829f, -0.446597721821945f, 6.992121938251076f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(10f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(10f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1676665f, KeyframeAnimations.degreeVec(2.2307925039942713f, -0.21576122102964312f, -7.927957140726775f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.4167665f, KeyframeAnimations.degreeVec(4.294867444937954f, 2.2606154253199566f, -6.0592613319625706f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.625f, KeyframeAnimations.degreeVec(1.7645231481382142f, 1.1309400733304573f, 0.14523321293696423f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.875f, KeyframeAnimations.degreeVec(6.178001369910271f, -0.19388043339675276f, 5.467675351455965f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.posVec(0f, -1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75f, KeyframeAnimations.posVec(0f, -1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9167665f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0834335f, KeyframeAnimations.degreeVec(49.89121214627585f, -3.3756177280956763f, 3.6906601938271706f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.375f, KeyframeAnimations.degreeVec(62.26769532549997f, 10.515127085314361f, 31.44641266227063f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7083435f, KeyframeAnimations.degreeVec(62.54663949450434f, -8.432200276274816f, -49.78158893940414f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3f, KeyframeAnimations.degreeVec(58.19080366360111f, 20.578159182988493f, 18.790040178069503f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2083435f, KeyframeAnimations.degreeVec(34.363668641116874f, 11.307647518170633f, 12.797722379234328f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5f, KeyframeAnimations.degreeVec(-7.379261962134369f, 2.46597558997084f, 2.922121806087871f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.7916765f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();

    public static final AnimationDefinition WAKE_UP = AnimationDefinition.Builder.withLength(5.791677f)
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(-12f, -5f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9583435f, KeyframeAnimations.posVec(-12f, -5f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.125f, KeyframeAnimations.posVec(-12f, -5f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.7083435f, KeyframeAnimations.posVec(-14f, -5f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.375f, KeyframeAnimations.posVec(-9f, -3f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.75f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.291677f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.ROOT, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -90f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9583435f, KeyframeAnimations.degreeVec(0f, 0f, -90f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.125f, KeyframeAnimations.degreeVec(0f, 0f, -92.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.7083435f, KeyframeAnimations.degreeVec(0f, 0f, -117.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.375f, KeyframeAnimations.degreeVec(0f, 0f, -70f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5f, KeyframeAnimations.degreeVec(0f, 0f, 7.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.posVec(0.75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.posVec(0.75f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5f, KeyframeAnimations.posVec(1f, 0.25f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.125f, KeyframeAnimations.posVec(0.75f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.291677f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-7.5f, 0f, 17.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891774127f, 26.424248239231474f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.degreeVec(-7.366127295935403f, -1.4184144280073452f, 6.660634475402492f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9583433f, KeyframeAnimations.degreeVec(-7.408694655995223f, 1.1699920891777946f, 26.424248239231403f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0416765f, KeyframeAnimations.degreeVec(-7.49745918025943f, 0.19576730469809256f, 18.98717307987583f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2083435f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.125f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.7083435f, KeyframeAnimations.degreeVec(-6.041112393472904f, 4.452922991257692f, 53.76491217464347f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.375f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5f, KeyframeAnimations.degreeVec(0.24117231125816033f, -0.09448572501914895f, -16.85553579291023f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(3.125f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5416765f, KeyframeAnimations.posVec(-1f, -0.75f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.125f, KeyframeAnimations.posVec(-0.78f, 0.42000000000000004f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.291677f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-20f, 0f, 5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9583435f, KeyframeAnimations.degreeVec(-20f, 0f, 5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.125f, KeyframeAnimations.degreeVec(-20f, 0f, 5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5416765f, KeyframeAnimations.degreeVec(-12.279770389181849f, 12.007062843212225f, 47.98109178875055f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_FRONT_LEG, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(3.5416765f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.9583435f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.583433f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.75f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.posVec(1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.834333f, KeyframeAnimations.posVec(1f, 0.25f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.958343f, KeyframeAnimations.posVec(0.75f, 1f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.125f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.RIGHT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(22.48071702609377f, 0.9564494572955482f, 20.190086556017377f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.degreeVec(22.30257854223737f, -3.0529741491914137f, 29.898054778260757f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(20.091305344004923f, 1.1699920891774127f, 26.424248239231474f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.degreeVec(-4.866127295935403f, -1.4184144280073452f, 6.660634475402492f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9583433f, KeyframeAnimations.degreeVec(-7.408694655995223f, 1.1699920891777946f, 26.424248239231403f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0416765f, KeyframeAnimations.degreeVec(-7.49745918025943f, 0.19576730469809256f, 18.98717307987583f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2083435f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9583435f, KeyframeAnimations.degreeVec(-7.4086946559950775f, 1.1699920891780493f, 26.42424823923169f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5416765f, KeyframeAnimations.degreeVec(-6.041112393472904f, 4.452922991257692f, 53.76491217464347f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.208343f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.834333f, KeyframeAnimations.degreeVec(0.24117231125816033f, -0.09448572501914895f, -16.85553579291023f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9583435f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2083435f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.625f, KeyframeAnimations.posVec(-1f, -0.75f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.208343f, KeyframeAnimations.posVec(-0.78f, 0.42000000000000004f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.375f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.degreeVec(7.14232996078681f, 4.599015978825719f, -8.889268973502567f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9583433f, KeyframeAnimations.degreeVec(-2.475429931501239f, 4.3960867006031545f, -3.8970580610586154f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9583435f, KeyframeAnimations.degreeVec(-2.475429931501239f, 4.3960867006031545f, -3.8970580610586154f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2083435f, KeyframeAnimations.degreeVec(-2.475429931501239f, 4.3960867006031545f, -3.8970580610586154f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.625f, KeyframeAnimations.degreeVec(-12.279770389181849f, 12.007062843212225f, 47.98109178875055f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.375f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.LEFT_HIND_LEG, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(3.625f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.041677f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.583433f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.75f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.BODY, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.posVec(0.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.posVec(0.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.125f, KeyframeAnimations.posVec(0.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5416765f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.BODY, new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25f, KeyframeAnimations.scaleVec(1.1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.scaleVec(1f, 1f, 1f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9583434f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7083433f, KeyframeAnimations.posVec(-1f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.posVec(1.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2916765f, KeyframeAnimations.posVec(1.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.625f, KeyframeAnimations.posVec(0.25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation(PartNames.HEAD, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 20f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9583434f, KeyframeAnimations.degreeVec(0f, 0f, 20f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7083433f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.875f, KeyframeAnimations.degreeVec(0f, 0f, 57.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0834335f, KeyframeAnimations.degreeVec(0f, 0f, 57.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2916765f, KeyframeAnimations.degreeVec(17.5f, 0f, 57.5f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.625f, KeyframeAnimations.degreeVec(16.14973182841186f, 6.279671924441573f, 33.25231220461228f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4f, KeyframeAnimations.degreeVec(11.23738518791506f, 12.240295486395354f, 3.4386635769687928f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.25f, KeyframeAnimations.degreeVec(7.106591348469683f, 14.290604655089956f, -14.422213143878762f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.676667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5f, KeyframeAnimations.degreeVec(0f, 0f, 15f), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM))).build();
}
