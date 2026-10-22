package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.FluidPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.KilledByCrossbowTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import java.util.function.Consumer;

public class PromenadeAdvancementProvider extends FabricAdvancementProvider {
    public PromenadeAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement.Builder.advancement()
                .display(
                        PromenadeItems.MAPLE_SYRUP_BOTTLE,
                        Component.translatable("advancements.promenade.husbandry.harvest_maple_syrup.title"),
                        Component.translatable("advancements.promenade.husbandry.harvest_maple_syrup.description"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .parent(vanilla("husbandry/safely_harvest_honey"))
                .addCriterion("harvest_maple_syrup", createPickMapleSyrup())
                .save(consumer, Promenade.MOD_ID + ":husbandry/harvest_maple_syrup");
        Advancement.Builder.advancement()
                .display(
                        Items.FIRE_CORAL,
                        Component.translatable("advancements.promenade.adventure.kill_sunken_outside_water.title"),
                        Component.translatable("advancements.promenade.adventure.kill_sunken_outside_water.description"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .parent(vanilla("adventure/whos_the_pillager_now"))
                .addCriterion("kill_sunken_outside_water", createCrossbowSunkenOutsideWaterFromWater())
                .rewards(AdvancementRewards.Builder.experience(65))
                .save(consumer, Promenade.MOD_ID + ":adventure/kill_sunken_outside_water");


    }

    /**
     * In 1.20.1, advancements can only be built once their parent is: this stands for a vanilla one, of which only the id is saved.
     */
    private static Advancement vanilla(String path) {
        return Advancement.Builder.advancement().build(new ResourceLocation(path));
    }

    public static KilledByCrossbowTrigger.TriggerInstance createCrossbowSunkenOutsideWaterFromWater() {
        var water = FluidPredicate.Builder.fluid().of(FluidTags.WATER).build();
        return new KilledByCrossbowTrigger.TriggerInstance(
                // player is in water
                ContextAwarePredicate.create(LocationCheck.checkLocation(LocationPredicate.Builder.location().setFluid(water), new BlockPos(0, 1, 0)).build()),
                // entity is a sunken and is outside water
                new ContextAwarePredicate[]{ContextAwarePredicate.create(
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(PromenadeEntityTypes.SUNKEN)).build(),
                        InvertedLootItemCondition.invert(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setFluid(water).build())
                        )).build()
                )},
                MinMaxBounds.Ints.ANY
        );
    }


    public static ItemUsedOnLocationTrigger.TriggerInstance createPickMapleSyrup() {
        return ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(PromenadeBlocks.STRIPPED_MAPLE_LOG).build()),
                ItemPredicate.Builder.item().of(Items.GLASS_BOTTLE)
        );
    }
}
