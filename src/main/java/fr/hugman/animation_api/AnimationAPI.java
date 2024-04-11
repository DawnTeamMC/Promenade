package fr.hugman.animation_api;

import fr.hugman.animation_api.codec.AnimationCodecs;
import fr.hugman.dawn.Dawn;
import fr.hugman.dawn.registry.ReloadableResourceManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.resource.ResourceType;

@Environment(value= EnvType.CLIENT)
public class AnimationAPI {
    public static final ReloadableResourceManager<Animation> INSTANCE = ReloadableResourceManager.of(AnimationCodecs.ANIMATION, ResourceType.CLIENT_RESOURCES, "animations");

    public static void init() {
        INSTANCE.register(Dawn.id("animations"));
    }
}
