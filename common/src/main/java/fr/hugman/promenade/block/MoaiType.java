package fr.hugman.promenade.block;

import net.minecraft.util.StringRepresentable;

public enum MoaiType implements StringRepresentable {
    SINGLE("single"),
    TOP("top"),
    BOTTOM("bottom");

    private final String name;

    MoaiType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}