package net.village_taverns;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Schedule;
import net.minecraft.entity.ai.brain.ScheduleBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.village_taverns.block.TavernBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;

public class TavernVillagers {
    public static final String BAR_TENDER = "bartender";
    public static final String ALWAYS_WORK = "always_work";
    public static final Schedule ALWAYS_WORK_SCHEDULE = new Schedule();
    @Nullable public static VillagerProfession BAR_TENDER_PROFESSION;

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(TavernsMod.ID, name),
                1, 10, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> workStation) {
        var id = Identifier.of(TavernsMod.ID, name);
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(TavernsMod.ID, name), new VillagerProfession(
                id.toString(),
                (entry) -> {
                    return entry.matchesKey(workStation);
                },
                (entry) -> {
                    return entry.matchesKey(workStation);
                },
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundEvents.ITEM_BOTTLE_FILL)
        );
    }

    public static void register() {
        var poi = registerPOI(BAR_TENDER, TavernBlocks.BARREL.block());
        var scheduleBuilder = new ScheduleBuilder(ALWAYS_WORK_SCHEDULE).withActivity(50, Activity.WORK).withActivity(23950, Activity.REST).build();
        Registry.register(Registries.SCHEDULE, Identifier.of(TavernsMod.ID, ALWAYS_WORK), ALWAYS_WORK_SCHEDULE);

        var profession = registerProfession(
                BAR_TENDER,
                RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), Identifier.of(TavernsMod.ID, BAR_TENDER)));
        BAR_TENDER_PROFESSION = profession;

        LinkedHashMap<Integer, List<TradeOffers.Factory>> trades = new LinkedHashMap<>();


        trades.put(1, List.of(
                new TradeOffers.SellItemFactory(Items.COOKED_CHICKEN, 4, 1, 12, 10),
                new TradeOffers.SellItemFactory(Items.COOKED_BEEF, 4, 1, 12, 10),
                new TradeOffers.SellItemFactory(Items.COOKED_RABBIT, 4, 1, 12, 10)
        ));
        trades.put(2, List.of(
                new TradeOffers.BuyItemFactory(Items.POTION, 7, 8, 2, 8),
                // public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
                new TradeOffers.SellItemFactory(new ItemStack(Items.POTION), 10, 1, 6, 5)
        ));
        trades.put(3, List.of(
                new TradeOffers.BuyItemFactory(Items.DIAMOND, 1, 12, 10, 10)
        ));
        trades.put(4, List.of(
        ));
        trades.put(5, List.of(
        ));

//        trades.put(1, List.of(
//                new TradeOffers.BuyItemFactory(Items.COPPER_INGOT, 8, 8, 3, 2),
//                new TradeOffers.BuyItemFactory(Items.STRING, 7, 6, 3, 2),
//                new TradeOffers.SellItemFactory(JewelryItems.copper_ring.item(), 4, 1, 12, 4)
//        ));
//        trades.put(2, List.of(
//                new TradeOffers.BuyItemFactory(Items.GOLD_INGOT, 7, 8, 2, 8),
//                new TradeOffers.SellItemFactory(JewelryItems.iron_ring.item(), 4, 1, 6, 5),
//                new TradeOffers.SellItemFactory(JewelryItems.gold_ring.item(), 18, 1, 6, 5)
//        ));
//        trades.put(3, List.of(
//                new TradeOffers.BuyItemFactory(Items.DIAMOND, 1, 12, 10, 10),
//                new TradeOffers.SellItemFactory(JewelryItems.emerald_necklace.item(), 20, 1, 12, 10),
//                new TradeOffers.SellItemFactory(JewelryItems.diamond_necklace.item(), 25, 1, 12, 10)
//        ));
//        trades.put(4, List.of(
//                new TradeOffers.SellItemFactory(JewelryItems.ruby_ring.item(), 35, 1, 5, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.topaz_ring.item(), 35, 1, 5, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.citrine_ring.item(), 35, 1, 5, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.jade_ring.item(), 35, 1, 5, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.sapphire_ring.item(), 35, 1, 5, 13),
//                new TradeOffers.SellItemFactory(JewelryItems.tanzanite_ring.item(), 35, 1, 5, 13)
//        ));
//        trades.put(5, List.of(
//                new TradeOffers.SellItemFactory(JewelryItems.ruby_necklace.item(), 45, 1, 3, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.topaz_necklace.item(), 45, 1, 3, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.citrine_necklace.item(), 45, 1, 3, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.jade_necklace.item(), 45, 1, 3, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.sapphire_necklace.item(), 45, 1, 3, 15),
//                new TradeOffers.SellItemFactory(JewelryItems.tanzanite_necklace.item(), 45, 1, 3, 15)
//        ));

        for (var entry: trades.entrySet()) {
            TradeOfferHelper.registerVillagerOffers(profession, entry.getKey(), factories -> {
                factories.addAll(entry.getValue());
            });
        }
    }
}
