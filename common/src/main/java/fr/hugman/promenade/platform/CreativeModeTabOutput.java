package fr.hugman.promenade.platform;

import net.minecraft.world.level.ItemLike;

/**
 * The entries of an existing creative tab, as the loader lets us edit them.
 */
public interface CreativeModeTabOutput {
    /**
     * Inserts {@code items}, in order, right after {@code anchor}.
     * If the tab does not contain {@code anchor}, they are appended at the end of the tab.
     */
    void insertAfter(ItemLike anchor, ItemLike... items);
}
