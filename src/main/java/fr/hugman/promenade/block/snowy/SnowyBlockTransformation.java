package fr.hugman.promenade.block.snowy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

public record SnowyBlockTransformation(RegistryEntry<Block> baseBlock, RegistryEntry<Block> snowyBlock) {
    public static final Codec<SnowyBlockTransformation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registries.BLOCK.getEntryCodec().fieldOf("base_block").forGetter(SnowyBlockTransformation::baseBlock),
            Registries.BLOCK.getEntryCodec().fieldOf("snowy_block").forGetter(SnowyBlockTransformation::snowyBlock)
    ).apply(instance, SnowyBlockTransformation::new));
}
