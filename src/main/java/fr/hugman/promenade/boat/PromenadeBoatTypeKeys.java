package fr.hugman.promenade.boat;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;

public class PromenadeBoatTypeKeys {
    public static final RegistryKey<TerraformBoatType> SAKURA = of("sakura");
    public static final RegistryKey<TerraformBoatType> MAPLE = of("maple");
    public static final RegistryKey<TerraformBoatType> PALM = of("palm");
    public static final RegistryKey<TerraformBoatType> DUSK_CYPRESS = of("dusk_cypress");

    private static RegistryKey<TerraformBoatType> of(String path) {
        return TerraformBoatTypeRegistry.createKey(Promenade.id(path));
    }
}
