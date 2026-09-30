package fr.hugman.promenade.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * A chest boat of one of the mod's wood types (see {@link PromenadeBoat}).
 */
public class PromenadeChestBoat extends ChestBoat {
    private final Supplier<Item> item;
    private final Supplier<? extends ItemLike> planks;

    public PromenadeChestBoat(EntityType<? extends Boat> type, Level world, Supplier<Item> item, Supplier<? extends ItemLike> planks) {
        super(type, world);
        this.item = item;
        this.planks = planks;
    }

    @Override
    public Item getDropItem() {
        return this.item.get();
    }

    /**
     * Boats that break when falling drop the planks of their vanilla variant, which is always oak here.
     */
    @Nullable
    @Override
    public ItemEntity spawnAtLocation(ItemLike item) {
        return super.spawnAtLocation(item == Blocks.OAK_PLANKS ? this.planks.get() : item);
    }
}
