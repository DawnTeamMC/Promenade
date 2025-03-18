package fr.hugman.promenade.component;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.RemoveEffectsConsumeEffect;
import net.minecraft.sound.SoundEvents;

public class PromenadeConsumableComponents {
    public static final ConsumableComponent MAPLE_SYRUP_BOTTLE = ConsumableComponents.drink()
            .consumeSeconds(2.0F)
            .sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK)
            .consumeEffect(new RemoveEffectsConsumeEffect(StatusEffects.POISON))
            .build();

    public static final ConsumableComponent RAW_DUCK = foodWithEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.3F);

    public static ConsumableComponent foodWithEffect(StatusEffectInstance effect, float probability) {
        return ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(effect, probability)).build();
    }
}
