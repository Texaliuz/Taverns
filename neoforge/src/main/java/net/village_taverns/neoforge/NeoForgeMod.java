package net.village_taverns.neoforge;

import net.minecraft.registry.RegistryKeys;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.village_taverns.TavernsMod;
import net.village_taverns.TavernVillagers;

@Mod(TavernsMod.ID)
public final class NeoForgeMod {
    public NeoForgeMod(IEventBus modBus) {
        TavernsMod.init();
        modBus.addListener(RegisterEvent.class, NeoForgeMod::register);
    }

    public static void register(RegisterEvent event) {
        event.register(RegistryKeys.BLOCK, reg -> {
            TavernsMod.registerBlocks();
        });
        event.register(RegistryKeys.POINT_OF_INTEREST_TYPE, reg -> {
            // Not sure why errors are thrown, but this seems to fix it.
            try {
                TavernVillagers.registerPOI();
            } catch (Exception e) {
            }
        });
        event.register(RegistryKeys.VILLAGER_PROFESSION, reg -> {
            TavernVillagers.registerVillagers();
        });
    }
}
