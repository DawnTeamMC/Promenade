package fr.hugman.promenade.block.sparkler;

import net.minecraft.block.Block;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.entry.RegistryEntryList;

public record SparklerBehavior(ParticleEffect particle, double strength, RegistryEntryList<Block> blocks) {
    public SparklerBehavior(ParticleEffect particle, double strength, Block... blocks) {
        this(particle, strength, RegistryEntryList.of(Block::getRegistryEntry, blocks));
    }
}
