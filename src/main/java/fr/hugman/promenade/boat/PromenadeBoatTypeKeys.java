package fr.hugman.promenade.boat;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class PromenadeBoatTypeKeys {
    public static final RegistryKey<TerraformBoatType> MAPLE = of("maple");

    private static RegistryKey<TerraformBoatType> of(String path) {
        return TerraformBoatTypeRegistry.createKey(Promenade.id(path));
    }
}
