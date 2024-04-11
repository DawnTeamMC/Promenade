package fr.hugman.animation_api.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import fr.hugman.animation_api.render.entity.animation.CustomAnimation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.Transformation;

import javax.annotation.Nullable;

@Environment(value= EnvType.CLIENT)
public class AnimationCodecs {
    public static final Codec<Transformation.Interpolation> TRANSFORMATION_INTERPOLATION = Codec.STRING.comapFlatMap(string -> {
        Transformation.Interpolation interpolation = transformationInterpolationFromString(string);
        if (interpolation instanceof Transformation.Interpolation) {
            return DataResult.success(interpolation);
        }
        return DataResult.error(() -> "Not a compound tag: " + string);
    }, AnimationCodecs::transformationInterpolationToString);

    public static final Codec<Animation> ANIMATION = CustomAnimation.CODEC.xmap(CustomAnimation::toVanillaAnimation, CustomAnimation::fromVanillaAnimation);

    @Nullable
    private static Transformation.Interpolation transformationInterpolationFromString(String interpolation) {
        return switch (interpolation) {
            case "linear" -> Transformation.Interpolations.LINEAR;
            case "cubic" -> Transformation.Interpolations.CUBIC;
            default -> null;
        };
    }

    private static String transformationInterpolationToString(Transformation.Interpolation interpolation) {
        if(interpolation == Transformation.Interpolations.LINEAR) {
            return "linear";
        }
        if(interpolation == Transformation.Interpolations.CUBIC) {
            return "cubic";
        }
        throw new IllegalArgumentException("Unknown interpolation: null");
    }
}
