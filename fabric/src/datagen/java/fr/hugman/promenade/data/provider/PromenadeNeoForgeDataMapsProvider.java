package fr.hugman.promenade.data.provider;

import com.google.gson.JsonObject;
import fr.hugman.promenade.registry.PromenadeCompostables;
import fr.hugman.promenade.registry.PromenadeStrippables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Generates the NeoForge data maps through which NeoForge adds stripping and composting (Fabric registers them at
 * runtime instead). The files are ignored on Fabric.
 */
public class PromenadeNeoForgeDataMapsProvider implements DataProvider {
    private final PackOutput.PathProvider blockDataMaps;
    private final PackOutput.PathProvider itemDataMaps;

    public PromenadeNeoForgeDataMapsProvider(FabricDataOutput output) {
        this.blockDataMaps = output.createPathProvider(PackOutput.Target.DATA_PACK, "data_maps/block");
        this.itemDataMaps = output.createPathProvider(PackOutput.Target.DATA_PACK, "data_maps/item");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        JsonObject strippables = new JsonObject();
        PromenadeStrippables.register((block, stripped) -> {
            JsonObject value = new JsonObject();
            value.addProperty("stripped_block", BuiltInRegistries.BLOCK.getKey(stripped).toString());
            strippables.add(BuiltInRegistries.BLOCK.getKey(block).toString(), value);
        });
        futures.add(DataProvider.saveStable(writer, values(strippables), this.blockDataMaps.json(neoforge("strippables"))));

        JsonObject compostables = new JsonObject();
        PromenadeCompostables.all().forEach((item, chance) -> {
            JsonObject value = new JsonObject();
            value.addProperty("chance", chance);
            compostables.add(BuiltInRegistries.ITEM.getKey(item.asItem()).toString(), value);
        });
        futures.add(DataProvider.saveStable(writer, values(compostables), this.itemDataMaps.json(neoforge("compostables"))));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private static JsonObject values(JsonObject values) {
        JsonObject root = new JsonObject();
        root.add("values", values);
        return root;
    }

    private static ResourceLocation neoforge(String path) {
        return ResourceLocation.fromNamespaceAndPath("neoforge", path);
    }

    @Override
    public String getName() {
        return "NeoForge Data Maps";
    }
}
