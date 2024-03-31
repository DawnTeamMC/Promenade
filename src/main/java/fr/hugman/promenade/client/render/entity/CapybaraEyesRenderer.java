package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.CapybaraModel;
import fr.hugman.promenade.entity.CapybaraEntity;
import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraEyesRenderer<E extends CapybaraEntity> extends FeatureRenderer<E, CapybaraModel<E>> {
	public CapybaraEyesRenderer(FeatureRendererContext<E, CapybaraModel<E>> featureRendererContext) {
		super(featureRendererContext);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, E capybara, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(eyeTexture(capybara)));
		((Model) this.getContextModel()).render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
	}

	public Identifier eyeTexture(CapybaraEntity capybara) {
		return capybara.getEyesTexture();
	}
}
