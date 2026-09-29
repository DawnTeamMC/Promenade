package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.SnowyLeavesBlock;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.world.PromenadeGameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

@Mixin(SnowLayerBlock.class)
public class SnowBlockMixin {
    @Shadow @Final public static IntegerProperty LAYERS;

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void promenade$randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
		if (! world.getGameRules().get(PromenadeGameRules.DO_BLOCKS_GET_SNOWY)) {
            return;
        }
        if(state.getValue(LAYERS) == 8 && world.getBlockState(pos.above()).is((SnowLayerBlock)(Object)this)) {
            return;
        }
        var downPos = findNextBottomSnowyBlock(world, pos);
        if(downPos.isEmpty()) {
            return;
        }
        var downState = world.getBlockState(downPos.get());
        var snowyLeaves = world.registryAccess().lookupOrThrow(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION).stream().filter(
                entry -> entry.baseBlock().is(downState.getBlock().builtInRegistryHolder())
        ).findFirst().map(sbt -> sbt.snowyBlock().value()).orElse(null);

        if(snowyLeaves != null) {
            var layers = state.getValue(LAYERS) - 1;
            var newLeavesState = snowyLeaves.defaultBlockState();
            // copy properties of snowy block
            for (Property property : downState.getProperties()) {
                newLeavesState = newLeavesState.hasProperty(property) ? newLeavesState.setValue(property, downState.getValue(property)) : newLeavesState;
            }
            newLeavesState = newLeavesState.hasProperty(SnowyLeavesBlock.BOTTOM) ? newLeavesState.setValue(SnowyLeavesBlock.BOTTOM, true) : newLeavesState;
            if(layers < 1) {
                world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }
            else {
                world.setBlockAndUpdate(pos, state.setValue(LAYERS, layers));
            }
            world.setBlockAndUpdate(downPos.get(), newLeavesState);
            ci.cancel();
        }
    }

    @Unique
    private Optional<BlockPos> findNextBottomSnowyBlock(ServerLevel world, BlockPos pos) {
        BlockPos.MutableBlockPos mutable = pos.mutable();

        BlockState blockState;
        do {
            mutable.move(Direction.DOWN);
            blockState = world.getBlockState(mutable);
        } while (isFullSnowyBlock(world, blockState));

        var block = blockState.getBlock();
        return world.registryAccess().lookupOrThrow(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION).stream().anyMatch(
                entry -> entry.baseBlock().is(block.builtInRegistryHolder())
        ) ? Optional.of(mutable) : Optional.empty();
    }


    @Unique
    private boolean isFullSnowyBlock(ServerLevel world, BlockState state) {
        var block = state.getBlock();
        if (world.registryAccess().lookupOrThrow(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION).stream().anyMatch(
                entry -> entry.snowyBlock().is(block.builtInRegistryHolder())
        )) return true;
        if(state.is(Blocks.SNOW_BLOCK) || state.is(Blocks.POWDER_SNOW)) return true;
        if(state.is(Blocks.SNOW) && state.hasProperty(LAYERS) && state.getValue(LAYERS) == 8) return true;
        return false;
    }
}
