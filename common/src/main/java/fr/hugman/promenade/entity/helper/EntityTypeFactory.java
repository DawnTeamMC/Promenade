package fr.hugman.promenade.entity.helper;

import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;

public class EntityTypeFactory {
    public static EntityType.Builder<Boat> boat(Supplier<Item> item) {
        EntityType.EntityFactory<Boat> factory = (type, world) -> new Boat(type, world, item);
        return EntityType.Builder.of(factory, MobCategory.MISC)
                .noLootTable()
                .sized(1.375F, 0.5625F)
                .eyeHeight(0.5625F)
                .clientTrackingRange(10);
    }

    public static EntityType.Builder<ChestBoat> chestBoat(Supplier<Item> item) {
        EntityType.EntityFactory<ChestBoat> factory = (type, world) -> new ChestBoat(type, world, item);
        return EntityType.Builder.of(factory, MobCategory.MISC)
                .noLootTable()
                .sized(1.375F, 0.5625F)
                .eyeHeight(0.5625F)
                .clientTrackingRange(10);
    }
}
