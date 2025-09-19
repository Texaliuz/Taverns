package net.village_taverns.neoforge.client;

import net.minecraft.client.render.RenderLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.village_taverns.TavernsMod;
import net.village_taverns.block.TavernBlocks;
import net.village_taverns.client.TavernsModClient;

@EventBusSubscriber(modid = TavernsMod.ID, value = Dist.CLIENT)
public class NeoForgeClientMod {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        TavernsModClient.init();
        event.enqueueWork(() -> {
            registerBlockRenderLayers();
        });
    }

    private static void registerBlockRenderLayers() {
        // Register block render layers for NeoForge
        net.neoforged.neoforge.client.RenderTypeHelper.setRenderLayer(TavernBlocks.BARREL.block(), RenderLayer.getCutout());
    }
}