package com.hugman.newdawn;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * Dawn's version of {@code Block.Settings}. Adds additional methods and hooks
 * not found in the original class, on top of {@code FabricBlockSettings}.
 *
 * <p>Make note that this behaves slightly different from the
 * vanilla counterpart, copying some settings that vanilla or Fabric does not.
 *
 * <p>To use it, simply replace {@code Block.Settings.of()} or {@code FabricBlockSettings.of()} with
 * {@code DawnBlockSettings.of()}.
 */
public class DawnBlockSettings extends FabricBlockSettings {
	private int flameBurn;
	private int flameSpread;
	@Nullable
	private Block stripInto;
	@Nullable
	private Item.Settings itemSettings;

	protected DawnBlockSettings(Material material, MapColor color) {
		super(material, color);
	}

	protected DawnBlockSettings(AbstractBlock.Settings settings) {
		super(settings);
		if(settings instanceof DawnBlockSettings dawnSettings) {
			this.flameBurn = dawnSettings.flameBurn;
			this.flameSpread = dawnSettings.flameSpread;
			this.stripInto = dawnSettings.stripInto;
			this.itemSettings = dawnSettings.itemSettings;
		}
	}

	// Getters

	public int getFlameBurn() {
		return flameBurn;
	}

	public int getFlameSpread() {
		return flameSpread;
	}

	@Nullable
	public Block getStripInto() {
		return stripInto;
	}

	@Nullable
	public Item.Settings getItemSettings() {
		return itemSettings;
	}

	// Factory methods

	public static DawnBlockSettings of(Material material) {
		return of(material, material.getColor());
	}

	public static DawnBlockSettings of(Material material, MapColor color) {
		return new DawnBlockSettings(material, color);
	}

	public static DawnBlockSettings of(Material material, DyeColor color) {
		return new DawnBlockSettings(material, color.getMapColor());
	}

	public static DawnBlockSettings of(Material material, Function<BlockState, MapColor> mapColor) {
		return new DawnBlockSettings(AbstractBlock.Settings.of(material, mapColor));
	}

	public static DawnBlockSettings copy(AbstractBlock block) {
		return new DawnBlockSettings(((AbstractBlockAccessor) block).getSettings());
	}

	public static DawnBlockSettings copyOf(AbstractBlock block) {
		return copy(block);
	}

	public static DawnBlockSettings copyOf(AbstractBlock.Settings settings) {
		return new DawnBlockSettings(settings);
	}

	// New methods

	// TODO: add more javadocs

	/**
	 * @see FireBlock#registerDefaultFlammables() Vanilla flammability values
	 */
	public DawnBlockSettings flammability(int burn, int spread) {
		this.flameBurn = burn;
		this.flameSpread = spread;
		return this;
	}

	// TODO: add more javadocs

	/**
	 * @see AxeItem Vanilla axe stripping values
	 */
	public DawnBlockSettings stripInto(Block block) {
		this.stripInto = block;
		return this;
	}

	/**
	 * Provides item settings for a {@code BlockItem} to be registered with this block.
	 * <p>The item will be registered with the same identifier as the block.
	 */
	public DawnBlockSettings item(Item.Settings itemSettings) {
		this.itemSettings = itemSettings;
		return this;
	}

	public DawnBlockSettings item(ItemGroup group) {
		return this.item(new Item.Settings().group(group));
	}

	// Overrides of vanilla and Fabric methods

	@Override
	public DawnBlockSettings noCollision() {
		super.noCollision();
		return this;
	}

	@Override
	public DawnBlockSettings nonOpaque() {
		super.nonOpaque();
		return this;
	}

	@Override
	public DawnBlockSettings slipperiness(float value) {
		super.slipperiness(value);
		return this;
	}

	@Override
	public DawnBlockSettings velocityMultiplier(float velocityMultiplier) {
		super.velocityMultiplier(velocityMultiplier);
		return this;
	}

	@Override
	public DawnBlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
		super.jumpVelocityMultiplier(jumpVelocityMultiplier);
		return this;
	}

	@Override
	public DawnBlockSettings sounds(BlockSoundGroup group) {
		super.sounds(group);
		return this;
	}

	@Override
	public DawnBlockSettings luminance(ToIntFunction<BlockState> luminanceFunction) {
		super.luminance(luminanceFunction);
		return this;
	}

	@Override
	public DawnBlockSettings strength(float hardness, float resistance) {
		super.strength(hardness, resistance);
		return this;
	}

	@Override
	public DawnBlockSettings breakInstantly() {
		super.breakInstantly();
		return this;
	}

	public DawnBlockSettings strength(float strength) {
		super.strength(strength);
		return this;
	}

	@Override
	public DawnBlockSettings ticksRandomly() {
		super.ticksRandomly();
		return this;
	}

	@Override
	public DawnBlockSettings dynamicBounds() {
		super.dynamicBounds();
		return this;
	}

	@Override
	public DawnBlockSettings dropsLike(Block block) {
		super.dropsLike(block);
		return this;
	}

	@Override
	public DawnBlockSettings air() {
		super.air();
		return this;
	}

	@Override
	public DawnBlockSettings allowsSpawning(AbstractBlock.TypedContextPredicate<EntityType<?>> predicate) {
		super.allowsSpawning(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings solidBlock(AbstractBlock.ContextPredicate predicate) {
		super.solidBlock(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings suffocates(AbstractBlock.ContextPredicate predicate) {
		super.suffocates(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings blockVision(AbstractBlock.ContextPredicate predicate) {
		super.blockVision(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings postProcess(AbstractBlock.ContextPredicate predicate) {
		super.postProcess(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings emissiveLighting(AbstractBlock.ContextPredicate predicate) {
		super.emissiveLighting(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings dropsNothing() {
		super.dropsNothing();
		return this;
	}

	@Override
	public DawnBlockSettings offsetType(AbstractBlock.OffsetType offsetType) {
		super.offsetType(offsetType);
		return this;
	}

	@Override
	public DawnBlockSettings offsetType(Function<BlockState, AbstractBlock.OffsetType> offsetType) {
		super.offsetType(offsetType);
		return this;
	}

	@Override
	public DawnBlockSettings luminance(int luminance) {
		super.luminance(luminance);
		return this;
	}

	@Override
	public DawnBlockSettings hardness(float hardness) {
		super.hardness(hardness);
		return this;
	}

	@Override
	public DawnBlockSettings resistance(float resistance) {
		super.resistance(resistance);
		return this;
	}

	@Override
	public DawnBlockSettings drops(Identifier dropTableId) {
		super.drops(dropTableId);
		return this;
	}

	@Override
	public DawnBlockSettings requiresTool() {
		super.requiresTool();
		return this;
	}

	@Override
	public DawnBlockSettings mapColor(MapColor color) {
		super.mapColor(color);
		return this;
	}

	@Override
	public DawnBlockSettings mapColor(DyeColor color) {
		super.mapColor(color);
		return this;
	}

	@Override
	public DawnBlockSettings collidable(boolean collidable) {
		super.collidable(collidable);
		return this;
	}
}
