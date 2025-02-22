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
    public static final double AURORAL_SCALE = 15.0;
    public static final int AURORAL_TURQUOISE = 0x12e6ad;
    public static final int AURORAL_BLUE = 0x45bbff;
    public static final int AURORAL_PURPLE = 0xbb39f7;
    public static final int AURORAL_MAGENTA = 0xf25eeb;
    public static final int AURORAL_MID = ColorUtil.lerp(0.5f, AURORAL_TURQUOISE, AURORAL_BLUE, AURORAL_PURPLE, AURORAL_MAGENTA);
    public static final Random RANDOM = new Xoroshiro128PlusPlusRandom(0L);
    public static final PerlinNoiseSampler PERLIN_NOISE = new PerlinNoiseSampler(RANDOM);

    public static int getAuroralFoliageColor(@Nullable BlockRenderView world, @Nullable BlockPos pos) {
        if(pos == null || world == null) {
            return AURORAL_MID;
        }
        //TODO: is a cache needed here?
        var v = (float) PERLIN_NOISE.sample(pos.getX() / AURORAL_SCALE, pos.getY() / AURORAL_SCALE, pos.getZ() / AURORAL_SCALE);
        float i = MathHelper.clamp((v + 1) / 2, 0.0f, 1.0f);
        return ColorUtil.lerp(i, AURORAL_TURQUOISE, AURORAL_BLUE, AURORAL_PURPLE, AURORAL_MAGENTA);
    }
}
