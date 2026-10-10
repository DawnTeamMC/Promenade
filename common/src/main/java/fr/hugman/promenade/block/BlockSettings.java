package fr.hugman.promenade.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public final class BlockSettings {
    public static BlockBehaviour.Properties rock() {
        return BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0f);
    }

    public static BlockBehaviour.Properties rock(MapColor mapColor, SoundType sounds) {
        return rock().mapColor(mapColor).sound(sounds);
    }

    public static BlockBehaviour.Properties planks(MapColor color, SoundType sounds, boolean flammable) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.of()
                .mapColor(color)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f)
                .sound(sounds);
        if (flammable) settings.ignitedByLava();
        return settings;
    }
}
