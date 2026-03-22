package fr.hugman.promenade.mixin;

import fr.hugman.promenade.world.biome.PromenadeBiomes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setTicksFrozen(I)V", ordinal = 0), cancellable = true)
    private void promenade$tickMovement(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        int frozenTicks = entity.getTicksFrozen();
        if (entity.level() instanceof ServerLevel serverWorld && PromenadeBiomes.canFreezeFromBiomeAndWeather(entity)) {
            entity.setTicksFrozen(Math.min(entity.getTicksRequiredToFreeze(), frozenTicks + 1));
            if (entity.tickCount % 40 == 0 && entity.isFullyFrozen()) {
                entity.hurtServer(serverWorld, entity.damageSources().freeze(), entity.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES) ? 5 : 1);
            }
            ci.cancel();
        }
    }
}
