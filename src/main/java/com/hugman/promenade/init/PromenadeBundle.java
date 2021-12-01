package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.SimpleCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.promenade.Promenade;

public abstract class PromenadeBundle extends Bundle {
	protected static <O, V extends SimpleCreator<O>> O add(V creator) {
		Promenade.MOD_DATA.addCreator(creator);
		return creator.getValue();
	}

	protected static <V extends Creator> V creator(V creator) {
		Promenade.MOD_DATA.addCreator(creator);
		return creator;
	}

	protected static <V extends Bundle> V bundle(V bundle) {
		Promenade.MOD_DATA.addBundle(bundle);
		return bundle;
	}

	public static void init() {
	}
}
