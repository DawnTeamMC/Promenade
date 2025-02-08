package fr.hugman.promenade.block;

import net.minecraft.util.StringIdentifiable;

public enum MoaiType implements StringIdentifiable {
    SINGLE("single"),
    TOP("top"),
    BOTTOM("bottom");

    private final String name;

    MoaiType(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}