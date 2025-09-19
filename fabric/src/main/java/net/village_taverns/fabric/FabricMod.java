package net.village_taverns.fabric;

import net.fabricmc.api.ModInitializer;

import net.village_taverns.TavernsMod;

public final class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Run our common setup.
        TavernsMod.init();
    }
}
