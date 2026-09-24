package fr.hugman.promenade.client.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.hugman.promenade.client.config.PromenadeConfigScreen;

public class PromenadeModMenuApi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return PromenadeConfigScreen::new;
    }
}
