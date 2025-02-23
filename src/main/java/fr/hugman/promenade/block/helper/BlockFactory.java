package fr.hugman.promenade.block.helper;

import fr.hugman.promenade.block.HangingLeavesBlock;
import fr.hugman.promenade.block.ParticleSnowyLeavesBlock;
import fr.hugman.promenade.block.PileBlock;
import fr.hugman.promenade.block.SnowyLeavesBlock;
import fr.hugman.promenade.sound.PromenadeBlockSounds;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Function;
import java.util.function.Predicate;

public final class BlockFactory {
    public static BlockBuilder of(Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return new BlockBuilder(factory, settings);
    }

    public static BlockBuilder of(AbstractBlock.Settings settings) {
        return new BlockBuilder(settings);
    }

    public static BlockBuilder copy(Block block) {
        return new BlockBuilder(block);
    }

    public static BlockBuilder slab(Block baseBlock) {
        return copy(baseBlock).factory(SlabBlock::new);
    }

    public static BlockBuilder stairs(Block baseBlock) {
        return copy(baseBlock).factory(s -> new StairsBlock(baseBlock.getDefaultState(), s));
    }

    public static BlockBuilder wall(Block baseBlock) {
        return copy(baseBlock).factory(WallBlock::new).settings(AbstractBlock.Settings.copyShallow(baseBlock).solid());
    }

    public static BlockBuilder fence(Block baseBlock) {
        return copy(baseBlock).factory(FenceBlock::new).settings(AbstractBlock.Settings.copyShallow(baseBlock));
    }

    public static BlockBuilder fenceGate(Block baseBlock, WoodType woodType) {
        return copy(baseBlock).factory(s -> new FenceGateBlock(woodType, s)).settings(AbstractBlock.Settings.copyShallow(baseBlock).solid());
    }

