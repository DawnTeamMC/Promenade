package fr.hugman.promenade.client.color.world;


import fr.hugman.promenade.client.color.ColorUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.Xoroshiro128PlusPlusRandom;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

@Environment(value= EnvType.CLIENT)
public class GradientColors {
    public static final double SCALE = 10.0;
    public static final int DUSK_TURQUOISE = 0x12e6ad;
    public static final int DUSK_BLUE = 0x45bbff;
    public static final int DUSK_PURPLE = 0x702eff;
    public static final int DUSK_MAGENTA = 0xf284fa;
    public static final int DUSK_MID = ColorUtil.lerp(0.5f, DUSK_TURQUOISE, DUSK_BLUE, DUSK_PURPLE, DUSK_MAGENTA);
    public static final Random RANDOM = new Xoroshiro128PlusPlusRandom(0L);
    public static final PerlinNoiseSampler PERLIN_NOISE = new PerlinNoiseSampler(RANDOM);

    public static int getDuskFoliageColor(@Nullable BlockRenderView world, @Nullable BlockPos pos) {
        if(pos == null || world == null) {
            return DUSK_MID;
        }
        //TODO: is a cache needed here?
        var v = (float) PERLIN_NOISE.sample(pos.getX() / SCALE, pos.getY() / SCALE, pos.getZ() / SCALE, 0.0, 0.0);
        float i = MathHelper.clamp((v + 1) / 2, 0.0f, 1.0f);
        return ColorUtil.lerp(i, DUSK_TURQUOISE, DUSK_BLUE, DUSK_PURPLE, DUSK_MAGENTA);
    }
}
