package fr.hugman.promenade.block;

import fr.hugman.promenade.registry.content.MapleContent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

//TODO add Codec
public class StrippedMapleLogBlock extends PillarBlock {
	public static final BooleanProperty DRIP = PromenadeBlockProperties.DRIP;

	//TODO : add dispenser behavior

	public StrippedMapleLogBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(DRIP, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(DRIP);
	}

	@Override
	protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		// if the player is holding a bottle, they can collect the syrup
		if(state.get(DRIP)) {
			if(stack.getItem() == Items.GLASS_BOTTLE) {
				stack.decrement(1);
				world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				if(stack.isEmpty()) {
					player.setStackInHand(hand, new ItemStack(MapleContent.MAPLE_SYRUP_BOTTLE));
				}
				else if(!player.getInventory().insertStack(new ItemStack(MapleContent.MAPLE_SYRUP_BOTTLE))) {
					player.dropItem(new ItemStack(MapleContent.MAPLE_SYRUP_BOTTLE), false);
				}
				world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
				player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
				world.setBlockState(pos, state.with(DRIP, false));
				return ItemActionResult.success(world.isClient);
			}
		}
		return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
	}
}
