package fr.hugman.promenade.fabric.mixin;

import fr.hugman.promenade.fabric.AliasedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * Resolves aliases when content is looked up by id, as when worlds and items are loaded.
 * Defaulted registries (blocks, items...) look up their entries through this method too.
 */
@Mixin(MappedRegistry.class)
public abstract class MappedRegistryMixin<T> implements AliasedRegistry {
    @Shadow
    @Final
    private Map<ResourceLocation, Holder.Reference<T>> byLocation;

    @Unique
    private final Map<ResourceLocation, ResourceLocation> promenade$aliases = new HashMap<>();

    @Override
    public void promenade$addAlias(ResourceLocation oldId, ResourceLocation newId) {
        this.promenade$aliases.put(oldId, newId);
    }

    @ModifyVariable(method = "get(Lnet/minecraft/resources/ResourceLocation;)Ljava/lang/Object;", at = @At("HEAD"), argsOnly = true)
    private ResourceLocation promenade$resolveAlias(@Nullable ResourceLocation id) {
        if (id == null || this.promenade$aliases.isEmpty() || this.byLocation.containsKey(id)) {
            return id;
        }
        return this.promenade$aliases.getOrDefault(id, id);
    }
}
