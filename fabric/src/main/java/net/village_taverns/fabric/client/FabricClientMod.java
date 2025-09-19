package net.village_taverns.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.village_taverns.client.TavernsModClient;

public final class FabricClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TavernsModClient.init();
    }
}
