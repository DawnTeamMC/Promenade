package fr.hugman.promenade.pack;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.List;

/**
 * Optional data packs shipped with the mod. They are disabled by default: players can enable them from the data pack
 * selection screen when creating a world.
 * <p>
 * Each pack is stored in the mod's jar, in {@code resourcepacks/<pack path>/}. Their content is generated.
 */
public final class PromenadeDataPacks {
    public static final Identifier NO_SAKURA_GROVES = Promenade.id("no_sakura_groves");
    public static final Identifier NO_CARNELIAN_TREEWAY = Promenade.id("no_carnelian_treeway");
    public static final Identifier NO_GLACARIAN_TAIGA = Promenade.id("no_glacarian_taiga");
    public static final Identifier NO_DARK_AMARANTH_FOREST = Promenade.id("no_dark_amaranth_forest");

    public static final Identifier SAKURA_GROVES_REPLACE_CHERRY_GROVES = Promenade.id("sakura_groves_replace_cherry_groves");
    public static final Identifier CARNELIAN_TREEWAY_REPLACES_DAPPLED_FOREST = Promenade.id("carnelian_treeway_replaces_dappled_forest");

    public static final List<Identifier> ALL = List.of(
            NO_SAKURA_GROVES,
            NO_CARNELIAN_TREEWAY,
            NO_GLACARIAN_TAIGA,
            NO_DARK_AMARANTH_FOREST,
            SAKURA_GROVES_REPLACE_CHERRY_GROVES,
            CARNELIAN_TREEWAY_REPLACES_DAPPLED_FOREST
    );

    public static void register() {
        for (Identifier pack : ALL) {
            PromenadePlatform.INSTANCE.registerBuiltinDataPack(pack, Component.translatable(nameKey(pack)));
        }
    }

    public static String nameKey(Identifier pack) {
        return "dataPack." + pack.getNamespace() + "." + pack.getPath() + ".name";
    }

    public static String descriptionKey(Identifier pack) {
        return "dataPack." + pack.getNamespace() + "." + pack.getPath() + ".description";
    }
}
