package fr.hugman.promenade.data;

import com.google.common.collect.Maps;
import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;

import java.util.Map;
import java.util.stream.Stream;

public class PromenadeBlockFamilies {
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

    /**
     * The group used for the recipes of wooden block families.
     */
    private static final String WOODEN_GROUP = "wooden";
    /**
     * The name of the criterion used for the recipe unlock advancements of wooden block families.
     */
    private static final String WOODEN_UNLOCK_CRITERION_NAME = "has_planks";

    public static final BlockFamily ASPHALT = register(PromenadeBlocks.ASPHALT)
            .wall(PromenadeBlocks.ASPHALT_WALL)
            .stairs(PromenadeBlocks.ASPHALT_STAIRS)
            .slab(PromenadeBlocks.ASPHALT_SLAB)
            .polished(PromenadeBlocks.POLISHED_ASPHALT)
            .build();
    public static final BlockFamily POLISHED_ASPHALT = register(PromenadeBlocks.POLISHED_ASPHALT)
            .stairs(PromenadeBlocks.POLISHED_ASPHALT_STAIRS)
            .slab(PromenadeBlocks.POLISHED_ASPHALT_SLAB)
            .build();

    public static final BlockFamily BLUNITE = register(PromenadeBlocks.BLUNITE)
            .wall(PromenadeBlocks.BLUNITE_WALL)
            .stairs(PromenadeBlocks.BLUNITE_STAIRS)
            .slab(PromenadeBlocks.BLUNITE_SLAB)
            .polished(PromenadeBlocks.POLISHED_BLUNITE)
            .build();

    public static final BlockFamily POLISHED_BLUNITE = register(PromenadeBlocks.POLISHED_BLUNITE)
            .stairs(PromenadeBlocks.POLISHED_BLUNITE_STAIRS)
            .slab(PromenadeBlocks.POLISHED_BLUNITE_SLAB)
            .build();

    public static final BlockFamily SAKURA = register(PromenadeBlocks.SAKURA_PLANKS)
            .button(PromenadeBlocks.SAKURA_BUTTON)
            .fence(PromenadeBlocks.SAKURA_FENCE)
            .fenceGate(PromenadeBlocks.SAKURA_FENCE_GATE)
            .pressurePlate(PromenadeBlocks.SAKURA_PRESSURE_PLATE)
            .sign(PromenadeBlocks.SAKURA_SIGN, PromenadeBlocks.SAKURA_WALL_SIGN)
            .slab(PromenadeBlocks.SAKURA_SLAB)
            .stairs(PromenadeBlocks.SAKURA_STAIRS)
            .door(PromenadeBlocks.SAKURA_DOOR)
            .trapdoor(PromenadeBlocks.SAKURA_TRAPDOOR)
            .group(WOODEN_GROUP)
            .unlockCriterionName(WOODEN_UNLOCK_CRITERION_NAME)
            .build();

    public static final BlockFamily MAPLE = register(PromenadeBlocks.MAPLE_PLANKS)
            .button(PromenadeBlocks.MAPLE_BUTTON)
            .fence(PromenadeBlocks.MAPLE_FENCE)
            .fenceGate(PromenadeBlocks.MAPLE_FENCE_GATE)
            .pressurePlate(PromenadeBlocks.MAPLE_PRESSURE_PLATE)
            .sign(PromenadeBlocks.MAPLE_SIGN, PromenadeBlocks.MAPLE_WALL_SIGN)
            .slab(PromenadeBlocks.MAPLE_SLAB)
            .stairs(PromenadeBlocks.MAPLE_STAIRS)
            .door(PromenadeBlocks.MAPLE_DOOR)
            .trapdoor(PromenadeBlocks.MAPLE_TRAPDOOR)
            .group(WOODEN_GROUP)
            .unlockCriterionName(WOODEN_UNLOCK_CRITERION_NAME)
            .build();

    public static final BlockFamily PALM = register(PromenadeBlocks.PALM_PLANKS)
            .button(PromenadeBlocks.PALM_BUTTON)
            .fence(PromenadeBlocks.PALM_FENCE)
            .fenceGate(PromenadeBlocks.PALM_FENCE_GATE)
            .pressurePlate(PromenadeBlocks.PALM_PRESSURE_PLATE)
            .sign(PromenadeBlocks.PALM_SIGN, PromenadeBlocks.PALM_WALL_SIGN)
            .slab(PromenadeBlocks.PALM_SLAB)
            .stairs(PromenadeBlocks.PALM_STAIRS)
            .door(PromenadeBlocks.PALM_DOOR)
            .trapdoor(PromenadeBlocks.PALM_TRAPDOOR)
            .group(WOODEN_GROUP)
            .unlockCriterionName(WOODEN_UNLOCK_CRITERION_NAME)
            .build();

    public static final BlockFamily AURORAL_CYPRESS = register(PromenadeBlocks.AURORAL_CYPRESS_PLANKS)
            .button(PromenadeBlocks.AURORAL_CYPRESS_BUTTON)
            .fence(PromenadeBlocks.AURORAL_CYPRESS_FENCE)
            .fenceGate(PromenadeBlocks.AURORAL_CYPRESS_FENCE_GATE)
            .pressurePlate(PromenadeBlocks.AURORAL_CYPRESS_PRESSURE_PLATE)
            .sign(PromenadeBlocks.AURORAL_CYPRESS_SIGN, PromenadeBlocks.AURORAL_CYPRESS_WALL_SIGN)
            .slab(PromenadeBlocks.AURORAL_CYPRESS_SLAB)
            .stairs(PromenadeBlocks.AURORAL_CYPRESS_STAIRS)
            .door(PromenadeBlocks.AURORAL_CYPRESS_DOOR)
            .trapdoor(PromenadeBlocks.AURORAL_CYPRESS_TRAPDOOR)
            .group(WOODEN_GROUP)
            .unlockCriterionName(WOODEN_UNLOCK_CRITERION_NAME)
            .build();

    public static final BlockFamily DARK_AMARANTH = register(PromenadeBlocks.DARK_AMARANTH_PLANKS)
            .button(PromenadeBlocks.DARK_AMARANTH_BUTTON)
            .fence(PromenadeBlocks.DARK_AMARANTH_FENCE)
            .fenceGate(PromenadeBlocks.DARK_AMARANTH_FENCE_GATE)
            .pressurePlate(PromenadeBlocks.DARK_AMARANTH_PRESSURE_PLATE)
            .sign(PromenadeBlocks.DARK_AMARANTH_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_SIGN)
            .slab(PromenadeBlocks.DARK_AMARANTH_SLAB)
            .stairs(PromenadeBlocks.DARK_AMARANTH_STAIRS)
            .door(PromenadeBlocks.DARK_AMARANTH_DOOR)
            .trapdoor(PromenadeBlocks.DARK_AMARANTH_TRAPDOOR)
            .group(WOODEN_GROUP)
            .unlockCriterionName(WOODEN_UNLOCK_CRITERION_NAME)
            .build();


    public static BlockFamily.Builder register(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + Registries.BLOCK.getId(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }
}
