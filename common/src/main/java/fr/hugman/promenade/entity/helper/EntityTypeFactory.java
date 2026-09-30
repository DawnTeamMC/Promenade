package fr.hugman.promenade.entity.helper;

import fr.hugman.promenade.entity.PromenadeBoat;
import fr.hugman.promenade.entity.PromenadeChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class EntityTypeFactory {
    public static EntityType.Builder<PromenadeBoat> boat(Supplier<Item> item, Supplier<? extends ItemLike> planks) {
        EntityType.EntityFactory<PromenadeBoat> factory = (type, world) -> new PromenadeBoat(type, world, item, planks);
        return EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(1.375F, 0.5625F)
                .eyeHeight(0.5625F)
                .clientTrackingRange(10);
    }

    public static EntityType.Builder<PromenadeChestBoat> chestBoat(Supplier<Item> item, Supplier<? extends ItemLike> planks) {
        EntityType.EntityFactory<PromenadeChestBoat> factory = (type, world) -> new PromenadeChestBoat(type, world, item, planks);
        return EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(1.375F, 0.5625F)
                .eyeHeight(0.5625F)
                .clientTrackingRange(10);
    }
}
