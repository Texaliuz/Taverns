package net.village_taverns.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.village_taverns.TavernsMod;
import net.village_taverns.client.TavernsModClient;

@EventBusSubscriber(modid = TavernsMod.ID, value = Dist.CLIENT)
public class NeoForgeClientMod {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        TavernsModClient.init();
    }
}