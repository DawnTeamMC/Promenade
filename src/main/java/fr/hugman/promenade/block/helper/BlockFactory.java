package fr.hugman.promenade.block.helper;

import fr.hugman.promenade.block.HangingLeavesBlock;
import fr.hugman.promenade.block.PileBlock;
import fr.hugman.promenade.block.TintedParticleSnowyLeavesBlock;
import fr.hugman.promenade.block.UntintedParticleSnowyLeavesBlock;
import fr.hugman.promenade.sound.PromenadeBlockSounds;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeafLitterBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.function.Function;
import java.util.function.Predicate;

public final class BlockFactory {
    public static BlockBuilder of(Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return new BlockBuilder(factory, settings);
    }

    public static BlockBuilder of(BlockBehaviour.Properties settings) {
        return new BlockBuilder(settings);
    }

    public static BlockBuilder copy(Block block) {
        return new BlockBuilder(block);
    }

    public static BlockBuilder slab(Block baseBlock) {
        return copy(baseBlock).factory(SlabBlock::new);
    }

    public static BlockBuilder stairs(Block baseBlock) {
        return copy(baseBlock).factory(s -> new StairBlock(baseBlock.defaultBlockState(), s));
    }

    public static BlockBuilder wall(Block baseBlock) {
        return copy(baseBlock).factory(WallBlock::new).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock).forceSolidOn());
    }

    public static BlockBuilder fence(Block baseBlock) {
        return copy(baseBlock).factory(FenceBlock::new).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock));
    }

    public static BlockBuilder fenceGate(Block baseBlock, WoodType woodType) {
        return copy(baseBlock).factory(s -> new FenceGateBlock(woodType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock).forceSolidOn());
    }

    public static BlockBuilder trapdoor(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new TrapDoorBlock(setType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock)
                .strength(3.0f)
                .noOcclusion()
                .isValidSpawn(Blocks::never));
    }

    public static BlockBuilder door(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new DoorBlock(setType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock)
                .strength(3.0f)
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY));
    }

    public static BlockBuilder woodenButton(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new ButtonBlock(setType, 30, s)).settings(BlockBehaviour.Properties.of()
                .strength(0.5f)
                .noCollision()
                .pushReaction(PushReaction.DESTROY)
                .sound(baseBlock.defaultBlockState().getSoundType()));
    }

    public static BlockBuilder pressurePlate(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new PressurePlateBlock(setType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock)
                .forceSolidOn()
                .strength(0.5f)
                .pushReaction(PushReaction.DESTROY)
                .requiresCorrectToolForDrops()
                .noCollision());
    }

    public static BlockBuilder sign(Block baseBlock, WoodType woodType) {
        return copy(baseBlock).factory(s -> new StandingSignBlock(woodType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock)
                .mapColor(baseBlock.defaultMapColor())
                .forceSolidOn()
                .noCollision()
                .strength(1.0F)).noItem();
    }

    public static BlockBuilder wallSign(Block standingSign, WoodType woodType) {
        return copy(standingSign).factory(s -> new WallSignBlock(woodType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(standingSign)
                .overrideLootTable(standingSign.getLootTable())
                .mapColor(standingSign.defaultMapColor())
                .forceSolidOn()
                .noCollision()
                .strength(1.0F)).noItem();
    }

    public static BlockBuilder hangingSign(Block baseBlock, WoodType woodType, SoundType soundGroup) {
        return copy(baseBlock).factory(s -> new CeilingHangingSignBlock(woodType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(baseBlock)
                .mapColor(baseBlock.defaultMapColor())
                .forceSolidOn()
                .noCollision()
                .strength(1.0F)
                .sound(soundGroup)).noItem();
    }

    public static BlockBuilder wallHangingSign(Block standingSign, WoodType woodType, SoundType soundGroup) {
        return copy(standingSign).factory(s -> new WallHangingSignBlock(woodType, s)).settings(BlockBehaviour.Properties.ofLegacyCopy(standingSign)
                .overrideLootTable(standingSign.getLootTable())
                .mapColor(standingSign.defaultMapColor())
                .forceSolidOn()
                .noCollision()
                .strength(1.0F)
                .sound(soundGroup)).noItem();
    }

    public static BlockBuilder shelf(Block planks) {
        return copy(planks).factory(ShelfBlock::new).settings(BlockBehaviour.Properties.ofLegacyCopy(planks).sound(SoundType.SHELF)).itemSettings((s -> s.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)));
    }

    public static BlockBuilder log(MapColor woodColor, MapColor barkColor, SoundType sounds, boolean flammable) {
        return log(woodColor, sounds, flammable)
                .settings(settings -> settings.mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor));
    }

    public static BlockBuilder log(MapColor color, SoundType sounds, boolean flammable) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.of()
                .mapColor(color)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(sounds);
        if (flammable) settings.ignitedByLava();
        var builder = new BlockBuilder(RotatedPillarBlock::new, settings);
        return builder;
    }

    public static BlockBuilder sapling(MapColor mapColor, TreeGrower generator) {
        return new BlockBuilder(s -> new SaplingBlock(generator, s), BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .sound(SoundType.GRASS)
                .instabreak()
                .noCollision()
                .randomTicks()
                .pushReaction(PushReaction.DESTROY));
    }

    public static BlockBuilder sapling(MapColor mapColor, TreeGrower generator, Predicate<BlockState> saplingSoilPredicate) {
        return sapling(mapColor, generator).factory(settings -> new fr.hugman.promenade.block.SaplingBlock(generator, saplingSoilPredicate, settings));
    }

    public static BlockBuilder leaves(MapColor mapColor) {
        return new BlockBuilder(s -> new TintedParticleLeavesBlock(0.01f, s), BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .strength(0.2f)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .isValidSpawn(Blocks::never)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(Blocks::never));
    }

    public static BlockBuilder leaves(MapColor mapColor, SoundType soundGroup, float particleChance, ParticleOptions fallingParticle) {
        return new BlockBuilder(s -> new UntintedParticleLeavesBlock(particleChance, fallingParticle, s), BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .strength(0.2f)
                .randomTicks()
                .sound(soundGroup)
                .noOcclusion()
                .ignitedByLava()
                .isValidSpawn(Blocks::never)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(Blocks::never));
    }

    public static BlockBuilder hangingLeaves(MapColor mapColor) {
        return new BlockBuilder(HangingLeavesBlock::new, BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .sound(SoundType.GRASS)
                .replaceable().noCollision().instabreak()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY));
    }

    public static BlockBuilder snowyLeaves() {
        return new BlockBuilder(s -> new TintedParticleSnowyLeavesBlock(0.01f, s), BlockBehaviour.Properties.of()
                .mapColor(MapColor.SNOW)
                .strength(0.2f)
                .randomTicks()
                .sound(PromenadeBlockSounds.SNOWY_LEAVES)
                .noOcclusion()
                .ignitedByLava()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(Blocks::never));
    }

    public static BlockBuilder snowyLeaves(SoundType soundGroup) {
        return snowyLeaves().settings(settings -> settings.sound(soundGroup));
    }

    public static BlockBuilder snowyLeaves(float particleChance, ParticleOptions particle) {
        return snowyLeaves().factory(s -> new UntintedParticleSnowyLeavesBlock(particleChance, particle, s));
    }

    public static BlockBuilder snowyLeaves(float particleChance, ParticleOptions particle, SoundType soundGroup) {
        return snowyLeaves(particleChance, particle).settings(settings -> settings.sound(soundGroup));
    }

    public static BlockBuilder pot(Block block) {
        return new BlockBuilder(s -> new FlowerPotBlock(block, s), BlockBehaviour.Properties.of()
                .instabreak()
                .noOcclusion()
                .lightLevel(state -> block.defaultBlockState().getLightEmission())
                .pushReaction(PushReaction.DESTROY));
    }

    public static BlockBuilder pile() {
        return pile(MapColor.PLANT);
    }

    public static BlockBuilder pile(MapColor color) {
        return pile(color, SoundType.GRASS);
    }

    public static BlockBuilder pile(MapColor color, SoundType sounds) {
        return new BlockBuilder(PileBlock::new, BlockBehaviour.Properties.of()
                .mapColor(color)
                .ignitedByLava()
                .strength(0.1f)
                .sound(sounds)
                .noCollision()
                .noOcclusion());
    }

    public static BlockBuilder fallenLeaves(MapColor color) {
        return fallenLeaves(color, SoundType.LEAF_LITTER);
    }

    public static BlockBuilder fallenLeaves(MapColor color, SoundType sounds) {
        return new BlockBuilder(LeafLitterBlock::new, BlockBehaviour.Properties.of()
                .mapColor(color)
                .replaceable()
                .noCollision()
                .sound(sounds)
                .pushReaction(PushReaction.DESTROY)
        );
    }

    public static BlockBuilder fungus(MapColor mapColor, ResourceKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn) {
        return of(s -> new fr.hugman.promenade.block.FungusBlock(featureKey, canPlantOn, canGrowOn, s), BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .sound(SoundType.FUNGUS)
                .pushReaction(PushReaction.DESTROY)
                .instabreak()
                .noCollision());
    }
}
