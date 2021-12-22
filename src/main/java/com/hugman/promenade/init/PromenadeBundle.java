package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.SimpleCreator;
import com.hugman.promenade.Promenade;

public abstract class PromenadeBundle {
	protected static <O, V extends SimpleCreator<O>> O add(V creator) {
		Promenade.MOD_DATA.addCreator(creator);
		return creator.getValue();
	}

	protected static <V extends Creator> V creator(V creator) {
		Promenade.MOD_DATA.addCreator(creator);
		return creator;
	}
}
