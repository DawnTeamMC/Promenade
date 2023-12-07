package fr.hugman.promenade.state.property;

import fr.hugman.promenade.block.MoaiType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public class PromenadeBlockProperties {
	public static final BooleanProperty NATURAL = BooleanProperty.of("natural");
	public static final BooleanProperty DRIP = BooleanProperty.of("drip");
	public static final EnumProperty<MoaiType> MOAI_TYPE = EnumProperty.of("type", MoaiType.class);

	public static final BooleanProperty SLOT_0_OPEN = BooleanProperty.of("slot_0_open");
	public static final BooleanProperty SLOT_1_OPEN = BooleanProperty.of("slot_1_open");
	public static final BooleanProperty SLOT_2_OPEN = BooleanProperty.of("slot_2_open");
	public static final BooleanProperty SLOT_3_OPEN = BooleanProperty.of("slot_3_open");
	public static final BooleanProperty SLOT_4_OPEN = BooleanProperty.of("slot_4_open");
	public static final BooleanProperty SLOT_5_OPEN = BooleanProperty.of("slot_5_open");
	public static final BooleanProperty SLOT_6_OPEN = BooleanProperty.of("slot_6_open");
	public static final BooleanProperty SLOT_7_OPEN = BooleanProperty.of("slot_7_open");
	public static final BooleanProperty SLOT_8_OPEN = BooleanProperty.of("slot_8_open");
}
