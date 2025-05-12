package fr.hugman.promenade.mixin;

import fr.hugman.promenade.world.biome.PromenadeBiomes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFrozenTicks(I)V", ordinal = 0), cancellable = true)
    private void promenade$tickMovement(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        int frozenTicks = entity.getFrozenTicks();
        if (entity.getEntityWorld() instanceof ServerWorld serverWorld && PromenadeBiomes.canFreezeFromBiomeAndWeather(entity)) {
            entity.setFrozenTicks(Math.min(entity.getMinFreezeDamageTicks(), frozenTicks + 1));
            if (entity.age % 40 == 0 && entity.isFrozen()) {
                entity.damage(serverWorld, entity.getDamageSources().freeze(), entity.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES) ? 5 : 1);
            }
            ci.cancel();
        }
    }
}
