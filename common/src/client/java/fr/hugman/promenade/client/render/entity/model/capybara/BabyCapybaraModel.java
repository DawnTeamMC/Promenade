package fr.hugman.promenade.client.render.entity.model.capybara;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BabyCapybaraModel extends CapybaraModel {
	public BabyCapybaraModel(ModelPart part) {
		super(part);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.5F, 14.5F, 3.5F));

		root.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, CubeListBuilder.create().texOffs(21, 14).addBox(-1.1F, 1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 5.5F, -5.5F));
		root.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, CubeListBuilder.create().texOffs(1, 14).addBox(-0.9F, 1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 5.5F, -5.5F));
		root.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, CubeListBuilder.create().texOffs(8, 23).addBox(-1.2F, 1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 4.5F, 0.5F));
		root.addOrReplaceChild(PartNames.LEFT_HIND_LEG, CubeListBuilder.create().texOffs(0, 23).addBox(-0.9F, 1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 4.5F, 0.5F));

		root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -2.0F, -4.25F, 6.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 4.5F, -3.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition head = root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
					.texOffs(22, 0).addBox(-2.0F, 1.0F, -4.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(0, 0).addBox(-2.0F, -3.0F, -6.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
			PartPose.offset(-1.0F, 3.5F, -6.0F));
		PartDefinition jaw = head.addOrReplaceChild(PartNames.JAW, CubeListBuilder.create().texOffs(24, 5).addBox(-2.25F, 0.0F, -2.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 1.0F, -4.0F));

		head.addOrReplaceChild(PartNames.LEFT_EAR, CubeListBuilder.create().texOffs(16, 2).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -2.0F, -1.0F, -0.2182F, 0.2182F, 0.0F));
		head.addOrReplaceChild(PartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -2.0F, -1.0F, -0.2182F, -0.2182F, 0.0F));
		head.addOrReplaceChild(UPPER_TEETH, CubeListBuilder.create().texOffs(22, 2).addBox(-0.5F, -0.25F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -5.0F));
		jaw.addOrReplaceChild(LOWER_TEETH, CubeListBuilder.create().texOffs(22, 3).addBox(-0.5F, -0.75F, 2.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 0.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}