package fr.hugman.promenade.block;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.WoodType;

public class PromenadeWoodTypes {
    public static final WoodType MAPLE = WoodTypeBuilder.copyOf(WoodType.OAK).register(Promenade.id("maple"), PromenadeBlockSetTypes.MAPLE);
}
