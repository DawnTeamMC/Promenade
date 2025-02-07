package fr.hugman.promenade.block.dispenser;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;

public class PromenadeDispenserBehaviors {
    public static void register() {
        ItemDispenserBehavior spawnEggBehavior = new ItemDispenserBehavior() {
            @Override
            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                Direction direction = pointer.state().get(DispenserBlock.FACING);
                EntityType<?> entityType = ((SpawnEggItem) stack.getItem()).getEntityType(pointer.world().getRegistryManager(), stack);

                try {
                    entityType.spawnFromItemStack(pointer.world(), stack, null, pointer.pos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                } catch (Exception var6) {
                    LOGGER.error("Error while dispensing spawn egg from dispenser at {}", pointer.pos(), var6);
                    return ItemStack.EMPTY;
                }

                stack.decrement(1);
                pointer.world().emitGameEvent(null, GameEvent.ENTITY_PLACE, pointer.pos());
                return stack;
            }
        };

        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_BOAT, new BoatDispenserBehavior(PromenadeEntityTypes.SAKURA_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_CHEST_BOAT, new BoatDispenserBehavior(PromenadeEntityTypes.SAKURA_CHEST_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_BOAT, new BoatDispenserBehavior(PromenadeEntityTypes.MAPLE_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_CHEST_BOAT, new BoatDispenserBehavior(PromenadeEntityTypes.MAPLE_CHEST_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.PALM_BOAT, new BoatDispenserBehavior(PromenadeEntityTypes.PALM_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.PALM_CHEST_BOAT, new BoatDispenserBehavior(PromenadeEntityTypes.PALM_CHEST_BOAT));


        DispenserBlock.registerBehavior(PromenadeItems.CAPYBARA_SPAWN_EGG, spawnEggBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.DUCK_SPAWN_EGG, spawnEggBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.LUSH_CREEPER_SPAWN_EGG, spawnEggBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.SUNKEN_SPAWN_EGG, spawnEggBehavior);
    }
}
