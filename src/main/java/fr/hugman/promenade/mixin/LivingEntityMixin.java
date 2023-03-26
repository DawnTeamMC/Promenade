package fr.hugman.promenade.mixin;

import fr.hugman.promenade.registry.content.GlaglaglaContent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	// inject before the second call to `this.setFrozenTicks()` in the method `public void tickMovement()`
	@Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFrozenTicks(I)V", ordinal = 1), cancellable = true)
	private void tickMovement(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		int frozenTicks = entity.getFrozenTicks();
		if(GlaglaglaContent.canFreezeFromBiomeAndWeather(entity)) {
			entity.setFrozenTicks(Math.min(entity.getMinFreezeDamageTicks(), frozenTicks + 1));
			if (entity.age % 40 == 0 && entity.isFrozen()) {
				entity.damage(entity.getDamageSources().freeze(), entity.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES) ? 5 : 1);
			}
			ci.cancel();
		}
	}
}
