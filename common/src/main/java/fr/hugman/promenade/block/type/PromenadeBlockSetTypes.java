package fr.hugman.promenade.block.type;

import fr.hugman.promenade.Promenade;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class PromenadeBlockSetTypes {
    public static final BlockSetType SAKURA = copyOf(BlockSetType.CHERRY, "sakura");
    public static final BlockSetType MAPLE = copyOf(BlockSetType.OAK, "maple");
    public static final BlockSetType PALM = copyOf(BlockSetType.OAK, "palm");
    public static final BlockSetType DARK_AMARANTH = copyOf(BlockSetType.CRIMSON, "dark_amaranth");

    /**
     * Registers a block set type that behaves and sounds like {@code base}, under a namespaced name.
     */
    private static BlockSetType copyOf(BlockSetType base, String path) {
        return BlockSetType.register(new BlockSetType(Promenade.id(path).toString(),
                base.canOpenByHand(), base.canOpenByWindCharge(), base.canButtonBeActivatedByArrows(), base.pressurePlateSensitivity(), base.soundType(),
                base.doorClose(), base.doorOpen(), base.trapdoorClose(), base.trapdoorOpen(),
                base.pressurePlateClickOff(), base.pressurePlateClickOn(), base.buttonClickOff(), base.buttonClickOn()));
    }
}
