package net.village_taverns.fabric;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.village_taverns.TavernVillagers;

public class FabricTrades {
    public static void registerTrades() {
        TavernVillagers.TRADES.forEach((level, trades) -> {
            TradeOfferHelper.registerVillagerOffers(TavernVillagers.BAR_TENDER_PROFESSION, level, factories -> {
                factories.addAll(trades);
            });
        });
    }
}