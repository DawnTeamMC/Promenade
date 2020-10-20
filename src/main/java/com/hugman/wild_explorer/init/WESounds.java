package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.SoundCreator;
import net.minecraft.sound.SoundEvent;

public class WESounds extends WEPack{
	public static void init() {
	}

	public static final SoundEvent ENTITY_DUCK_AMBIENT = register(new SoundCreator.Builder("entity.duck.ambient"));
	public static final SoundEvent ENTITY_DUCK_HURT = register(new SoundCreator.Builder("entity.duck.hurt"));
	public static final SoundEvent ENTITY_DUCK_DEATH = register(new SoundCreator.Builder("entity.duck.death"));
	public static final SoundEvent ENTITY_DUCK_STEP = register(new SoundCreator.Builder("entity.duck.step"));
}
