package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.SnowyBlocksMap;
import fr.hugman.promenade.block.SnowyLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(SnowBlock.class)
public class SnowBlockMixin {
    @Shadow @Final public static IntProperty LAYERS;

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void promenade$randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        var downPos = findNextBottomSnowyBlock(world, pos);
        if(downPos.isEmpty()) {
            return;
        }
        var downState = world.getBlockState(downPos.get());
        var snowyLeaves = SnowyBlocksMap.get(downState.getBlock());

        if(snowyLeaves != null) {
            var layers = state.get(LAYERS) - 1;
            var newLeavesState = snowyLeaves.getDefaultState();
            // copy properties of snowy block
            for (Property property : downState.getProperties()) {
                newLeavesState = newLeavesState.contains(property) ? newLeavesState.with(property, downState.get(property)) : newLeavesState;
            }
            newLeavesState = newLeavesState.contains(SnowyLeavesBlock.BOTTOM) ? newLeavesState.with(SnowyLeavesBlock.BOTTOM, true) : newLeavesState;
            if(layers < 1) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
            else {
                world.setBlockState(pos, state.with(LAYERS, layers));
            }
            world.setBlockState(downPos.get(), newLeavesState);
            ci.cancel();
        }
    }

    @Unique
    private Optional<BlockPos> findNextBottomSnowyBlock(ServerWorld world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();

        BlockState blockState;
        do {
            mutable.move(Direction.DOWN);
            blockState = world.getBlockState(mutable);
        } while (isFullSnowyBlock(blockState));

        return SnowyBlocksMap.hasKey(blockState.getBlock()) ? Optional.of(mutable) : Optional.empty();
    }


    @Unique
    private boolean isFullSnowyBlock(BlockState state) {
        var block = state.getBlock();
        if(SnowyBlocksMap.hasValue(block)) return true;
        if(state.isOf(Blocks.SNOW_BLOCK) || state.isOf(Blocks.POWDER_SNOW)) return true;
        if(state.isOf(Blocks.SNOW) && state.contains(LAYERS) && state.get(LAYERS) == 8) return true;
        return false;
    }
}
