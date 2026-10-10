package fr.hugman.promenade.block.property;

import fr.hugman.promenade.block.MoaiType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PromenadeBlockProperties {
    public static final BooleanProperty NATURAL = BooleanProperty.create("natural");
    public static final BooleanProperty DRIP = BooleanProperty.create("drip");
    public static final EnumProperty<MoaiType> MOAI_TYPE = EnumProperty.create("type", MoaiType.class);
    public static final IntegerProperty DISTANCE_1_14 = IntegerProperty.create("distance", 1, 14);
}
