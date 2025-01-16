package fr.hugman.promenade.block.type;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.BlockSetType;

public class PromenadeBlockSetTypes {
    public static final BlockSetType SAKURA = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(Promenade.id("sakura"));
    public static final BlockSetType MAPLE = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Promenade.id("maple"));
    public static final BlockSetType PALM = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Promenade.id("palm"));
    public static final BlockSetType AMARANTH = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(Promenade.id("amaranth"));
}
