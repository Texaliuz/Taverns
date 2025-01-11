package net.village_taverns.mixin;

import net.fabric_extras.ranged_weapon.RangedWeaponMod;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.potion.Potions;
import net.spell_power.SpellPowerMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Potions.class)
public class PotionsMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void static_tail_SpellPower(CallbackInfo ci) {
        if (FabricLoader.getInstance().isModLoaded("spell_power")) {
            try {
                SpellPowerMod.registerPotions();
            } catch (Throwable t) { }
        }
        if (FabricLoader.getInstance().isModLoaded("ranged_weapon")) {
            try {
                RangedWeaponMod.registerPotions();
            } catch (Throwable t) { }
        }
    }
}
