package fr.hugman.promenade.content;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.BlockSetType;

public class PromenadeBlockSetTypes {
    public static final BlockSetType MAPLE = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Promenade.id("maple"));
}
