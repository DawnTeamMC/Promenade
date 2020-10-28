package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.wild_explorer.WildExplorer;

public abstract class WEPack extends Pack {
	protected static <V, B extends Creator.Builder<V>> V register(B creatorBuilder) {
		return add(creatorBuilder, WildExplorer.MOD_DATA);
	}

	protected static <P extends Pack, B extends Pack.Builder> P register(B packBuilder) {
		return add(packBuilder, WildExplorer.MOD_DATA);
	}
}
