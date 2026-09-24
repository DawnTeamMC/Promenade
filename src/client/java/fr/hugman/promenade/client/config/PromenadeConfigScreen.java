package fr.hugman.promenade.client.config;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.config.PromenadeConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.List;

/**
 * An in-game screen to edit the {@link PromenadeConfig}.
 * <p>
 * Changes are saved when the screen is closed, and only take effect once the game restarts.
 */
public class PromenadeConfigScreen extends OptionsSubScreen {
    private static final String KEY = Promenade.MOD_ID + ".config";
    private static final Component TITLE = Component.translatable(KEY + ".title");
    private static final int MAX_WEIGHT = 100;

    private final OptionInstance<Boolean> igneousRockPatches;
    private final OptionInstance<Boolean> blueberryBushes;
    private final OptionInstance<Boolean> palms;
    private final OptionInstance<Integer> capybarasWeight;
    private final OptionInstance<Integer> ducksWeight;
    private final OptionInstance<Integer> lushCreepersWeight;
    private final OptionInstance<Integer> sunkensWeight;

    public PromenadeConfigScreen(Screen lastScreen) {
        super(lastScreen, Minecraft.getInstance().options, TITLE);
        var config = PromenadeConfig.getSaved();
        var worldFeatures = config.worldFeatures();
        this.igneousRockPatches = toggle("world_features.igneous_rock_patches", worldFeatures.igneousRockPatches());
        this.blueberryBushes = toggle("world_features.blueberry_bushes", worldFeatures.blueberryBushes());
        this.palms = toggle("world_features.palms", worldFeatures.palms());
        this.capybarasWeight = weight("animals.capybaras_weight", config.animals().capybarasWeight());
        this.ducksWeight = weight("animals.ducks_weight", config.animals().ducksWeight());
        this.lushCreepersWeight = weight("monsters.lush_creepers_weight", config.monsters().lushCreepersWeight());
        this.sunkensWeight = weight("monsters.sunkens_weight", config.monsters().sunkensWeight());
    }

    @Override
    protected void addOptions() {
        this.list.addHeader(Component.translatable(KEY + ".world_features"));
        this.list.addSmall(this.igneousRockPatches, this.blueberryBushes, this.palms);
        this.list.addHeader(Component.translatable(KEY + ".animals"));
        this.list.addSmall(this.capybarasWeight, this.ducksWeight);
        this.list.addHeader(Component.translatable(KEY + ".monsters"));
        this.list.addSmall(this.lushCreepersWeight, this.sunkensWeight);
    }

    @Override
    protected void addFooter() {
        var footer = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        footer.addChild(Button.builder(Component.translatable(KEY + ".reset"), button -> this.reset()).build());
        footer.addChild(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).build());
    }

    private void reset() {
        var config = PromenadeConfig.DEFAULT;
        this.igneousRockPatches.set(config.worldFeatures().igneousRockPatches());
        this.blueberryBushes.set(config.worldFeatures().blueberryBushes());
        this.palms.set(config.worldFeatures().palms());
        this.capybarasWeight.set(config.animals().capybarasWeight());
        this.ducksWeight.set(config.animals().ducksWeight());
        this.lushCreepersWeight.set(config.monsters().lushCreepersWeight());
        this.sunkensWeight.set(config.monsters().sunkensWeight());
        // Refreshes the existing widgets: rebuilding them would stack a new layout on top of the old one
        for (var option : List.of(this.igneousRockPatches, this.blueberryBushes, this.palms,
                this.capybarasWeight, this.ducksWeight, this.lushCreepersWeight, this.sunkensWeight)) {
            this.resetOption(option);
        }
    }

    @Override
    public void removed() {
        // Saves our config instead of the vanilla options
        PromenadeConfig.save(this.toConfig());
        if (PromenadeConfig.requiresRestart()) {
            SystemToast.addOrUpdate(this.minecraft.gui.toastManager(), SystemToast.SystemToastId.PERIODIC_NOTIFICATION,
                    TITLE, Component.translatable(KEY + ".restart_required"));
        }
    }

    private PromenadeConfig toConfig() {
        return new PromenadeConfig(
                new PromenadeConfig.WorldFeaturesConfig(this.igneousRockPatches.get(), this.blueberryBushes.get(), this.palms.get()),
                new PromenadeConfig.AnimalsConfig(this.capybarasWeight.get(), this.ducksWeight.get()),
                new PromenadeConfig.MonstersConfig(this.lushCreepersWeight.get(), this.sunkensWeight.get())
        );
    }

    private static OptionInstance<Boolean> toggle(String name, boolean value) {
        return OptionInstance.createBoolean(KEY + "." + name, OptionInstance.cachedConstantTooltip(tooltip(name)), value);
    }

    private static OptionInstance<Integer> weight(String name, int value) {
        // Weights of 0 or below disable the mob, and weights above the slider's range can still be set in the file
        int initialValue = Math.max(value, 0);
        return new OptionInstance<>(
                KEY + "." + name,
                OptionInstance.cachedConstantTooltip(tooltip(name)),
                (caption, weight) -> weight == 0 ? Options.genericValueLabel(caption, CommonComponents.OPTION_OFF) : Options.genericValueLabel(caption, weight),
                new OptionInstance.IntRange(0, Math.max(MAX_WEIGHT, initialValue)),
                initialValue,
                OptionInstance.NO_ACTION
        );
    }

    private static Component tooltip(String name) {
        return Component.translatable(KEY + "." + name + ".tooltip");
    }
}
