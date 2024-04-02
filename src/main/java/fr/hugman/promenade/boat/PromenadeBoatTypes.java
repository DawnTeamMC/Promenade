package fr.hugman.promenade.boat;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.item.PromenadeItems;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class PromenadeBoatTypes {
    public static final TerraformBoatType SAKURA = register(PromenadeBoatTypeKeys.SAKURA, new TerraformBoatType.Builder()
            .item(PromenadeItems.SAKURA_BOAT)
            .chestItem(PromenadeItems.SAKURA_CHEST_BOAT)
            .planks(PromenadeBlocks.SAKURA_PLANKS.asItem())
            .build());
    public static final TerraformBoatType MAPLE = register(PromenadeBoatTypeKeys.MAPLE, new TerraformBoatType.Builder()
            .item(PromenadeItems.MAPLE_BOAT)
            .chestItem(PromenadeItems.MAPLE_CHEST_BOAT)
            .planks(PromenadeBlocks.MAPLE_PLANKS.asItem())
            .build());

    private static <O extends TerraformBoatType> O register(RegistryKey<TerraformBoatType> key, O boatType) {
        return Registry.register(TerraformBoatTypeRegistry.INSTANCE, key, boatType);
    }

    /**
     * @deprecated Use {@link #register(RegistryKey, TerraformBoatType)} instead
     */
    @Deprecated
    private static <O extends TerraformBoatType> O register(String path, O boatType) {
        return Registry.register(TerraformBoatTypeRegistry.INSTANCE, Promenade.id(path), boatType);
    }
}
