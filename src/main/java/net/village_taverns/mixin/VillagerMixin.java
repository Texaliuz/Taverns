package net.village_taverns.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.Schedule;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.VillagerData;
import net.village_taverns.TavernVillagers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VillagerEntity.class)
public abstract class VillagerMixin {
    @Shadow public abstract VillagerData getVillagerData();
    
    @WrapOperation(
            method = "initBrain",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/brain/Brain;setSchedule(Lnet/minecraft/entity/ai/brain/Schedule;)V")
    )
    private void wrapInitBrain(Brain instance, Schedule schedule, Operation<Void> original) {
        var profession = getVillagerData().getProfession();
        if (profession.equals(TavernVillagers.BAR_TENDER_PROFESSION)) {
            original.call(instance, TavernVillagers.ALWAYS_WORK_SCHEDULE);
        } else {
            original.call(instance, schedule);
        }
    }
}
