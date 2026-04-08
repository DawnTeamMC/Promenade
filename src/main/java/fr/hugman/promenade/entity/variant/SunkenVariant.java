package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import java.util.List;
import net.minecraft.core.ClientAsset.ResourceTexture;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.variant.PriorityProvider;
import net.minecraft.world.entity.variant.SpawnCondition;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.level.storage.loot.LootTable;

public record SunkenVariant(
        ResourceTexture texture,
        ResourceKey<LootTable> lootTable,
        SpawnPrioritySelectors spawnConditions
) implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final Codec<SunkenVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(SunkenVariant::texture),
            ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("loot_table").forGetter(SunkenVariant::lootTable),
            SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(SunkenVariant::spawnConditions)
    ).apply(instance, SunkenVariant::new));

    public static final Codec<SunkenVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(SunkenVariant::texture),
            ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("loot_table").forGetter(SunkenVariant::lootTable)
    ).apply(instance, SunkenVariant::new));

    public static final Codec<Holder<SunkenVariant>> ENTRY_CODEC = RegistryFixedCodec.create(PromenadeRegistryKeys.SUNKEN_VARIANT);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<SunkenVariant>> ENTRY_PACKET_CODEC = ByteBufCodecs.holderRegistry(PromenadeRegistryKeys.SUNKEN_VARIANT);

    public SunkenVariant(ResourceTexture texture, ResourceKey<LootTable> lootTable) {
        this(texture, lootTable, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
    }
}
