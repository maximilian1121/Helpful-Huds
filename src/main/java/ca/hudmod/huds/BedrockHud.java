package ca.hudmod.huds;

import ca.hudmod.Hud;
import ca.hudmod.Utils;
import ca.hudmod.config.HudmodConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.Level;

import static ca.hudmod.HudMod.modConfig;

public class BedrockHud implements Hud {
    @Override
    public void render(GuiGraphics guiGraphics, float tickDelta, Minecraft minecraft, LocalPlayer player) {
        if (player == null) return;

        int posX = (int) Math.floor(player.getX());
        int posY = (int) Math.floor(player.getY());
        int posZ = (int) Math.floor(player.getZ());

        int renderX = 5;
        int renderY = 45;
        int padding = 2;

        if (HudmodConfig.BEDROCK_COORDS.get()) {
            String text = String.format("Position: %d, %d, %d", posX, posY, posZ);

            int coordinateTextWidth = minecraft.font.width(text);
            int coordinateTextHeight = minecraft.font.lineHeight;

            guiGraphics.fill(
                    0,
                    renderY - padding,
                    renderX + coordinateTextWidth + padding,
                    renderY + coordinateTextHeight + padding,
                    0x80000000
            );

            guiGraphics.drawString(minecraft.font, text, renderX - padding, renderY + 1, 0xFFFFFF, true);

            renderY = renderY + coordinateTextHeight + padding + 2;
        }

        if (HudmodConfig.BEDROCK_DAYS.get()) {
            long totalTicks = player.level().getDayTime();
            int totalDays = (int)(totalTicks / Level.TICKS_PER_DAY);

            String text = String.format("Days played: %d", totalDays);

            int daysTextWidth = minecraft.font.width(text);
            int daysTextHeight = minecraft.font.lineHeight;

            guiGraphics.fill(
                    0,
                    renderY - padding,
                    renderX + daysTextWidth + padding,
                    renderY + daysTextHeight + padding,
                    0x80000000
            );

            guiGraphics.drawString(minecraft.font, text, renderX - padding, renderY + 1, 0xFFFFFF, true);

            renderY = renderY + daysTextHeight + padding + 2;
        }

        if (HudmodConfig.BEDROCK_TIME.get()) {
            long tod = player.level().getDayTime();

            String text = String.format("Time of day: %s", Utils.formatTime(tod));

            int daysTextWidth = minecraft.font.width(text);
            int daysTextHeight = minecraft.font.lineHeight;

            guiGraphics.fill(
                    0,
                    renderY - padding,
                    renderX + daysTextWidth + padding,
                    renderY + daysTextHeight + padding,
                    0x80000000
            );

            guiGraphics.drawString(minecraft.font, text, renderX - padding, renderY + 1, 0xFFFFFF, true);

            renderY = renderY + daysTextHeight + padding + 2;
        }

        if (HudmodConfig.BEDROCK_FPS.get()) {
            String text = String.format("FPS: %s", minecraft.getFps());

            int daysTextWidth = minecraft.font.width(text);
            int daysTextHeight = minecraft.font.lineHeight;

            guiGraphics.fill(
                    0,
                    renderY - padding,
                    renderX + daysTextWidth + padding,
                    renderY + daysTextHeight + padding,
                    0x80000000
            );

            guiGraphics.drawString(minecraft.font, text, renderX - padding, renderY + 1, 0xFFFFFF, true);

            renderY = renderY + daysTextHeight + padding + 2;
        }
    }
}
