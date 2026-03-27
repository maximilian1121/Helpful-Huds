package ca.hudmod;

/*? if fabric {*/
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
/*?}*/

/*? if forge {*/
/*import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.ConfigScreenHandler;
*//*?}*/

/*? if neoforge {*/
/*import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
*//*?}*/

import ca.hudmod.config.HudmodConfig;
import ca.hudmod.config.ConfigScreen;

/*? if forgeLike {*/
/*@Mod("hudmod")
public class HudMod {
*//*?}*/

    /*? if fabric {*/
    public class HudMod implements ClientModInitializer {
/*?}*/

        public static HudmodConfig modConfig;

        /*? if forge {*/
        /*public HudMod() {
            HudmodConfig.load();
            modConfig = HudmodConfig.get();

            //noinspection removal Because im not making forge past 1.21.1!
            ModLoadingContext.get().registerExtensionPoint(
                    ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory((mc, parent) -> new ConfigScreen(parent))
            );

            MinecraftForge.EVENT_BUS.addListener(this::onForgeRender);
        }

        private void onForgeRender(RenderGuiOverlayEvent.Post event) {
            MainHud.render(event.getGuiGraphics(), event.getPartialTick());
        }
        *//*?}*/

        /*? if neoforge {*/
        /*public HudMod(IEventBus modEventBus, ModContainer modContainer) {
            HudmodConfig.load();
            modConfig = HudmodConfig.get();

            modContainer.registerExtensionPoint(
                    net.neoforged.neoforge.client.gui.IConfigScreenFactory.class,
                    (mc, parent) -> new ca.hudmod.config.ConfigScreen(parent)
            );

            NeoForge.EVENT_BUS.addListener(this::onNeoForgeRender);
        }

        private void onNeoForgeRender(RenderGuiLayerEvent.Post event) {
            MainHud.render(event.getGuiGraphics(), event.getPartialTick());
        }
        *//*?}*/

        /*? if fabric {*/
        @Override
        public void onInitializeClient() {
            HudmodConfig.load();
            modConfig = HudmodConfig.get();
            HudRenderCallback.EVENT.register(MainHud::render);
        }
        /*?}*/
    }
