package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.MapleLogBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.block.StrippedMapleLogBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(method = "getStripped", at = @At("RETURN"), cancellable = true)
    private void promenade$appendCustomStrip(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
        if (state.getBlock() == PromenadeBlocks.MAPLE_LOG) {
            // if the log is natural, it has a 10% chance to be stripped into a stripped log with the state "has_syrup" set to true
            cir.setReturnValue(Optional.of(PromenadeBlocks.STRIPPED_MAPLE_LOG.defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS))
                    .setValue(StrippedMapleLogBlock.DRIP, state.getValue(MapleLogBlock.NATURAL) && Math.random() < 0.1f)));
        }
    }
}