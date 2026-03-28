package ca.hudmod.config;

import ca.hudmod.gui.OptionScroll;
import ca.hudmod.gui.SummaryScroll;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {
    private final Screen lastScreen;
    private OptionScroll scrollList;
    private SummaryScroll summaryScroll;

    public ConfigScreen(Screen parent) {
        super(Component.literal("Helpful Huds Config"));
        this.lastScreen = parent;
    }

    @Override
    protected void init() {
        HudmodConfig.load();

        int listHeight = this.height - 110;
        int leftWidth = (int) (this.width / 1.5);
        int rightStart = leftWidth + 15;
        int rightWidth = (this.width - rightStart) - 20;

        this.scrollList = new OptionScroll(5, 50, leftWidth, listHeight, Component.literal("Settings"),
                newHover -> {
                    if (this.summaryScroll != null) {
                        this.summaryScroll.updateContent(newHover);
                    }
                }
        );
        this.addRenderableWidget(this.scrollList);

        this.summaryScroll = new SummaryScroll(rightStart, 50, rightWidth, listHeight);
        this.addRenderableWidget(this.summaryScroll);

        int buttonWidth = 120;
        int gap = 5;
        int totalWidth = (buttonWidth * 2) + gap;
        int startX = this.width / 2 - (totalWidth / 2);

        this.addRenderableWidget(Button.builder(Component.translatable("gui.done"), (button) -> {
            HudmodConfig.save();
            if (this.minecraft != null) this.minecraft.setScreen(this.lastScreen);
        }).bounds(startX, this.height - 40, buttonWidth, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("gui.hudmod.reset"), (button) -> {
            HudmodConfig.resetToDefaults();
            this.scrollList.refreshButtons();
        }).bounds(startX + buttonWidth + gap, this.height - 40, buttonWidth, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        //? if >1.21 {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        //?} else {
        /*this.renderBackground(graphics);
        *///?}
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(this.lastScreen);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
