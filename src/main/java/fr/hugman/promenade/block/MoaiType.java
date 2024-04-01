package fr.hugman.promenade.block;

public enum MoaiType implements net.minecraft.util.StringIdentifiable {
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