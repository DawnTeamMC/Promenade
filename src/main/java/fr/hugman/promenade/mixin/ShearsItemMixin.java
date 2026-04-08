package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.AbstractFacingPlantStemBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void promenade$useOnBlock(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getBlock() instanceof AbstractFacingPlantStemBlock stem && !stem.hasMaxAge(blockState)) {
            Player playerEntity = context.getPlayer();
            ItemStack itemStack = context.getItemInHand();
            if (playerEntity instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) playerEntity, blockPos, itemStack);
            }

            world.playSound(playerEntity, blockPos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);
            BlockState blockState2 = stem.withMaxAge(blockState);
            world.setBlockAndUpdate(blockPos, blockState2);
            world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(context.getPlayer(), blockState2));
            if (playerEntity != null) {
                itemStack.hurtAndBreak(1, playerEntity, context.getHand());
            }

            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}