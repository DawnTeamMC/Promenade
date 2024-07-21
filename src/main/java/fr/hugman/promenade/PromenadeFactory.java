package fr.hugman.promenade;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.promenade.block.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class PromenadeFactory {
    public static Block leafPile() {
        return leafPile(MapColor.DARK_GREEN, BlockSoundGroup.GRASS);
    }

    public static Block leafPile(MapColor mapColor) {
        return leafPile(mapColor, BlockSoundGroup.GRASS);
    }

    public static Block leafPile(BlockSoundGroup soundGroup) {
        return leafPile(MapColor.DARK_GREEN, soundGroup);
    }

    public static Block leafPile(MapColor mapColor, BlockSoundGroup soundGroup) {
        return new PileBlock(AbstractBlock.Settings.create()
                .item(new Item.Settings().compostingChance(0.3f))
                .mapColor(mapColor)
                .burnable(30, 60)
                .strength(0.1f)
                .ticksRandomly()
                .sounds(soundGroup)
                .noCollision()
                .nonOpaque());
    }

    public static Block carpetedGrassBlock(MapColor color) {
        return new CarpetedGrassBlock(AbstractBlock.Settings.create()
                .item(new Item.Settings().compostingChance(0.3f))
                .mapColor(color)
                .ticksRandomly()
                .strength(0.6F)
                .sounds(BlockSoundGroup.GRASS));
    }

    public static SnowyLeavesBlock snowyLeaves() {
        return snowyLeaves(PromenadeBlocks.SNOWY_LEAVES_SOUNDS);
    }

    public static SnowyLeavesBlock snowyLeaves(BlockSoundGroup soundGroup) {
        return new SnowyLeavesBlock(AbstractBlock.Settings.create()
                .item(new Item.Settings().compostingChance(0.3f))
                .mapColor(MapColor.WHITE)
                .strength(0.2f)
                .ticksRandomly()
                .sounds(soundGroup)
                .nonOpaque()
                .allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
                .suffocates((state, world, pos) -> false)
                .blockVision((state, world, pos) -> false)
                .burnable(30, 60)
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock((state, world, pos) -> false));
    }

    public static DecoratedLeavesBlock decoratedLeaves(MapColor mapColor, BlockSoundGroup soundGroup, int bound, ParticleEffect particle) {
        return new DecoratedLeavesBlock(DawnFactory.leavesSettings(mapColor, soundGroup), bound, particle);
    }

    public static DecoratedLeavesBlock decoratedLeaves(MapColor mapColor, BlockSoundGroup soundGroup, ParticleEffect particle) {
        return decoratedLeaves(mapColor, soundGroup, 10, particle);
    }

    public static DecoratedLeavesBlock decoratedLeaves(MapColor mapColor, int bound, ParticleEffect particle) {
        return decoratedLeaves(mapColor, BlockSoundGroup.GRASS, bound, particle);
    }

    public static DecoratedLeavesBlock decoratedLeaves(MapColor mapColor, ParticleEffect particle) {
        return decoratedLeaves(mapColor, BlockSoundGroup.GRASS, 10, particle);
    }
}
