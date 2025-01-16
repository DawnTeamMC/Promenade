package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.Promenade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SunkenEntityRenderState extends SkeletonEntityRenderState {
	private static final Identifier DEFAULT_TEXTURE = Promenade.id("textures/entity/sunken/bubble.png");

	public Identifier texture = DEFAULT_TEXTURE;

}
