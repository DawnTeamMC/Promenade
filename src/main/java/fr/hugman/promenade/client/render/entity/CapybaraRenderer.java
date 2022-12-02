package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.CapybaraModel;
import fr.hugman.promenade.entity.CapybaraEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CapybaraRenderer extends MobEntityRenderer<CapybaraEntity, CapybaraModel<CapybaraEntity>> {
	private static final String REGULAR = "regular";
	private static final String BABY = "baby";

	public CapybaraRenderer(EntityRendererFactory.Context context) {
		super(context, new CapybaraModel<>(context.getPart(PromenadeEntityModelLayers.CAPYBARA)), 0.5f);
	}

	@Override
	public Identifier getTexture(CapybaraEntity entity) {
		String type = entity.isBaby() ? BABY : REGULAR;
		return Promenade.id("textures/entity/capybara/" + type + ".png");
	}
}
