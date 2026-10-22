package fr.hugman.promenade.fabric;

import net.minecraft.resources.ResourceLocation;

/**
 * Implemented by every registry. Fabric API has no registry aliases for 1.20.1, unlike Forge and newer versions.
 */
public interface AliasedRegistry {
    /**
     * Makes {@code oldId} resolve to {@code newId} when nothing is registered under {@code oldId}.
     */
    void promenade$addAlias(ResourceLocation oldId, ResourceLocation newId);
}
