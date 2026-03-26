package ca.maximilian.hudmod.client.Huds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

import static ca.maximilian.hudmod.client.HelpfulHudsClient.modConfig;

public class HotbarHud implements Hud {
    @Override
    public void render(GuiGraphics guiGraphics, float tickDelta, Minecraft minecraft, LocalPlayer player) {
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();

        if (modConfig.hotbarCoordinateCounter.get() == false) return;

        int posX = (int) Math.floor(player.getX());
        int posY = (int) Math.floor(player.getY());
        int posZ = (int) Math.floor(player.getZ());

        String text = String.format("§cX: %d §aY: %d §bZ: %d", posX, posY, posZ);

        int coordinateTextWidth = minecraft.font.width(text);

        int textHeight = height - 48;

        if (minecraft.gameMode != null && !minecraft.gameMode.canHurtPlayer()) {
            textHeight += 14;
        }

        guiGraphics.drawString(minecraft.font, text, width / 2 - coordinateTextWidth / 2, textHeight, 0xFFFFFF, true);
    }
}
