package net.village_taverns.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.village_taverns.block.TavernBlocks;
import net.village_taverns.client.TavernsModClient;

public final class FabricClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TavernsModClient.init();
        // Fabric-specific block render layer registration
        BlockRenderLayerMap.INSTANCE.putBlock(TavernBlocks.BARREL.block(), RenderLayer.getCutout());
    }
}
