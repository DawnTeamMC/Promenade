package fr.hugman.promenade.state.property;

import fr.hugman.promenade.block.MoaiType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public class PromenadeBlockProperties {
	public static final BooleanProperty NATURAL = BooleanProperty.of("natural");
	public static final BooleanProperty DRIP = BooleanProperty.of("drip");
	public static final EnumProperty<MoaiType> MOAI_TYPE = EnumProperty.of("type", MoaiType.class);
	public static final BooleanProperty HAS_STARS = BooleanProperty.of("has_stars");
}
