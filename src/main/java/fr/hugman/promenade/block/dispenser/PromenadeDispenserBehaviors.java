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
        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.SAKURA_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_CHEST_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.SAKURA_CHEST_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.MAPLE_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_CHEST_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.MAPLE_CHEST_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.PALM_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.PALM_BOAT));
        DispenserBlock.registerBehavior(PromenadeItems.PALM_CHEST_BOAT, new BoatDispenseItemBehavior(PromenadeEntityTypes.PALM_CHEST_BOAT));
    }
}
