package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.Promenade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DuckEntityRenderState extends LivingEntityRenderState {
    private static final Identifier DEFAULT_TEXTURE = Promenade.id("textures/entity/duck/pekin.png");

    public float flapProgress;
    public float maxWingDeviation;

    public Identifier texture = DEFAULT_TEXTURE;
}
