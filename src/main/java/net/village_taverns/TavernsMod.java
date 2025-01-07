package net.village_taverns;

import net.fabricmc.api.ModInitializer;
import net.village_taverns.block.TavernBlocks;

public class TavernsMod implements ModInitializer {

    public static final String ID = "village_taverns";

    @Override
    public void onInitialize() {
        TavernBlocks.register();
        TavernVillagers.register();
    }
}
