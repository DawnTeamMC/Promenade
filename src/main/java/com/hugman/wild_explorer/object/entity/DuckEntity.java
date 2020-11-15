package com.hugman.wild_explorer.object.entity;

import com.hugman.wild_explorer.init.WEEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class DuckEntity extends AnimalEntity {
	private static final TrackedData<Integer> DUCK_TYPE = DataTracker.registerData(DuckEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.ofItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;

	public DuckEntity(EntityType<? extends DuckEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
	}

	public static Builder createDuckAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
	}

	public static List<RegistryKey<Biome>> getSpawnBiomes() {
		ArrayList<RegistryKey<Biome>> biomeList = new ArrayList<>();
		for(DuckEntity.Type type : Type.typeList) {
			biomeList.addAll(type.getSpawnBiomes());
		}
		return biomeList.stream().distinct().collect(Collectors.toList());
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(DUCK_TYPE, 0);
	}

	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
		Optional<RegistryKey<Biome>> biome = world.method_31081(this.getBlockPos());
		DuckEntity.Type type = DuckEntity.Type.fromBiome(biome);
		if(entityData instanceof DuckEntity.DuckData) {
			type = ((DuckEntity.DuckData) entityData).type;
		}
		else {
			entityData = new DuckEntity.DuckData(type);
		}
		this.setVariant(type);
		return super.initialize(world, difficulty, spawnReason, entityData, entityTag);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4D));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(3, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(7, new LookAroundGoal(this));
	}

	@Override
	public void writeCustomDataToTag(CompoundTag compound) {
		super.writeCustomDataToTag(compound);
		compound.putString("Type", this.getVariant().getName());
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compound) {
		super.readCustomDataFromTag(compound);
		this.setVariant(DuckEntity.Type.byName(compound.getString("Type")));
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size) {
		return this.isBaby() ? size.height * 0.85F : size.height * 0.92F;
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
		if(!this.onGround && this.wingRotDelta < 0.55F) {
			this.wingRotDelta = 0.55F;
		}
		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);
		Vec3d vec3d = this.getVelocity();
		if(!this.onGround && vec3d.y < 0.0D) {
			this.setVelocity(vec3d.multiply(1.0D, 0.75D, 1.0D));
		}
		this.wingRotation += this.wingRotDelta * 2.0F;
	}

	@Override
	public boolean handleFallDamage(float distance, float multiplier) {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return WEEntities.DUCK_AMBIENT_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return WEEntities.DUCK_HURT_SOUND;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return WEEntities.DUCK_DEATH_SOUND;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(WEEntities.DUCK_STEP_SOUND, 0.15F, 1.0F);
	}

	@Nullable
	@Override
	public DuckEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
		DuckEntity child = WEEntities.DUCK.create(this.world);
		child.setVariant(this.random.nextFloat() < 0.5f ? ((DuckEntity) (mate)).getVariant() : this.getVariant());
		return child;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}

	@Override
	public void updatePassengerPosition(Entity entity) {
		super.updatePassengerPosition(entity);
		float f = MathHelper.sin(this.bodyYaw * ((float) Math.PI / 180F));
		float f1 = MathHelper.cos(this.bodyYaw * ((float) Math.PI / 180F));
		entity.updatePosition(this.getX() + (double) (0.1F * f), this.getBodyY(0.5D) + entity.getHeightOffset() + 0.0D, this.getZ() - (double) (0.1F * f1));
		if(entity instanceof LivingEntity) {
			((LivingEntity) entity).bodyYaw = this.bodyYaw;
		}
	}

	public DuckEntity.Type getVariant() {
		return DuckEntity.Type.fromId(this.dataTracker.get(DUCK_TYPE));
	}

	private void setVariant(DuckEntity.Type type) {
		this.dataTracker.set(DUCK_TYPE, type.getIndex());
	}

	public enum Type {
		PEKIN(0, "pekin", BiomeKeys.PLAINS, BiomeKeys.FOREST),
		MALLARD(1, "mallard", BiomeKeys.PLAINS, BiomeKeys.RIVER, BiomeKeys.SWAMP);

		private static final DuckEntity.Type[] typeList = Arrays.stream(values()).sorted(Comparator.comparingInt(DuckEntity.Type::getIndex)).toArray(Type[]::new);
		private static final Map<String, DuckEntity.Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(DuckEntity.Type::getName, (type) -> type));
		private final int index;
		private final String name;
		private final List<RegistryKey<Biome>> spawnBiomes;

		Type(int indexIn, String nameIn, RegistryKey<Biome>... spawnBiomesIn) {
			this.index = indexIn;
			this.name = nameIn;
			this.spawnBiomes = Arrays.asList(spawnBiomesIn);
		}

		public static DuckEntity.Type byName(String name) {
			return TYPES_BY_NAME.getOrDefault(name, PEKIN);
		}

		public static DuckEntity.Type fromId(int index) {
			if(index < 0 || index > typeList.length) {
				index = 0;
			}
			return typeList[index];
		}

		public static DuckEntity.Type fromBiome(Optional<RegistryKey<Biome>> optional) {
			List<Type> shuffledList = Arrays.asList(typeList.clone());
			Collections.shuffle(shuffledList);
			if(optional.isPresent()) {
				for(DuckEntity.Type type : shuffledList) {
					if(type.getSpawnBiomes().contains(optional.get())) {
						return type;
					}
				}
			}
			return PEKIN;
		}

		public String getName() {
			return this.name;
		}

		public List<RegistryKey<Biome>> getSpawnBiomes() {
			return this.spawnBiomes;
		}

		public int getIndex() {
			return this.index;
		}
	}

	public static class DuckData extends PassiveEntity.PassiveData {
		public final DuckEntity.Type type;

		public DuckData(DuckEntity.Type typeIn) {
			super(false);
			this.type = typeIn;
		}
	}
}