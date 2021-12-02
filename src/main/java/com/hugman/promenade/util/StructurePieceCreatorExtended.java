package com.hugman.promenade.util;

import com.hugman.dawn.api.creator.StructurePieceCreator;
import net.minecraft.structure.StructurePieceType;

public class StructurePieceCreatorExtended extends StructurePieceCreator {
	public StructurePieceCreatorExtended(String name, StructurePieceType.ManagerAware piece) {
		super(name, piece);
	}
}
