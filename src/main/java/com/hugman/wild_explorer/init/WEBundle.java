package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.SimpleCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.wild_explorer.WildExplorer;

public abstract class WEBundle extends Bundle {
	protected static <O, V extends SimpleCreator<O>> O add(V creator) {
		WildExplorer.MOD_DATA.addCreator(creator);
		return creator.getValue();
	}

	protected static <V extends Creator> V creator(V creator) {
		WildExplorer.MOD_DATA.addCreator(creator);
		return creator;
	}

	protected static <V extends Bundle> V bundle(V bundle) {
		WildExplorer.MOD_DATA.addBundle(bundle);
		return bundle;
	}
}
