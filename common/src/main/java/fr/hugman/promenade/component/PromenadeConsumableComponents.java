package fr.hugman.promenade.component;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public class PromenadeConsumableComponents {
    public static final Consumable MAPLE_SYRUP_BOTTLE = Consumables.defaultDrink()
            .consumeSeconds(2.0F)
            .sound(SoundEvents.HONEY_DRINK)
            .onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.POISON))
            .build();

    public static final Consumable RAW_DUCK = foodWithEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F);

    public static Consumable foodWithEffect(MobEffectInstance effect, float probability) {
        return Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(effect, probability)).build();
    }
}
