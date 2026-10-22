package fr.hugman.promenade.client.config;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.config.PromenadeConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
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

    private OptionList list;

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
    protected void init() {
        this.list = new OptionList(this.minecraft, this.width, this.height, 32, this.height - 32);
        this.list.addHeader(Component.translatable(KEY + ".world_features"), this.font);
        this.list.addOptions(this.options, this.igneousRockPatches, this.blueberryBushes, this.palms);
        this.list.addHeader(Component.translatable(KEY + ".animals"), this.font);
        this.list.addOptions(this.options, this.capybarasWeight, this.ducksWeight);
        this.list.addHeader(Component.translatable(KEY + ".monsters"), this.font);
        this.list.addOptions(this.options, this.lushCreepersWeight, this.sunkensWeight);
        this.addWidget(this.list);

        this.addRenderableWidget(Button.builder(Component.translatable(KEY + ".reset"), button -> this.reset())
                .bounds(this.width / 2 - 155, this.height - 27, 150, 20).build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose())
                .bounds(this.width / 2 + 5, this.height - 27, 150, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        this.list.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, partialTick);
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
        // Reopens the screen to refresh the widgets: this screen saves the defaults as it is closed, and the new one
        // reads them back. Rebuilding the widgets in place would stack a new layout on top of the old one.
        this.minecraft.setScreen(new PromenadeConfigScreen(this.lastScreen));
    }

    @Override
    public void removed() {
        // Saves our config instead of the vanilla options
        PromenadeConfig.save(this.toConfig());
        if (PromenadeConfig.requiresRestart()) {
            SystemToast.addOrUpdate(this.minecraft.getToasts(), SystemToast.SystemToastIds.PERIODIC_NOTIFICATION,
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
                newValue -> {
                }
        );
    }

    private static Component tooltip(String name) {
        return Component.translatable(KEY + "." + name + ".tooltip");
    }

    /**
     * The 1.20.1 option list only holds options: this one also has headers, spanning both columns.
     */
    private static class OptionList extends ContainerObjectSelectionList<OptionList.Row> {
        private static final int ROW_HEIGHT = 25;

        public OptionList(Minecraft minecraft, int width, int height, int top, int bottom) {
            super(minecraft, width, height, top, bottom, ROW_HEIGHT);
            this.centerListVertically = false;
        }

        public void addHeader(Component title, Font font) {
            this.addEntry(new Row(List.of(new StringWidget(this.width / 2 - 155, 0, 310, 20, title, font))));
        }

        public void addOptions(Options options, OptionInstance<?>... instances) {
            for (int i = 0; i < instances.length; i += 2) {
                var left = instances[i].createButton(options, this.width / 2 - 155, 0, 150);
                this.addEntry(new Row(i + 1 < instances.length
                        ? List.of(left, instances[i + 1].createButton(options, this.width / 2 + 5, 0, 150))
                        : List.of(left)));
            }
        }

        @Override
        public int getRowWidth() {
            return 400;
        }

        @Override
        protected int getScrollbarPosition() {
            return super.getScrollbarPosition() + 32;
        }

        private static class Row extends ContainerObjectSelectionList.Entry<Row> {
            private final List<AbstractWidget> widgets;

            private Row(List<AbstractWidget> widgets) {
                this.widgets = widgets;
            }

            @Override
            public void render(GuiGraphics graphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovered, float partialTick) {
                for (var widget : this.widgets) {
                    widget.setY(top);
                    widget.render(graphics, mouseX, mouseY, partialTick);
                }
            }

            @Override
            public List<? extends GuiEventListener> children() {
                return this.widgets;
            }

            @Override
            public List<? extends NarratableEntry> narratables() {
                return this.widgets;
            }
        }
    }
}
