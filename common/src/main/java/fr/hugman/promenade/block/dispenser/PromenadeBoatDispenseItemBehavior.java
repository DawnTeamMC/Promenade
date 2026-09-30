package fr.hugman.promenade.block.dispenser;

import fr.hugman.promenade.item.PromenadeBoatItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

/**
 * Dispenses one of the mod's boats. Mirrors vanilla's {@link net.minecraft.core.dispenser.BoatDispenseItemBehavior}.
 */
public class PromenadeBoatDispenseItemBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

    @Override
    public ItemStack execute(BlockSource source, ItemStack stack) {
        if (!(stack.getItem() instanceof PromenadeBoatItem boatItem)) {
            return this.defaultDispenseItemBehavior.dispense(source, stack);
        }
        Direction direction = source.state().getValue(DispenserBlock.FACING);
        ServerLevel world = source.level();
        Vec3 center = source.center();
        double offset = 0.5625 + (double) EntityType.BOAT.getWidth() / 2.0;
        double x = center.x() + (double) direction.getStepX() * offset;
        double y = center.y() + (double) ((float) direction.getStepY() * 1.125F);
        double z = center.z() + (double) direction.getStepZ() * offset;
        BlockPos pos = source.pos().relative(direction);
        double yOffset;
        if (world.getFluidState(pos).is(FluidTags.WATER)) {
            yOffset = 1.0;
        } else {
            if (!world.getBlockState(pos).isAir() || !world.getFluidState(pos.below()).is(FluidTags.WATER)) {
                return this.defaultDispenseItemBehavior.dispense(source, stack);
            }
            yOffset = 0.0;
        }

        Boat boat = boatItem.createBoat(world, new Vec3(x, y + yOffset, z), stack, null);
        if (boat != null) {
            boat.setYRot(direction.toYRot());
            world.addFreshEntity(boat);
            stack.shrink(1);
        }
        return stack;
    }

    @Override
    protected void playSound(BlockSource source) {
        source.level().levelEvent(1000, source.pos(), 0);
    }
}
