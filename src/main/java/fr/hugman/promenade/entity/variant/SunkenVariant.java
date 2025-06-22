package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.loot.LootTable;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.util.AssetInfo;

import java.util.List;

public record SunkenVariant(
        AssetInfo texture,
        RegistryKey<LootTable> lootTable,
        SpawnConditionSelectors spawnConditions
) implements VariantSelectorProvider<SpawnContext, SpawnCondition> {
    public static final Codec<SunkenVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.MAP_CODEC.forGetter(SunkenVariant::texture),
            RegistryKey.createCodec(RegistryKeys.LOOT_TABLE).fieldOf("loot_table").forGetter(SunkenVariant::lootTable),
            SpawnConditionSelectors.CODEC.fieldOf("spawn_conditions").forGetter(SunkenVariant::spawnConditions)
    ).apply(instance, SunkenVariant::new));

    public static final Codec<SunkenVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.MAP_CODEC.forGetter(SunkenVariant::texture),
            RegistryKey.createCodec(RegistryKeys.LOOT_TABLE).fieldOf("loot_table").forGetter(SunkenVariant::lootTable)
    ).apply(instance, SunkenVariant::new));

    public static final Codec<RegistryEntry<SunkenVariant>> ENTRY_CODEC = RegistryFixedCodec.of(PromenadeRegistryKeys.SUNKEN_VARIANT);
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<SunkenVariant>> ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(PromenadeRegistryKeys.SUNKEN_VARIANT);

    public SunkenVariant(AssetInfo texture, RegistryKey<LootTable> lootTable) {
        this(texture, lootTable, SpawnConditionSelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> getSelectors() {
        return this.spawnConditions.selectors();
    }
}
