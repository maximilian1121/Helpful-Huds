package ca.hudmod;

import ca.hudmod.huds.BedrockHud;
import ca.hudmod.huds.CompassHud;
import ca.hudmod.huds.HotbarHud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class MainHud {
    static BedrockHud bedrockHud = new BedrockHud();
    static HotbarHud hotbarHud = new HotbarHud();
    static CompassHud compassHud = new CompassHud();

    private static void renderMain(GuiGraphics guiGraphics, float tickDelta) {
        Minecraft minecraft = Minecraft.getInstance();
        //? if <1.21
        //if (minecraft.options.renderDebug) return;
        bedrockHud.render(guiGraphics, tickDelta, minecraft, minecraft.player);
        hotbarHud.render(guiGraphics, tickDelta, minecraft, minecraft.player);
        compassHud.render(guiGraphics, tickDelta, minecraft, minecraft.player);
    }

    //? if >=1.21 {
    public static void render(GuiGraphics guiGraphics, net.minecraft.client.DeltaTracker deltaTracker) {
        renderMain(guiGraphics, deltaTracker.getGameTimeDeltaPartialTick(true));
    }
    //?} else if fabric || forge {
    /*public static void render(GuiGraphics guiGraphics, float delta) {
        renderMain(guiGraphics, delta);
    }
    *///?}
}
