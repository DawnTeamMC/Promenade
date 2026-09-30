package fr.hugman.promenade.entity.spawn;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;

public record ChanceSpawnCondition(float chance) implements SpawnCondition {
    public static final MapCodec<ChanceSpawnCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter(ChanceSpawnCondition::chance)
    ).apply(instance, ChanceSpawnCondition::new));

    @Override
    public boolean test(SpawnContext spawnContext) {
        return spawnContext.level().getRandom().nextFloat() < this.chance;
    }

    @Override
    public MapCodec<ChanceSpawnCondition> codec() {
        return CODEC;
    }
}
