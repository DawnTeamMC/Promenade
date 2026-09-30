package fr.hugman.promenade.entity.spawn;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

public interface SpawnCondition extends PriorityProvider.SelectorCondition<SpawnContext> {
    Codec<SpawnCondition> CODEC = PromenadeSpawnConditions.TYPE_CODEC.dispatch(SpawnCondition::codec, codec -> codec);

    MapCodec<? extends SpawnCondition> codec();
}
