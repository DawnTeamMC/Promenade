package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherrackBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(NetherrackBlock.class)
public abstract class NetherrackBlockMixin {
    @Inject(method = "grow", at = @At("HEAD"), cancellable = true)
    public void promenade$grow(ServerWorld world, Random random, BlockPos pos, BlockState state, CallbackInfo ci) {
        boolean crimson = false;
        boolean warped = false;
        boolean darkAmaranth = false;

        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.WARPED_NYLIUM)) {
                warped = true;
            }

            if (blockState.isOf(Blocks.CRIMSON_NYLIUM)) {
                crimson = true;
            }

            if (blockState.isOf(PromenadeBlocks.DARK_AMARANTH_NYLIUM)) {
                darkAmaranth = true;
            }

            if (warped && crimson && darkAmaranth) {
                break;
            }
        }

        if (warped || crimson || darkAmaranth) {
            BlockState[] options = {
                    warped ? Blocks.WARPED_NYLIUM.getDefaultState() : null,
                    crimson ? Blocks.CRIMSON_NYLIUM.getDefaultState() : null,
                    darkAmaranth ? PromenadeBlocks.DARK_AMARANTH_NYLIUM.getDefaultState() : null
            };

            List<BlockState> available = Arrays.stream(options).filter(Objects::nonNull).toList();
            world.setBlockState(pos, available.get(random.nextInt(available.size())), Block.NOTIFY_ALL);
        }

        ci.cancel();
    }
}