package com.hugman.wild_explorer.object.block;

import com.hugman.wild_explorer.init.WEItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlueberryBushBlock extends SweetBerryBushBlock {
	public BlueberryBushBlock(Settings builder) {
		super(builder);
	}

	@Override
	public ItemStack getPickStack(BlockView worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(WEItems.BLUEBERRIES);
	}

	@Override
	public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
		int i = state.get(AGE);
		boolean flag = i == 3;
		if(!flag && player.getStackInHand(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResult.PASS;
		}
		else if(i > 1) {
			int j = 1 + worldIn.random.nextInt(2);
			dropStack(worldIn, pos, new ItemStack(WEItems.BLUEBERRIES, j + (flag ? 1 : 0)));
			worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, 1), 2);
			return ActionResult.SUCCESS;
		}
		else {
			return super.onUse(state, worldIn, pos, player, handIn, hit);
		}
	}
}
