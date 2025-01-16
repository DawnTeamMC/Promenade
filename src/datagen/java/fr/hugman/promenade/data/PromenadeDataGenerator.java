package fr.hugman.promenade.data;

import com.google.common.reflect.Reflection;
import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

public class PromenadeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		Reflection.initialize(PromenadeBlockFamilies.class);

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(PromenadeModelProvider::new);
		pack.addProvider((fabricDataOutput, completableFuture) -> new FabricRecipeProvider(fabricDataOutput, completableFuture) {
			@Override
			protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
				return new PromenadeRecipeGenerator(wrapperLookup, recipeExporter);
			}

			@Override
			public String getName() {
				return "PromenadeRecipeGenerator";
			}
		});
	}

	@Override
	@Nullable
	public String getEffectiveModId() {
		return Promenade.MOD_ID;
	}
}
