package fr.hugman.promenade.entity.spawn;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;

public record StructureCheck(HolderSet<Structure> requiredStructures) implements SpawnCondition {
    public static final MapCodec<StructureCheck> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RegistryCodecs.homogeneousList(Registries.STRUCTURE).fieldOf("structures").forGetter(StructureCheck::requiredStructures)
    ).apply(instance, StructureCheck::new));

    @Override
    public boolean test(SpawnContext context) {
        var structureManager = context.level().getLevel().structureManager();
        return this.requiredStructures.stream().anyMatch(structure -> structureManager.getStructureWithPieceAt(context.pos(), structure.value()).isValid());
    }

    @Override
    public MapCodec<StructureCheck> codec() {
        return CODEC;
    }
}
