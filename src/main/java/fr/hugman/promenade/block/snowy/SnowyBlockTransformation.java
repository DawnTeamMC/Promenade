package fr.hugman.promenade.block.snowy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;

public record SnowyBlockTransformation(RegistryEntryList<Block> baseBlocks, RegistryEntry<Block> snowyBlock) {
    public static final Codec<SnowyBlockTransformation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("base_blocks").forGetter(SnowyBlockTransformation::baseBlocks),
            Registries.BLOCK.getEntryCodec().fieldOf("snowy_block").forGetter(SnowyBlockTransformation::snowyBlock)
    ).apply(instance, SnowyBlockTransformation::new));

    public SnowyBlockTransformation(RegistryEntry<Block> baseBlock, RegistryEntry<Block> snowyBlock) {
        this(RegistryEntryList.of(baseBlock), snowyBlock);
    }
}
