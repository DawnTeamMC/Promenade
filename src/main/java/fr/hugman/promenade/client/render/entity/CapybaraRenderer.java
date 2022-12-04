package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.CapybaraModel;
import fr.hugman.promenade.entity.CapybaraEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraRenderer extends MobEntityRenderer<CapybaraEntity, CapybaraModel<CapybaraEntity>> {
	public CapybaraRenderer(EntityRendererFactory.Context context) {
		super(context, new CapybaraModel<>(context.getPart(PromenadeEntityModelLayers.CAPYBARA)), 0.5f);
	}

	@Override
	public Identifier getTexture(CapybaraEntity entity) {
		return entity.getVariant().texture(entity.hasLargeEyes());
	}
}
