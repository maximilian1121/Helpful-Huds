package ca.maximilian.hudmod.client.Huds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public interface Hud {
    void render(GuiGraphics guiGraphics, float tickDelta, Minecraft minecraft, LocalPlayer player);
}