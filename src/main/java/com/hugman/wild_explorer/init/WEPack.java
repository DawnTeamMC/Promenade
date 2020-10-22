package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.wild_explorer.WildExplorer;

public abstract class WEPack extends Pack {
	protected static <V, B extends CreatorBuilder<V>> V register(B creatorBuilder) {
		return add(creatorBuilder, WildExplorer.MOD_DATA);
	}

	protected static <P extends Pack, B extends PackBuilder> P register(B packBuilder) {
		return add(packBuilder, WildExplorer.MOD_DATA);
	}
}