    public static BlockBuilder trapdoor(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new TrapdoorBlock(setType, s)).settings(AbstractBlock.Settings.copyShallow(baseBlock)
                .strength(3.0f)
                .nonOpaque()
                .allowsSpawning(Blocks::never));
    }

    public static BlockBuilder door(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new DoorBlock(setType, s)).settings(AbstractBlock.Settings.copyShallow(baseBlock)
                .strength(3.0f)
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY));
    }

    public static BlockBuilder woodenButton(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new ButtonBlock(setType, 30, s)).settings(AbstractBlock.Settings.create()
                .strength(0.5f)
                .noCollision()
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(baseBlock.getDefaultState().getSoundGroup()));
    }

    public static BlockBuilder pressurePlate(Block baseBlock, BlockSetType setType) {
        return copy(baseBlock).factory(s -> new PressurePlateBlock(setType, s)).settings(AbstractBlock.Settings.copyShallow(baseBlock)
                .solid()
                .strength(0.5f)
                .pistonBehavior(PistonBehavior.DESTROY)
                .requiresTool()
                .noCollision());
    }

    public static BlockBuilder sign(Block baseBlock, WoodType woodType) {
        return copy(baseBlock).factory(s -> new SignBlock(woodType, s)).settings(AbstractBlock.Settings.copyShallow(baseBlock)
                .mapColor(baseBlock.getDefaultMapColor())
                .solid()
                .noCollision()
                .strength(1.0F)).noItem();
    }

    public static BlockBuilder wallSign(Block standingSign, WoodType woodType) {
        return copy(standingSign).factory(s -> new WallSignBlock(woodType, s)).settings(AbstractBlock.Settings.copyShallow(standingSign)
                .lootTable(standingSign.getLootTableKey())
                .mapColor(standingSign.getDefaultMapColor())
                .solid()
                .noCollision()
                .strength(1.0F)).noItem();
    }

    public static BlockBuilder hangingSign(Block baseBlock, WoodType woodType, BlockSoundGroup soundGroup) {
        return copy(baseBlock).factory(s -> new HangingSignBlock(woodType, s)).settings(AbstractBlock.Settings.copyShallow(baseBlock)
                .mapColor(baseBlock.getDefaultMapColor())
                .solid()
                .noCollision()
                .strength(1.0F)
                .sounds(soundGroup)).noItem();
    }

    public static BlockBuilder wallHangingSign(Block standingSign, WoodType woodType, BlockSoundGroup soundGroup) {
        return copy(standingSign).factory(s -> new WallHangingSignBlock(woodType, s)).settings(AbstractBlock.Settings.copyShallow(standingSign)
                .lootTable(standingSign.getLootTableKey())
                .mapColor(standingSign.getDefaultMapColor())
                .solid()
                .noCollision()
                .strength(1.0F)
                .sounds(soundGroup)).noItem();
    }

    public static BlockBuilder log(MapColor woodColor, MapColor barkColor, BlockSoundGroup sounds, boolean flammable) {
        return log(woodColor, sounds, flammable)
                .settings(settings -> settings.mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor));
    }

    public static BlockBuilder log(MapColor color, BlockSoundGroup sounds, boolean flammable) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create()
                .mapColor(color)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sounds(sounds);
        if (flammable) settings.burnable();
        var builder = new BlockBuilder(PillarBlock::new, settings);
        return builder;
    }

    public static BlockBuilder sapling(MapColor mapColor, SaplingGenerator generator) {
        return new BlockBuilder(s -> new SaplingBlock(generator, s), AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .sounds(BlockSoundGroup.GRASS)
                .breakInstantly()
                .noCollision()
                .ticksRandomly()
                .pistonBehavior(PistonBehavior.DESTROY));
    }

    public static BlockBuilder sapling(MapColor mapColor, SaplingGenerator generator, Predicate<BlockState> saplingSoilPredicate) {
        return sapling(mapColor, generator).factory(settings -> new fr.hugman.promenade.block.SaplingBlock(generator, saplingSoilPredicate, settings));
    }

    public static BlockBuilder leaves(MapColor mapColor) {
        return new BlockBuilder(LeavesBlock::new, AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .strength(0.2f)
                .ticksRandomly()
                .sounds(BlockSoundGroup.GRASS)
                .nonOpaque()
                .allowsSpawning(Blocks::never)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never)
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::never));
    }

    public static BlockBuilder leaves(MapColor mapColor, BlockSoundGroup soundGroup, int chance, ParticleEffect fallingParticle) {
        return new BlockBuilder(s -> new ParticleLeavesBlock(chance, fallingParticle, s), AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .strength(0.2f)
                .ticksRandomly()
                .sounds(soundGroup)
                .nonOpaque()
                .burnable()
                .allowsSpawning(Blocks::never)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never)
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::never));
    }

    public static BlockBuilder hangingLeaves(MapColor mapColor) {
        return new BlockBuilder(HangingLeavesBlock::new, AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .sounds(BlockSoundGroup.GRASS)
                .replaceable().noCollision().breakInstantly()
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY));
    }

    public static BlockBuilder snowyLeaves() {
        return new BlockBuilder(SnowyLeavesBlock::new, AbstractBlock.Settings.create()
                .mapColor(MapColor.WHITE)
                .strength(0.2f)
                .ticksRandomly()
                .sounds(PromenadeBlockSounds.SNOWY_LEAVES)
                .nonOpaque()
                .burnable()
                .allowsSpawning(Blocks::canSpawnOnLeaves)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never)
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::never));
    }

    public static BlockBuilder snowyLeaves(BlockSoundGroup soundGroup) {
        return snowyLeaves().settings(settings -> settings.sounds(soundGroup));
    }

    public static BlockBuilder snowyLeaves(int chance, ParticleEffect particle) {
        return snowyLeaves().factory(s -> new ParticleSnowyLeavesBlock(chance, particle, s));
    }

    public static BlockBuilder snowyLeaves(int chance, ParticleEffect particle, BlockSoundGroup soundGroup) {
        return snowyLeaves(chance, particle).settings(settings -> settings.sounds(soundGroup));
    }

    public static BlockBuilder pot(Block block) {
        return new BlockBuilder(s -> new FlowerPotBlock(block, s), AbstractBlock.Settings.create()
                .breakInstantly()
                .nonOpaque()
                .luminance(state -> block.getDefaultState().getLuminance())
                .pistonBehavior(PistonBehavior.DESTROY));
    }

    public static BlockBuilder pile() {
        return pile(MapColor.DARK_GREEN);
    }

    public static BlockBuilder pile(MapColor color) {
        return pile(color, BlockSoundGroup.GRASS);
    }

    public static BlockBuilder pile(MapColor color, BlockSoundGroup sounds) {
        return new BlockBuilder(PileBlock::new, AbstractBlock.Settings.create()
                .mapColor(color)
                .burnable()
                .strength(0.1f)
                .ticksRandomly()
                .sounds(sounds)
                .noCollision()
                .nonOpaque());
    }

    public static BlockBuilder fungus(MapColor mapColor, RegistryKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn) {
        return of(s -> new fr.hugman.promenade.block.FungusBlock(featureKey, canPlantOn, canGrowOn, s), AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .sounds(BlockSoundGroup.FUNGUS)
                .pistonBehavior(PistonBehavior.DESTROY)
                .breakInstantly()
                .noCollision());
    }
}
