package fr.hugman.promenade.block.type;

import fr.hugman.promenade.Promenade;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class PromenadeWoodTypes {
    public static final WoodType SAKURA = copyOf(WoodType.CHERRY, "sakura", PromenadeBlockSetTypes.SAKURA);
    public static final WoodType MAPLE = copyOf(WoodType.OAK, "maple", PromenadeBlockSetTypes.MAPLE);
    public static final WoodType PALM = copyOf(WoodType.OAK, "palm", PromenadeBlockSetTypes.PALM);
    public static final WoodType DARK_AMARANTH = copyOf(WoodType.CRIMSON, "dark_amaranth", PromenadeBlockSetTypes.DARK_AMARANTH);

    /**
     * Registers a wood type that sounds like {@code base}. Its name is namespaced, so that its sign textures are looked up in Promenade's assets.
     */
    private static WoodType copyOf(WoodType base, String path, BlockSetType setType) {
        return WoodType.register(new WoodType(Promenade.id(path).toString(), setType, base.soundType(), base.hangingSignSoundType(), base.fenceGateClose(), base.fenceGateOpen()));
    }
}
