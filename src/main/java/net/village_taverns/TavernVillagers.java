package net.village_taverns;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Schedule;
import net.minecraft.entity.ai.brain.ScheduleBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.village_taverns.block.TavernBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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

    private static final int POTION_PRICE_T1 = 16;
    private static final int POTION_PRICE_T2 = 24;
    private static final int POTION_PRICE_T3 = 32;
    private static final int POTION_PRICE_T4 = 40;

    public static void register() {
        var poi = registerPOI(BAR_TENDER, TavernBlocks.BARREL.block());
        var scheduleBuilder = new ScheduleBuilder(ALWAYS_WORK_SCHEDULE).withActivity(50, Activity.WORK).withActivity(23950, Activity.REST).build();
        Registry.register(Registries.SCHEDULE, Identifier.of(TavernsMod.ID, ALWAYS_WORK), ALWAYS_WORK_SCHEDULE);

        var profession = registerProfession(
                BAR_TENDER,
                RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), Identifier.of(TavernsMod.ID, BAR_TENDER)));
        BAR_TENDER_PROFESSION = profession;

        LinkedHashMap<Integer, List<TradeOffers.Factory>> trades = new LinkedHashMap<>();


        var trades_level_1 = new ArrayList<TradeOffers.Factory>();
        trades_level_1.add(new TradeOffers.SellItemFactory(Items.COOKED_CHICKEN, 2, 1, 12, 10));
        trades_level_1.add(new TradeOffers.SellItemFactory(Items.COOKED_BEEF, 4, 1, 12, 10));
        trades_level_1.add(new TradeOffers.SellItemFactory(Items.BREAD, 4, 1, 12, 10));
        trades_level_1.add(new TradeOffers.SellItemFactory(Items.COOKED_RABBIT, 6, 1, 12, 10));
        trades.put(1, trades_level_1);

        var trades_level_2 = new ArrayList<TradeOffers.Factory>();
        trades_level_2.add(potionOffer(Potions.STRENGTH, POTION_PRICE_T1, 1, 3, 20));
        trades_level_2.add(potionOffer(Potions.REGENERATION, POTION_PRICE_T1, 1, 3, 20));
        trades_level_2.add(potionOffer(Potions.SWIFTNESS, POTION_PRICE_T1, 1, 3, 20));
        trades_level_2.add(potionOffer(Potions.FIRE_RESISTANCE, POTION_PRICE_T1, 1, 3, 20));
        trades.put(2, trades_level_2);

        var trades_level_3 = new ArrayList<TradeOffers.Factory>();
        addIfNotNull(trades_level_3, potionOffer("spell_power:spell_power.arcane", POTION_PRICE_T2, 1, 3, 30));
        addIfNotNull(trades_level_3, potionOffer("spell_power:spell_power.fire", POTION_PRICE_T2, 1, 3, 30));
        addIfNotNull(trades_level_3, potionOffer("spell_power:spell_power.frost", POTION_PRICE_T2, 1, 3, 30));
        addIfNotNull(trades_level_3, potionOffer("spell_power:spell_power.healing", POTION_PRICE_T2, 1, 3, 30));

        if (trades_level_3.isEmpty()) {
            trades_level_3.add(potionOffer(Potions.HARMING, POTION_PRICE_T2, 1, 3, 30));
        }
        trades.put(3, trades_level_3);

        var trades_level_4 = new ArrayList<TradeOffers.Factory>();
        addIfNotNull(trades_level_4, potionOffer("spell_power:spell_power.critical_chance", POTION_PRICE_T3, 1, 3, 30));
        addIfNotNull(trades_level_4, potionOffer("spell_power:spell_power.critical_damage", POTION_PRICE_T3, 1, 3, 30));
        addIfNotNull(trades_level_4, potionOffer("ranged_weapon:ranged_weapon.damage", POTION_PRICE_T3, 1, 3, 30));
        if (trades_level_4.isEmpty()) {
            trades_level_4.add(potionOffer(Potions.LONG_REGENERATION, POTION_PRICE_T3, 1, 3, 30));
        }
        trades.put(4, trades_level_4);

        var trades_level_5 = new ArrayList<TradeOffers.Factory>();
        addIfNotNull(trades_level_5, potionOffer("spell_power:spell_power.haste", POTION_PRICE_T4, 1, 3, 30));
        addIfNotNull(trades_level_5, potionOffer("ranged_weapon:ranged_weapon.haste", POTION_PRICE_T4, 1, 3, 30));
        trades_level_5.add(new TradeOffers.SellItemFactory(Items.OMINOUS_BOTTLE, 60, 1, 1, 40));
        trades_level_5.add(potionOffer(Potions.LONG_FIRE_RESISTANCE, POTION_PRICE_T4, 1, 3, 40));
        trades.put(5, trades_level_5);

        for (var entry: trades.entrySet()) {
            TradeOfferHelper.registerVillagerOffers(profession, entry.getKey(), factories -> {
                factories.addAll(entry.getValue());
            });
        }
    }

    private static <T> void addIfNotNull(List<T> list, T item) {
        if (item != null) {
            list.add(item);
        }
    }

    private static TradeOffers.SellItemFactory potionOffer(String potionId, int price, int count, int maxUses, int experience) {
        var stack = createPotionStack(potionId);
        if (stack != null) {
            return new TradeOffers.SellItemFactory(stack, price, count, maxUses, experience);
        }
        return null;
    }

    private static TradeOffers.SellItemFactory potionOffer(RegistryEntry<Potion> potion, int price, int count, int maxUses, int experience) {
        var stack = createPotionStack(potion);
        return new TradeOffers.SellItemFactory(stack, price, count, maxUses, experience);
    }

    @Nullable
    private static ItemStack createPotionStack(String potionId) {
        var id = Identifier.of(potionId);
        var potion = Registries.POTION.getEntry(id);
        return potion
                .map(potionReference -> PotionContentsComponent.createStack(Items.POTION, potionReference))
                .orElse(null);
    }

    private static ItemStack createPotionStack(RegistryEntry<Potion> potion) {
        return PotionContentsComponent.createStack(Items.POTION, potion);
    }
}
