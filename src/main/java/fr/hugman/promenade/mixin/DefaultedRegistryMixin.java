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
			if (id.getNamespace().equals("promenade")) {
				if (id.getPath().equals("autumn_oak_leaves")) {
					return new Identifier("promenade", "fulvous_maple_leaves");
				} else if (id.getPath().equals("autumn_birch_leaves")) {
					return new Identifier("promenade", "mikado_maple_leaves");
				} else if (id.getPath().equals("autumn_oak_sapling")) {
					return new Identifier("promenade", "fulvous_maple_sapling");
				} else if (id.getPath().equals("autumn_birch_sapling")) {
					return new Identifier("promenade", "mikado_maple_sapling");
				} else if (id.getPath().equals("autumn_oak_leaf_pile")) {
					return new Identifier("promenade", "fulvous_maple_leaf_pile");
				} else if (id.getPath().equals("autumn_birch_leaf_pile")) {
					return new Identifier("promenade", "mikado_maple_leaf_pile");
				} else if (id.getPath().equals("potted_autumn_oak_sapling")) {
					return new Identifier("promenade", "potted_fulvous_maple_sapling");
				} else if (id.getPath().equals("potted_autumn_birch_sapling")) {
					return new Identifier("promenade", "potted_mikado_maple_sapling");
				} else if (id.getPath().equals("pink_cherry_oak_leaves")) {
					return new Identifier("promenade", "blush_sakura_blossoms");
				} else if (id.getPath().equals("white_cherry_oak_leaves")) {
					return new Identifier("promenade", "cotton_sakura_blossoms");
				} else if (id.getPath().equals("pink_cherry_oak_sapling")) {
					return new Identifier("promenade", "blush_sakura_sapling");
				} else if (id.getPath().equals("white_cherry_oak_sapling")) {
					return new Identifier("promenade", "cotton_sakura_sapling");
				} else if (id.getPath().equals("pink_cherry_oak_leaf_pile")) {
					return new Identifier("promenade", "blush_sakura_blossom_pile");
				} else if (id.getPath().equals("white_cherry_oak_leaf_pile")) {
					return new Identifier("promenade", "cotton_sakura_blossom_pile");
				} else if (id.getPath().equals("potted_pink_cherry_oak_sapling")) {
					return new Identifier("promenade", "potted_blush_sakura_sapling");
				} else if (id.getPath().equals("potted_white_cherry_oak_sapling")) {
					return new Identifier("promenade", "potted_cotton_sakura_sapling");
				} else if (id.getPath().equals("cherry_oak_door")) {
					return new Identifier("promenade", "sakura_door");
				} else if (id.getPath().equals("cherry_oak_fence_gate")) {
					return new Identifier("promenade", "sakura_fence_gate");
				} else if (id.getPath().equals("cherry_oak_fence")) {
					return new Identifier("promenade", "sakura_fence");
				} else if (id.getPath().equals("cherry_oak_button")) {
					return new Identifier("promenade", "sakura_button");
				} else if (id.getPath().equals("cherry_oak_pressure_plate")) {
					return new Identifier("promenade", "sakura_pressure_plate");
				} else if (id.getPath().equals("cherry_oak_trapdoor")) {
					return new Identifier("promenade", "sakura_trapdoor");
				} else if (id.getPath().equals("cherry_oak_planks")) {
					return new Identifier("promenade", "sakura_planks");
				} else if (id.getPath().equals("cherry_oak_log")) {
					return new Identifier("promenade", "sakura_log");
				} else if (id.getPath().equals("cherry_oak_wood")) {
					return new Identifier("promenade", "sakura_wood");
				} else if (id.getPath().equals("stripped_cherry_oak_log")) {
					return new Identifier("promenade", "stripped_sakura_log");
				} else if (id.getPath().equals("stripped_cherry_oak_wood")) {
					return new Identifier("promenade", "stripped_sakura_wood");
				} else if (id.getPath().equals("cherry_oak_stairs")) {
					return new Identifier("promenade", "sakura_stairs");
				} else if (id.getPath().equals("cherry_oak_slab")) {
					return new Identifier("promenade", "sakura_slab");
				} else if (id.getPath().equals("carbonite")) {
					return new Identifier("promenade", "asphalt");
				} else if (id.getPath().equals("carbonite_slab")) {
					return new Identifier("promenade", "asphalt_slab");
				} else if (id.getPath().equals("carbonite_stairs")) {
					return new Identifier("promenade", "asphalt_stairs");
				} else if (id.getPath().equals("carbonite_wall")) {
					return new Identifier("promenade", "asphalt_wall");
				} else if (id.getPath().equals("polished_carbonite")) {
					return new Identifier("promenade", "polished_asphalt");
				} else if (id.getPath().equals("polished_carbonite_slab")) {
					return new Identifier("promenade", "polished_asphalt_slab");
				} else if (id.getPath().equals("polished_carbonite_stairs")) {
					return new Identifier("promenade", "polished_asphalt_stairs");
				}
			}
		}
		return id;
	}
}