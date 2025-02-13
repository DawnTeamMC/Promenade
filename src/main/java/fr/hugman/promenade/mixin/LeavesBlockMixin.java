package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.SnowyLeavesMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {
    @Inject(method = "hasRandomTicks", at = @At("HEAD"), cancellable = true)
    private void promenade$hasRandomTicks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        var snowy = SnowyLeavesMap.get(state.getBlock());
        if(snowy != null) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void promenade$randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        var snowy = SnowyLeavesMap.get(state.getBlock());
        if(snowy != null) {
            var stateUp = world.getBlockState(pos.up());
            if(stateUp.isOf(Blocks.SNOW)) {
                world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
                world.setBlockState(pos, snowy.getDefaultState());
                ci.cancel();
            }
        }
    }
}
