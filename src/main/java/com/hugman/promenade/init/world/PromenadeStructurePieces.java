package com.hugman.promenade.init.world;

import com.hugman.dawn.api.creator.StructurePieceCreator;
import com.hugman.promenade.init.PromenadePack;
import com.hugman.promenade.object.world.gen.feature.structure.generator.WitchHutGenerator;
import net.minecraft.structure.StructurePieceType;

public class PromenadeStructurePieces extends PromenadePack {
	public static final StructurePieceType WITCH_HUT_PIECE = register(new StructurePieceCreator.Builder("dfh", WitchHutGenerator.MainPiece::new));

	public static void init() {
	}
}
