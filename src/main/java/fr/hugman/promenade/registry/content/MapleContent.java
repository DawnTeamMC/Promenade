package fr.hugman.promenade.registry.content;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.content.PromenadeBlocks;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class MapleContent {
    public static final SignBlocks MAPLE_SIGNS = DawnFactory.signs(Promenade.id("maple"), PromenadeBlocks.MAPLE_PLANKS);
    public static final TerraformBoatType MAPLE_BOAT_TYPE = DawnFactory.boat(Promenade.id("maple"), PromenadeBlocks.MAPLE_PLANKS); //TODO change when possible (PR #72 on TerraformersMC/Terraform)

    public static final Item MAPLE_SYRUP_BOTTLE = new HoneyBottleItem(new Item.Settings()
            .maxCount(16)
            .food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
            .recipeRemainder(Items.GLASS_BOTTLE));


    public static final RegistryKey<Biome> CARNELIAN_TREEWAY = DawnFactory.biome(Promenade.id("carnelian_treeway"));

    public static void register(Registrar r) {
        r.add(("maple"), MAPLE_SIGNS);
        r.add(("maple"), MAPLE_BOAT_TYPE);

        r.add(("maple_syrup_bottle"), MAPLE_SYRUP_BOTTLE);

        appendItemGroups();
        appendWorldGen();
    }

    private static void appendItemGroups() {
        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(SakuraContent.SAKURA_SIGNS.hangingSign(), MAPLE_SIGNS.sign(), MAPLE_SIGNS.hangingSign()));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(SakuraContent.SAKURA_BOAT_TYPE.getChestItem(), MAPLE_BOAT_TYPE.getItem(), MAPLE_BOAT_TYPE.getChestItem()));
        ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.HONEY_BOTTLE, MAPLE_SYRUP_BOTTLE));
    }

    private static void appendWorldGen() {
        if (Promenade.CONFIG.biomes.carnelian_treeway_weight <= 0) {
            return;
        }
        double weight = Promenade.CONFIG.biomes.carnelian_treeway_weight / 100.0D;
        BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, CARNELIAN_TREEWAY, weight);
    }
}
