package net.village_taverns.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.village_taverns.block.TavernBlocks;

public class TavernsModClient {
    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(TavernBlocks.BARREL.block(), RenderLayer.getCutout());
    }
}
