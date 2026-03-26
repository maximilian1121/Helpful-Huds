package ca.maximilian.hudmod.client;

import ca.maximilian.hudmod.client.Config.ModConfig;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class HelpfulHudsClient implements ClientModInitializer {
    public static ModConfig modConfig = ConfigApiJava.registerAndLoadConfig(ModConfig::new, RegisterType.CLIENT);

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new MainHud());
    }
}
