package fr.hugman.promenade.block;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.WoodType;

public class PromenadeWoodTypes {
    public static final WoodType SAKURA = WoodTypeBuilder.copyOf(WoodType.CHERRY).register(Promenade.id("sakura"), PromenadeBlockSetTypes.SAKURA);
    public static final WoodType MAPLE = WoodTypeBuilder.copyOf(WoodType.OAK).register(Promenade.id("maple"), PromenadeBlockSetTypes.MAPLE);
    public static final WoodType PALM = WoodTypeBuilder.copyOf(WoodType.OAK).register(Promenade.id("palm"), PromenadeBlockSetTypes.PALM);
    public static final WoodType AMARANTH = WoodTypeBuilder.copyOf(WoodType.CRIMSON).register(Promenade.id("amaranth"), PromenadeBlockSetTypes.AMARANTH);
}
