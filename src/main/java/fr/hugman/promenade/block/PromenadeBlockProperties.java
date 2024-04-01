package fr.hugman.promenade.block;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public class PromenadeBlockProperties {
    public static final BooleanProperty NATURAL = BooleanProperty.of("natural");
    public static final BooleanProperty DRIP = BooleanProperty.of("drip");
    public static final EnumProperty<MoaiType> MOAI_TYPE = EnumProperty.of("type", MoaiType.class);
}
