package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.StructurePieceCreator;
import com.hugman.wild_explorer.init.WEPack;
import com.hugman.wild_explorer.object.world.gen.feature.structure.generator.ElderVillageGenerator;
import net.minecraft.structure.StructurePieceType;

public class WEStructurePieces extends WEPack {
	public static final StructurePieceType ELDER_VILLAGE_PIECE = register(new StructurePieceCreator.Builder("evp", ElderVillageGenerator.MyPiece::new));

	public static void init() {
	}
}
