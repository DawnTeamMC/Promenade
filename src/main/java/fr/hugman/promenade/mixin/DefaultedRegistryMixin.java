// Code modified from https://github.com/Noaaan/MythicMetals/blob/34959d8caf773d0c54c81281b6e57b19662579b3/src/main/java/nourl/mythicmetals/mixin/DefaultedRegistryMixin.java

package fr.hugman.promenade.mixin;

import java.util.*;
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
		// A map for storing and retrieving remapped IDs
		Map promenadeUpdatedIDs = new HashMap();
		promenadeUpdatedIDs.put("autumn_oak_leaves", "fulvous_maple_leaves");
		promenadeUpdatedIDs.put("autumn_birch_leaves", "mikado_maple_leaves");
		promenadeUpdatedIDs.put("autumn_oak_sapling", "fulvous_maple_sapling");
		promenadeUpdatedIDs.put("autumn_birch_sapling", "mikado_maple_sapling");
		promenadeUpdatedIDs.put("autumn_oak_leaf_pile", "fulvous_maple_leaf_pile");
		promenadeUpdatedIDs.put("autumn_birch_leaf_pile", "mikado_maple_leaf_pile");
		promenadeUpdatedIDs.put("potted_autumn_oak_sapling", "potted_fulvous_maple_sapling");
		promenadeUpdatedIDs.put("potted_autumn_birch_sapling", "potted_mikado_maple_sapling");
		promenadeUpdatedIDs.put("pink_cherry_oak_leaves", "blush_sakura_blossoms");
		promenadeUpdatedIDs.put("white_cherry_oak_leaves", "cotton_sakura_blossoms");
		promenadeUpdatedIDs.put("pink_cherry_oak_sapling", "blush_sakura_sapling");
		promenadeUpdatedIDs.put("white_cherry_oak_sapling", "cotton_sakura_sapling");
		promenadeUpdatedIDs.put("pink_cherry_oak_leaf_pile", "blush_sakura_blossom_pile");
		promenadeUpdatedIDs.put("white_cherry_oak_leaf_pile", "cotton_sakura_blossom_pile");
		promenadeUpdatedIDs.put("potted_pink_cherry_oak_sapling", "potted_blush_sakura_sapling");
		promenadeUpdatedIDs.put("potted_white_cherry_oak_sapling", "potted_cotton_sakura_sapling");
		promenadeUpdatedIDs.put("cherry_oak_door", "sakura_door");
		promenadeUpdatedIDs.put("cherry_oak_fence_gate", "sakura_fence_gate");
		promenadeUpdatedIDs.put("cherry_oak_fence", "sakura_fence");
		promenadeUpdatedIDs.put("cherry_oak_button", "sakura_button");
		promenadeUpdatedIDs.put("cherry_oak_pressure_plate", "sakura_pressure_plate");
		promenadeUpdatedIDs.put("cherry_oak_trapdoor", "sakura_trapdoor");
		promenadeUpdatedIDs.put("cherry_oak_planks", "sakura_planks");
		promenadeUpdatedIDs.put("cherry_oak_log", "sakura_log");
		promenadeUpdatedIDs.put("cherry_oak_wood", "sakura_wood");
		promenadeUpdatedIDs.put("stripped_cherry_oak_log", "stripped_sakura_log");
		promenadeUpdatedIDs.put("stripped_cherry_oak_wood", "stripped_sakura_wood");
		promenadeUpdatedIDs.put("cherry_oak_stairs", "sakura_stairs");
		promenadeUpdatedIDs.put("cherry_oak_slab", "sakura_slab");
		promenadeUpdatedIDs.put("carbonite", "asphalt");
		promenadeUpdatedIDs.put("carbonite_slab", "asphalt_slab");
		promenadeUpdatedIDs.put("carbonite_stairs", "asphalt_stairs");
		promenadeUpdatedIDs.put("carbonite_wall", "asphalt_wall");
		promenadeUpdatedIDs.put("polished_carbonite", "polished_asphalt");
		promenadeUpdatedIDs.put("polished_carbonite_slab", "polished_asphalt_slab");
		promenadeUpdatedIDs.put("polished_carbonite_stairs", "polished_asphalt_stairs");
		if (id != null) {
			// Various MOD_ID renames across mod versions, including Mythic Metals Decorations
			if (id.getNamespace().equals("promenade")) {
				String objectPath = id.getPath();
				if (promenadeUpdatedIDs.containsKey(objectPath)) {
					return new Identifier("promenade", (String) promenadeUpdatedIDs.get(objectPath));
				};
			} else {
				return id;
			}
		}
		return id;
	}
}
