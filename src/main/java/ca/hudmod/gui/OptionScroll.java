package ca.hudmod.gui;

import ca.hudmod.config.ConfigOption;
import ca.hudmod.config.HudmodConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
//? if <1.21.2 {
/*import net.minecraft.client.gui.components.AbstractScrollWidget;
 *///?} else {
import net.minecraft.client.gui.components.AbstractScrollArea;
//?}
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

//? if <1.21.2 {
/*public class OptionScroll extends AbstractScrollWidget {
 *///?} else {
public class OptionScroll extends AbstractScrollArea {
    //?}
    private final List<Button> buttons = new ArrayList<>();
    private final int buttonHeight = 20;
    private final int gap = 5;
    private Consumer<String> setHoveredConsumer;

    public OptionScroll(int x, int y, int width, int height, Component title, Consumer<String> setHovered) {
        super(x, y, width, height, title);
        this.setHoveredConsumer = setHovered;
        refreshButtons();
    }

    public void refreshButtons() {
        buttons.clear();
        for (ConfigOption option : HudmodConfig.OPTIONS) {
            addToggleButton(option);
        }
    }

    private void addToggleButton(ConfigOption opt) {
        int buttonWidth = 40;
        int xPos = this.getX() + this.width - buttonWidth - 10;
        Button btn = Button.builder(getButtonText(opt.get()), (b) -> {
            opt.set(!opt.get());
            b.setMessage(getButtonText(opt.get()));
        }).bounds(xPos, 0, 40, 20).build();

        buttons.add(btn);
    }

    private Component getButtonText(boolean val) {
        return val ? Component.literal("ON").withStyle(s -> s.withColor(0x55FF55)) :
                Component.literal("OFF").withStyle(s -> s.withColor(0xFF5555));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.isMouseOver(mouseX, mouseY)) return false;

        double adjustedMouseY = mouseY + this.scrollAmount();

        for (Button btn : buttons) {
            if (btn.mouseClicked(mouseX, adjustedMouseY, button)) return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    //? if <1.21.2 {
    /*protected void renderContents(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
     *///?} else {
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        //?}
        int currentY = this.getY();
        int adjustedMouseY = (int) (mouseY + this.scrollAmount());

        for (int i = 0; i < buttons.size(); i++) {
            ConfigOption opt = HudmodConfig.OPTIONS.get(i);
            Button btn = buttons.get(i);
            btn.setY(currentY);

            graphics.drawString(Minecraft.getInstance().font,
                    Component.translatable(opt.translationKey()),
                    this.getX() + 10, currentY + 5, 0xFFFFFF);

            btn.render(graphics, mouseX, adjustedMouseY, partialTick);
            if (btn.isMouseOver(mouseX, adjustedMouseY)) {
                this.setHoveredConsumer.accept(opt.translationKey());
            }

            currentY += buttonHeight + gap;
        }
    }

    //? if <1.21.2 {
    /*@Override
    protected void renderBackground(GuiGraphics graphics) {
        graphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0x80000000);
    }
    *///?}

    //? if>1.21.1 {
    @Override
    protected int contentHeight() {
    //?} else {
    /*protected int getInnerHeight() {
    *///?}
        return (buttons.size() * (buttonHeight + gap));
    }

    @Override
    protected double scrollRate() { return 14.0; }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) { }
}
