package fr.hugman.promenade.entity.data;

import fr.hugman.promenade.entity.CapybaraState;
import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.registry.PromenadeRegistries;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

public class PromenadeTrackedData {
	public static final TrackedDataHandler<CapybaraVariant> CAPYBARA_VARIANT = TrackedDataHandler.of(PromenadeRegistries.CAPYBARA_VARIANT);
	public static final TrackedDataHandler<CapybaraState> CAPYBARA_STATE = TrackedDataHandler.ofEnum(CapybaraState.class);

	public static void init() {
		TrackedDataHandlerRegistry.register(CAPYBARA_VARIANT);
		TrackedDataHandlerRegistry.register(CAPYBARA_STATE);
	}
}
