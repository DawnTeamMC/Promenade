// Code modified from https://github.com/Noaaan/MythicMetals/blob/34959d8caf773d0c54c81281b6e57b19662579b3/src/main/java/nourl/mythicmetals/mixin/DefaultedRegistryMixin.java

package fr.hugman.promenade.mixin;

import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// This Mixin is a class that works as a datafixer.
// Upon loading a world it will check for missing objects in the recipe and replace them in order to
// prevent air pockets when upgrading from older worlds, as well as returning changed/removed items.
@Mixin(SimpleDefaultedRegistry.class)
public class DefaultedRegistryMixin {

	@ModifyVariable(at = @At("HEAD"), method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", ordinal = 0, argsOnly = true)
	Identifier fixMissingFromRegistry(@Nullable Identifier id) {
		if (id != null) {
			// Various MOD_ID renames across mod versions, including Mythic Metals Decorations
			if (id.getPath().equals("mm_decorations"))
				return new Identifier("mythicmetals_decorations", id.getPath());
			if (id.getNamespace().equals("mythicaddons") && !id.getPath().contains("aegis"))
				return new Identifier("mythicmetals_decorations", id.getPath());
			if (id.getNamespace().equals("mythicaddons") && id.getPath().contains("aegis"))
				return new Identifier(MythicMetals.MOD_ID, id.getPath());
			if (LegacyIds.getLegacyIds().containsKey(id))
				return LegacyIds.getLegacyIds().get(id);
		}
		return id;
	}
}