package fr.hugman.promenade.block.property;

import fr.hugman.promenade.block.MoaiType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class PromenadeBlockProperties {
    public static final BooleanProperty NATURAL = BooleanProperty.of("natural");
    public static final BooleanProperty DRIP = BooleanProperty.of("drip");
    public static final EnumProperty<MoaiType> MOAI_TYPE = EnumProperty.of("type", MoaiType.class);
    public static final IntProperty DISTANCE_1_14 = IntProperty.of("distance", 1, 14);
}
