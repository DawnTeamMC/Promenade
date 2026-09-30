package fr.hugman.promenade.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Places one of the mod's boats. Mirrors vanilla's {@link BoatItem}, which can only place the vanilla boat entities.
 */
public class PromenadeBoatItem extends BoatItem {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    // The entity types are registered after the items, as they reference them
    private final Supplier<? extends EntityType<? extends Boat>> entityType;

    public PromenadeBoatItem(Supplier<? extends EntityType<? extends Boat>> entityType, boolean hasChest, Properties settings) {
        super(hasChest, Boat.Type.OAK, settings);
        this.entityType = entityType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(stack);
        }
        Vec3 view = player.getViewVector(1.0F);
        List<Entity> entities = world.getEntities(player, player.getBoundingBox().expandTowards(view.scale(5.0)).inflate(1.0), ENTITY_PREDICATE);
        if (!entities.isEmpty()) {
            Vec3 eyePos = player.getEyePosition();
            for (Entity entity : entities) {
                AABB box = entity.getBoundingBox().inflate(entity.getPickRadius());
                if (box.contains(eyePos)) {
                    return InteractionResultHolder.pass(stack);
                }
            }
        }

        Boat boat = this.createBoat(world, hitResult.getLocation(), stack, player);
        if (boat == null) {
            return InteractionResultHolder.fail(stack);
        }
        boat.setYRot(player.getYRot());
        if (!world.noCollision(boat, boat.getBoundingBox())) {
            return InteractionResultHolder.fail(stack);
        }
        if (!world.isClientSide) {
            world.addFreshEntity(boat);
            world.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
            stack.consume(1, player);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }

    @Nullable
    public Boat createBoat(Level world, Vec3 pos, ItemStack stack, @Nullable Player player) {
        Boat boat = this.entityType.get().create(world);
        if (boat == null) {
            return null;
        }
        boat.setPos(pos.x, pos.y, pos.z);
        boat.xo = pos.x;
        boat.yo = pos.y;
        boat.zo = pos.z;
        if (world instanceof ServerLevel serverWorld) {
            EntityType.<Boat>createDefaultStackConfig(serverWorld, stack, player).accept(boat);
        }
        return boat;
    }
}
