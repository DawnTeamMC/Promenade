package fr.hugman.promenade.block.dispenser;

import fr.hugman.promenade.item.PromenadeItems;
import net.minecraft.world.level.block.DispenserBlock;

public class PromenadeDispenserBehaviors {
    public static void register() {
        var boatBehavior = new PromenadeBoatDispenseItemBehavior();
        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_BOAT, boatBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.SAKURA_CHEST_BOAT, boatBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_BOAT, boatBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.MAPLE_CHEST_BOAT, boatBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.PALM_BOAT, boatBehavior);
        DispenserBlock.registerBehavior(PromenadeItems.PALM_CHEST_BOAT, boatBehavior);
    }
}
