package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.StructurePieceCreator;
import com.hugman.wild_explorer.init.WEPack;
import com.hugman.wild_explorer.object.world.gen.feature.structure.generator.WitchHutGenerator;
import net.minecraft.structure.StructurePieceType;

public class WEStructurePieces extends WEPack {
	public static final StructurePieceType WITCH_HUT_PIECE = register(new StructurePieceCreator.Builder("dfh", WitchHutGenerator.MainPiece::new));

	public static void init() {
	}
}
