package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.MapleLogBlock;
import fr.hugman.promenade.block.StrippedMapleLogBlock;
import fr.hugman.promenade.registry.content.MapleContent;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeItemMixin {
	@Inject(method = "getStrippedState", at = @At("RETURN"), cancellable = true)
	private void promenade$appendCustomStrip(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
		if(state.getBlock() == MapleContent.MAPLE_LOG) {
			// if the log is natural, it has a 10% chance to be stripped into a stripped log with the state "has_syrup" set to true
			cir.setReturnValue(Optional.of(MapleContent.STRIPPED_MAPLE_LOG.getDefaultState()
					.with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
					.with(StrippedMapleLogBlock.DRIP, state.get(MapleLogBlock.NATURAL) && Math.random() < 0.1f)));
		}
	}
}