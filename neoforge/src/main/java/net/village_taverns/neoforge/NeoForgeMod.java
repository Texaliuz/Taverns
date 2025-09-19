package net.village_taverns.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.village_taverns.TavernsMod;
import net.village_taverns.TavernVillagers;

@Mod(TavernsMod.ID)
public final class NeoForgeMod {
    public NeoForgeMod(IEventBus modBus) {
        TavernsMod.init();
        TavernsMod.registerBlocks();
        TavernsMod.registerPOI();

        modBus.addListener(RegisterEvent.class, NeoForgeMod::register);
    }

    private static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(net.minecraft.registry.RegistryKeys.VILLAGER_PROFESSION)) {
            TavernsMod.registerVillagers();
            NeoForgeTrades.registerTrades();
        }
    }
}