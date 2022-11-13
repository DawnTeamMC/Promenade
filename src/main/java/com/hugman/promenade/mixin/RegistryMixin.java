package com.hugman.promenade.mixin;

import com.hugman.newdawn.DawnBlockSettings;
import com.hugman.newdawn.DawnItem;
import com.hugman.newdawn.DawnItemSettings;
import com.hugman.newdawn.RegistryHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Registry.class)
public class RegistryMixin {
	@Inject(method = "register(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/RegistryKey;Ljava/lang/Object;)Ljava/lang/Object;", at = @At("RETURN"))
	private static <V, T extends V> void register(Registry<V> registry, RegistryKey<V> key, T entry, CallbackInfoReturnable<T> cir) {
		if(entry instanceof Block block) {
			if(((AbstractBlockAccessor) block).getSettings() instanceof DawnBlockSettings settings) {
				if(settings.getFlameBurn() != 0 && settings.getFlameSpread() != 0)
					FlammableBlockRegistry.getDefaultInstance().add(block, settings.getFlameBurn(), settings.getFlameSpread());
				if(settings.getStripInto() != null)
					StrippableBlockRegistry.register(block, settings.getStripInto());
				if(settings.getItemSettings() != null)
					RegistryHelper.item(key.getValue(), new BlockItem(block, settings.getItemSettings()));
			}
		}
		else if(entry instanceof Item item) {
			DawnItemSettings settings = ((DawnItem)item).getDawnSettings();
			if(settings != null) {
				if(settings.getCompostingChance() > 0.0f)
					CompostingChanceRegistry.INSTANCE.add(item, settings.getCompostingChance());
				if(settings.getFuelTime() > 0)
					FuelRegistry.INSTANCE.add(item, settings.getFuelTime());
			}
		}
	}
}
