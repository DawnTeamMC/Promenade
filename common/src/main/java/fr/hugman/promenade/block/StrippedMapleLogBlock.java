package fr.hugman.promenade.block;

import fr.hugman.promenade.block.property.PromenadeBlockProperties;
import fr.hugman.promenade.item.PromenadeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

//TODO make generic
public class StrippedMapleLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty DRIP = PromenadeBlockProperties.DRIP;
    /**
     * The chance that a natural maple log drips syrup once stripped.
     */
    public static final float DRIP_CHANCE = 0.1F;

    //TODO : add dispenser behavior

    public StrippedMapleLogBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(DRIP, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(DRIP);
    }

    /**
     * Stripping a natural maple log may make it drip syrup. This is done here rather than in the stripping itself, as
     * the loaders only let blocks strip into a fixed state.
     */
    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, world, pos, oldState, movedByPiston);
        if (!state.getValue(DRIP) && oldState.getBlock() instanceof MapleLogBlock && oldState.getValue(MapleLogBlock.NATURAL) && world.getRandom().nextFloat() < DRIP_CHANCE) {
            world.setBlock(pos, state.setValue(DRIP, true), Block.UPDATE_ALL);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        // if the player is holding a bottle, they can collect the syrup
        if (state.getValue(DRIP)) {
            if (stack.getItem() == Items.GLASS_BOTTLE) {
                stack.shrink(1);
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, new ItemStack(PromenadeItems.MAPLE_SYRUP_BOTTLE));
                } else if (!player.getInventory().add(new ItemStack(PromenadeItems.MAPLE_SYRUP_BOTTLE))) {
                    player.drop(new ItemStack(PromenadeItems.MAPLE_SYRUP_BOTTLE), false);
                }
                world.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                world.setBlockAndUpdate(pos, state.setValue(DRIP, false));
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }
}
