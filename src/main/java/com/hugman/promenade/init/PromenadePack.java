package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.promenade.Promenade;

public abstract class PromenadePack extends Pack {
	protected static <V, B extends Creator.Builder<V>> V register(B creatorBuilder) {
		return add(creatorBuilder, Promenade.MOD_DATA);
	}

	protected static <P extends Pack, B extends Pack.Builder> P register(B packBuilder) {
		return add(packBuilder, Promenade.MOD_DATA);
	}
}
