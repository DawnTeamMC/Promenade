package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.tag.PromenadeBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.tag.PromenadeItemTags.*;

public class PromenadeItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public PromenadeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        copy(PromenadeBlockTags.SAKURA_LOGS, SAKURA_LOGS);
        copy(PromenadeBlockTags.MAPLE_LOGS, MAPLE_LOGS);
        copy(PromenadeBlockTags.PALM_LOGS, PALM_LOGS);
        copy(PromenadeBlockTags.DARK_AMARANTH_STEMS, DARK_AMARANTH_STEMS);

        getOrCreateTagBuilder(CAPYBARA_FOOD).add(Items.CARROT, Items.MELON_SLICE);
        getOrCreateTagBuilder(DUCK_FOOD).add(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.TORCHFLOWER_SEEDS, Items.PITCHER_POD);
    }
}