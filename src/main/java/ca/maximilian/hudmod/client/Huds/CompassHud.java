package ca.maximilian.hudmod.client.Huds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import com.mojang.blaze3d.vertex.PoseStack;

import static ca.maximilian.hudmod.client.HelpfulHudsClient.modConfig;

public class CompassHud implements Hud {
    private float smoothedYaw = 0f;
    private final float LERP_SPEED = 0.01f;

    @Override
    public void render(GuiGraphics guiGraphics, float tickDelta, Minecraft minecraft, LocalPlayer player) {
        if (!modConfig.compassHud.get()) return;
        int centerX = minecraft.getWindow().getGuiScaledWidth() / 2;
        int y = 10;
        float centerY = y + 15;

        float targetYaw = (player.getViewYRot(tickDelta) + 180) % 360;
        if (targetYaw < 0) targetYaw += 360;

        float diff = targetYaw - smoothedYaw;
        if (diff < -180) diff += 360;
        if (diff > 180) diff -= 360;

        smoothedYaw += diff * LERP_SPEED;
        smoothedYaw = (smoothedYaw % 360 + 360) % 360;

        int visibleRange = 90;
        int widthPx = 300;
        float scale = (float) widthPx / visibleRange;

        PoseStack poseStack = guiGraphics.pose();

        for (float i = 0; i < 360; i += 5.0f) {
            float angleDiff = getAngleDiff(i, smoothedYaw);
            if (Math.abs(angleDiff) < visibleRange / 2f) {
                float posX = centerX + (angleDiff * scale);
                float progress = angleDiff / (visibleRange / 2f);

                float cosValue = (float) Math.cos(progress * (Math.PI / 2));
                float sizeScale = cosValue * cosValue;

                float baseHeight;
                if (i % 45 == 0) baseHeight = 10f;
                else if (i % 15 == 0) baseHeight = 6f;
                else baseHeight = 3f;

                float lineHeight = baseHeight * sizeScale;

                poseStack.pushPose();
                poseStack.translate(posX, centerY - (lineHeight / 2f), 0);

                int alpha = (int) (sizeScale * 255);
                int color = (alpha << 24) | 0xFFFFFF;

                guiGraphics.fill(0, 0, 1, (int) Math.max(1, lineHeight), color);
                poseStack.popPose();
            }
        }

        String[] markers = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        int[] degrees = {0, 45, 90, 135, 180, 225, 270, 315};

        for (int i = 0; i < markers.length; i++) {
            float angleDiff = getAngleDiff(degrees[i], smoothedYaw);

            if (Math.abs(angleDiff) < visibleRange / 2f) {
                float posX = centerX + (angleDiff * scale);
                float progress = angleDiff / (visibleRange / 2f);
                float cosValue = (float) Math.cos(progress * (Math.PI / 2));
                float sizeScale = cosValue * cosValue;

                int baseColor = switch (markers[i]) {
                    case "N" -> 0xfe4141;
                    case "S" -> 0x4141fe;
                    default  -> 0xFFFFFF;
                };

                int alpha = (int) (sizeScale * 255);
                int color = (alpha << 24) | baseColor;

                poseStack.pushPose();
                poseStack.translate(posX, y + 4, 0);
                poseStack.scale(sizeScale, sizeScale, 1.0f);

                float textWidth = minecraft.font.width(markers[i]);
                guiGraphics.drawString(minecraft.font, markers[i], (int)(-textWidth / 2f) + 1, -4, color, false);

                poseStack.popPose();
            }
        }

        guiGraphics.fill(centerX - 1, y + 10, centerX + 1, y + 20, 0xFFFFFFFF);
    }

    private float getAngleDiff(float target, float current) {
        float diff = target - current;
        while (diff < -180) diff += 360;
        while (diff > 180) diff -= 360;
        return diff;
    }
}