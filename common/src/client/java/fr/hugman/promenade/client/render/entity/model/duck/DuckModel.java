package fr.hugman.promenade.client.render.entity.model.duck;

import fr.hugman.promenade.entity.Duck;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.util.Mth;

abstract public class DuckModel extends HierarchicalModel<Duck> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public DuckModel(ModelPart root) {
        this.root = root;
        ModelPart body = root.getChild(PartNames.BODY);
        this.head = body.getChild(PartNames.HEAD);
        this.rightLeg = body.getChild(PartNames.RIGHT_LEG);
        this.leftLeg = body.getChild(PartNames.LEFT_LEG);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    /**
     * @param wingFlap how far the wings are open, computed by the renderer (as vanilla does for chickens)
     */
    @Override
    public void setupAnim(Duck duck, float limbAngle, float limbDistance, float wingFlap, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        // Head
        this.head.xRot = headPitch * (float) (Math.PI / 180.0);
        this.head.yRot = headYaw * (float) (Math.PI / 180.0);

        // Legs
        this.rightLeg.xRot = Mth.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leftLeg.xRot = Mth.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
    }
}
