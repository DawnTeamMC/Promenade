package com.hugman.promenade.mixin;

import com.hugman.promenade.Promenade;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DefaultedRegistry.class)
public class DefaultedRegistryMixin {
	@ModifyVariable(at = @At("HEAD"), method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", ordinal = 0)
	Identifier fixMissingFromRegistry(@Nullable Identifier id) {
		if(id != null) {
			if(id.getNamespace().equals("wild_explorer")) return new Identifier(Promenade.MOD_DATA.getModName(), id.getPath());
		}
		return id;
	}
}