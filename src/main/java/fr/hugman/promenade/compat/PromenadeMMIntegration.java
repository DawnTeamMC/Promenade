package fr.hugman.promenade.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.hugman.promenade.config.PromenadeConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class PromenadeMMIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> (Screen) AutoConfig.getConfigScreen(PromenadeConfig.class, parent).get();
    }
}
