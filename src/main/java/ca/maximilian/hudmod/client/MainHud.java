package ca.maximilian.hudmod.client;

import ca.maximilian.hudmod.client.Huds.BedrockHud;
import ca.maximilian.hudmod.client.Huds.CompassHud;
import ca.maximilian.hudmod.client.Huds.HotbarHud;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import static ca.maximilian.hudmod.client.HelpfulHudsClient.modConfig;

public class MainHud implements HudRenderCallback {
    BedrockHud bedrockHud = new BedrockHud();
    HotbarHud hotbarHud = new HotbarHud();
    CompassHud compassHud = new CompassHud();

    @Override
    public void onHudRender(GuiGraphics guiGraphics, float tickDelta) {
        Minecraft minecraft = Minecraft.getInstance();
//        if (!modConfig.hudsAreVisible || Minecraft.getInstance().options.renderDebug) return;
        bedrockHud.render(guiGraphics, tickDelta, minecraft, minecraft.player);
        hotbarHud.render(guiGraphics, tickDelta, minecraft, minecraft.player);
        compassHud.render(guiGraphics, tickDelta, minecraft, minecraft.player);
    }
}