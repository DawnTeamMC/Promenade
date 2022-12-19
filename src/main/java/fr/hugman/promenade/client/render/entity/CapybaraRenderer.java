package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.CapybaraModel;
import fr.hugman.promenade.entity.CapybaraEntity;
import fr.hugman.promenade.registry.PromenadeRegistries;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraRenderer<E extends CapybaraEntity> extends MobEntityRenderer<E, CapybaraModel<E>> {
	public CapybaraRenderer(EntityRendererFactory.Context context) {
		super(context, new CapybaraModel<>(context.getPart(PromenadeEntityModelLayers.CAPYBARA)), 0.5f);
		this.addFeature(new CapybaraEyesRenderer<>(this));
	}

	@Override
	public Identifier getTexture(CapybaraEntity entity) {
		Identifier variantId = PromenadeRegistries.CAPYBARA_VARIANT.getId(entity.getVariant());
		if(variantId == null) {
			throw new IllegalStateException("Capybara variant is not registered: " + this);
		}
		return Identifier.of(variantId.getNamespace(), "textures/entity/capybara/" + variantId.getPath() + ".png");
	}
}
