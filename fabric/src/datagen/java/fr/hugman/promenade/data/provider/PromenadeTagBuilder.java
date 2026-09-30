package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.references.BlockItemId;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * Wraps Fabric's tag builder to also accept {@link BlockItemId}s, as newer versions of Fabric API do.
 */
public final class PromenadeTagBuilder<T> {
    private final FabricTagProvider<T>.FabricTagBuilder builder;
    @Nullable
    private final Function<BlockItemId, ResourceKey<T>> blockItemKey;

    public PromenadeTagBuilder(FabricTagProvider<T>.FabricTagBuilder builder, @Nullable Function<BlockItemId, ResourceKey<T>> blockItemKey) {
        this.builder = builder;
        this.blockItemKey = blockItemKey;
    }

    @SafeVarargs
    public final PromenadeTagBuilder<T> add(T... values) {
        this.builder.add(values);
        return this;
    }

    @SafeVarargs
    public final PromenadeTagBuilder<T> add(ResourceKey<T>... keys) {
        for (ResourceKey<T> key : keys) {
            this.builder.add(key);
        }
        return this;
    }

    public PromenadeTagBuilder<T> add(BlockItemId... ids) {
        if (this.blockItemKey == null) {
            throw new IllegalStateException("This tag can't contain blocks or items");
        }
        for (BlockItemId id : ids) {
            this.builder.add(this.blockItemKey.apply(id));
        }
        return this;
    }

    public PromenadeTagBuilder<T> addTag(TagKey<T> tag) {
        this.builder.addTag(tag);
        return this;
    }

    public PromenadeTagBuilder<T> addOptionalTag(TagKey<T> tag) {
        this.builder.addOptionalTag(tag);
        return this;
    }

    public PromenadeTagBuilder<T> forceAddTag(TagKey<T> tag) {
        this.builder.forceAddTag(tag);
        return this;
    }
}
