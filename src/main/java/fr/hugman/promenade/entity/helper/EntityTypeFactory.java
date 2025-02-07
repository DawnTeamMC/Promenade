package fr.hugman.promenade.entity.helper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class EntityTypeFactory {
    public static EntityType.Builder<BoatEntity> boat(Supplier<Item> item) {
        EntityType.EntityFactory<BoatEntity> factory = (type, world) -> new BoatEntity(type, world, item);
        return EntityType.Builder.create(factory, SpawnGroup.MISC)
                .dropsNothing()
                .dimensions(1.375F, 0.5625F)
                .eyeHeight(0.5625F)
                .maxTrackingRange(10);
    }

    public static EntityType.Builder<ChestBoatEntity> chestBoat(Supplier<Item> item) {
        EntityType.EntityFactory<ChestBoatEntity> factory = (type, world) -> new ChestBoatEntity(type, world, item);
        return EntityType.Builder.create(factory, SpawnGroup.MISC)
                .dropsNothing()
                .dimensions(1.375F, 0.5625F)
                .eyeHeight(0.5625F)
                .maxTrackingRange(10);
    }
}
