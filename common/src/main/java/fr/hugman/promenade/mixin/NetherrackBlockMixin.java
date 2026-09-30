package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.PromenadeBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherrackBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(NetherrackBlock.class)
public abstract class NetherrackBlockMixin {
    @Inject(method = "performBonemeal", at = @At("HEAD"), cancellable = true)
    public void promenade$grow(ServerLevel world, RandomSource random, BlockPos pos, BlockState state, CallbackInfo ci) {
        boolean crimson = false;
        boolean warped = false;
        boolean darkAmaranth = false;

        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.is(Blocks.WARPED_NYLIUM)) {
                warped = true;
            }

            if (blockState.is(Blocks.CRIMSON_NYLIUM)) {
                crimson = true;
            }

            if (blockState.is(PromenadeBlocks.DARK_AMARANTH_NYLIUM)) {
                darkAmaranth = true;
            }

            if (warped && crimson && darkAmaranth) {
                break;
            }
        }

        if (warped || crimson || darkAmaranth) {
            BlockState[] options = {
                    warped ? Blocks.WARPED_NYLIUM.defaultBlockState() : null,
                    crimson ? Blocks.CRIMSON_NYLIUM.defaultBlockState() : null,
                    darkAmaranth ? PromenadeBlocks.DARK_AMARANTH_NYLIUM.defaultBlockState() : null
            };

            List<BlockState> available = Arrays.stream(options).filter(Objects::nonNull).toList();
            world.setBlock(pos, available.get(random.nextInt(available.size())), Block.UPDATE_ALL);
        }

        ci.cancel();
    }
}