package fr.hugman.promenade.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

/**
 * A leaf that falls from leaves. Mirrors {@code FallingLeavesParticle} from newer versions of Minecraft; 1.21.1 only
 * has one for cherry leaves.
 */
public class PromenadeLeavesParticle extends TextureSheetParticle {
    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final int INITIAL_LIFETIME = 300;
    private static final int CURVE_ENDPOINT_TIME = 300;
    private float rotSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
    private final float spinAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
    private final float windBig;
    private final boolean swirl;
    private final boolean flowAway;
    private final double xaFlowScale;
    private final double zaFlowScale;
    private final double swirlPeriod;

    protected PromenadeLeavesParticle(
            ClientLevel level,
            double x,
            double y,
            double z,
            TextureAtlasSprite sprite,
            float fallAcceleration,
            float sideAcceleration,
            boolean swirl,
            boolean flowAway,
            float scale,
            float startVelocity
    ) {
        super(level, x, y, z);
        this.setSprite(sprite);
        this.windBig = sideAcceleration;
        this.swirl = swirl;
        this.flowAway = flowAway;
        this.lifetime = INITIAL_LIFETIME;
        this.gravity = fallAcceleration * 1.2F * ACCELERATION_SCALE;
        float size = scale * (this.random.nextBoolean() ? 0.05F : 0.075F);
        this.quadSize = size;
        this.setSize(size, size);
        this.friction = 1.0F;
        this.yd = -startVelocity;
        float particleRandom = this.random.nextFloat();
        this.xaFlowScale = Math.cos(Math.toRadians(particleRandom * 60.0F)) * this.windBig;
        this.zaFlowScale = Math.sin(Math.toRadians(particleRandom * 60.0F)) * this.windBig;
        this.swirlPeriod = Math.toRadians(1000.0F + particleRandom * 3000.0F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float aliveTicks = CURVE_ENDPOINT_TIME - this.lifetime;
            float relativeAge = Math.min(aliveTicks / CURVE_ENDPOINT_TIME, 1.0F);
            double xa = 0.0;
            double za = 0.0;
            if (this.flowAway) {
                xa += this.xaFlowScale * Math.pow(relativeAge, 1.25);
                za += this.zaFlowScale * Math.pow(relativeAge, 1.25);
            }

            if (this.swirl) {
                xa += relativeAge * Math.cos(relativeAge * this.swirlPeriod) * this.windBig;
                za += relativeAge * Math.sin(relativeAge * this.swirlPeriod) * this.windBig;
            }

            this.xd += xa * ACCELERATION_SCALE;
            this.zd += za * ACCELERATION_SCALE;
            this.yd = this.yd - this.gravity;
            this.rotSpeed = this.rotSpeed + this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll = this.roll + this.rotSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || this.lifetime < INITIAL_LIFETIME - 1 && (this.xd == 0.0 || this.zd == 0.0)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd = this.xd * this.friction;
                this.yd = this.yd * this.friction;
                this.zd = this.zd * this.friction;
            }
        }
    }

    /**
     * Same motion as vanilla's cherry leaves.
     */
    public static class BlossomProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public BlossomProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new PromenadeLeavesParticle(level, x, y, z, this.sprites.get(level.random), 0.25F, 2.0F, false, true, 1.0F, 0.0F);
        }
    }

    public static class MapleLeavesProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public MapleLeavesProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new PromenadeLeavesParticle(level, x, y, z, this.sprites.get(level.random), 0.07F, 5.0F, true, false, 1.5F, 0.021F);
        }
    }
}
