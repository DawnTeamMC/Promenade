package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

/**
 * A texture, referenced by its id (without the {@code textures/} prefix and the {@code .png} suffix).
 * Mirrors {@code ClientAsset.ResourceTexture} from newer versions of Minecraft, so variant files keep the same format.
 */
public record ResourceTexture(ResourceLocation id, ResourceLocation texturePath) {
    public static final Codec<ResourceTexture> CODEC = ResourceLocation.CODEC.xmap(ResourceTexture::new, ResourceTexture::id);
    public static final MapCodec<ResourceTexture> DEFAULT_FIELD_CODEC = CODEC.fieldOf("asset_id");
    public static final StreamCodec<ByteBuf, ResourceTexture> STREAM_CODEC = ResourceLocation.STREAM_CODEC.map(ResourceTexture::new, ResourceTexture::id);

    public ResourceTexture(ResourceLocation texture) {
        this(texture, texture.withPath(path -> "textures/" + path + ".png"));
    }
}
