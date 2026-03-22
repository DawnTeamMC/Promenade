package fr.hugman.promenade.block.dispenser;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;

public class PromenadeDispenserBehaviors {
    public static void register() {
        DefaultDispenseItemBehavior spawnEggBehavior = new DefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(BlockSource pointer, ItemStack stack) {
                Direction direction = pointer.state().getValue(DispenserBlock.FACING);
                EntityType<?> entityType = ((SpawnEggItem) stack.getItem()).getType(stack);

                try {
                    entityType.spawn(pointer.level(), stack, null, pointer.pos().relative(direction), EntitySpawnReason.DISPENSER, direction != Direction.UP, false);
                } catch (Exception var6) {
                    LOGGER.error("Error while dispensing spawn egg from dispenser at {}", pointer.pos(), var6);
                    return ItemStack.EMPTY;
                }

                stack.shrink(1);
                pointer.level().gameEvent(null, GameEvent.ENTITY_PLACE, pointer.pos());
                return stack;
            }
        };

        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.SAKURA_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_CHEST_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.SAKURA_CHEST_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.MAPLE_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_CHEST_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.MAPLE_CHEST_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.PALM_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.PALM_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.PALM_CHEST_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.PALM_CHEST_BOAT));


        DispenserBlock.registerBehavior(PromenadeItems.CAPYBARA_SPAWN_EGG, spawnEggBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.DUCK_SPAWN_EGG, spawnEggBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.LUSH_CREEPER_SPAWN_EGG, spawnEggBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.SUNKEN_SPAWN_EGG, spawnEggBehavior);
    }
}
