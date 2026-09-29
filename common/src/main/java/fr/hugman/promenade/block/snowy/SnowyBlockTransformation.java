package fr.hugman.promenade.block.snowy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public record SnowyBlockTransformation(Holder<Block> baseBlock, Holder<Block> snowyBlock) {
    public static final Codec<SnowyBlockTransformation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("base_block").forGetter(SnowyBlockTransformation::baseBlock),
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("snowy_block").forGetter(SnowyBlockTransformation::snowyBlock)
    ).apply(instance, SnowyBlockTransformation::new));
}
