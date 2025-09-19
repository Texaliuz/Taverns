package net.village_taverns.neoforge;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.village_taverns.TavernVillagers;

public class NeoForgeTrades {
    public static void registerTrades() {
        NeoForge.EVENT_BUS.addListener(NeoForgeTrades::onVillagerTrades);
    }

    private static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == TavernVillagers.BAR_TENDER_PROFESSION) {
            var trades = TavernVillagers.TRADES.get(event.getLevel());
            if (trades != null) {
                event.getTrades().addAll(trades);
            }
        }
    }
}