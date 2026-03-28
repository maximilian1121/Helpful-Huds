package ca.hudmod.gui;

import ca.hudmod.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
//? if <1.21.2 {
/*import net.minecraft.client.gui.components.AbstractScrollWidget;
*///?} else {
import net.minecraft.client.gui.components.AbstractScrollArea;
//?}
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.resources.ResourceManager;

//? if <1.21.2 {
/*public class SummaryScroll extends AbstractScrollWidget {
*///?} else {
public class SummaryScroll extends AbstractScrollArea {
//?}
        private String translationKey;
    private int contentHeight = 0;

    public SummaryScroll(int x, int y, int width, int height) {
        super(x, y, width, height, Component.literal("Summary"));
    }

    public void updateContent(String key) {
        if (key == null || !key.equals(this.translationKey)) {
            this.setScrollAmount(0);
        }
        this.translationKey = key;
    }

    @Override
    //? if <1.21.2 {
    /*protected void renderContents(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
     *///?} else {
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
    //?}
        String key = (translationKey != null) ? translationKey : "gui.hudmod.none_selected";
        Minecraft mc = Minecraft.getInstance();

        int padding = 4;
        int drawX = this.getX() + padding;
        int currentY = this.getY() + padding;

        int usableWidth = this.width - (padding * 2);

        int imageHeight = (key.equals("gui.hudmod.none_selected")) ? (usableWidth * 98) / 720 : (usableWidth * 9) / 16;

        ResourceManager manager = Minecraft.getInstance().getResourceManager();

        if (manager.getResource(Utils.getConfigImage(key)).isPresent()) {
            //? if <1.21.2 {
            
            /*graphics.blit(
                Utils.getConfigImage(key),
                drawX,
                currentY,
                0, 0, usableWidth,
                imageHeight, usableWidth, imageHeight
            );
            
            *///?} else {
            graphics.blit(
                    RenderType::guiTextured,
                    Utils.getConfigImage(key),
                    drawX,
                    currentY,
                    0, 0,
                    usableWidth,
                    imageHeight,
                    usableWidth,
                    imageHeight
            );
            //?}

            currentY += imageHeight + 8;
        }

        Component desc = Component.translatable(key + ".desc");
        graphics.drawWordWrap(mc.font, desc, drawX, currentY, usableWidth, 0xFFFFFF);

        int textHeight = mc.font.split(desc, usableWidth).size() * mc.font.lineHeight;
        this.contentHeight = (currentY + textHeight + 10) - this.getY();
    }

    @Override
    //? if <1.21.2 {
    /*protected int getInnerHeight() {
    *///?} else {
    protected int contentHeight() {
    //?}
        return Math.max(this.height, contentHeight);
    }

    //? if <1.21.2 {
    /*@Override
    protected void renderBackground(GuiGraphics graphics) {
        graphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0x80000000);
    }
    *///?}

    @Override
    protected double scrollRate() { return 14.0; }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) { }
}
