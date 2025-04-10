package net.village_taverns.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.natamus.collective_common_fabric.functions.CommandFunctions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.village_taverns.TavernsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "com.natamus.collective_common_fabric.functions.BlockPosFunctions")
public class BlockPosUtilMixin {
    @WrapOperation(
            method = "getNearbyVillage",
            at = @At(value = "INVOKE", target = "Lcom/natamus/collective_common_fabric/functions/CommandFunctions;getRawCommandOutput(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;Ljava/lang/String;)Ljava/lang/String;"),
            require = 0 // Make this optional
    )
    private static String getNearbyVillage_WRAP_Command(ServerWorld serverLevel, Vec3d nearPos, String command, Operation<String> original) {
        if (TavernsMod.getSecretConfig().override_collective_spawn_village) {
            return CommandFunctions.getRawCommandOutput(serverLevel, nearPos, "/locate structure #minecraft:spawn_village");
        } else {
            return original.call(serverLevel, nearPos, command);
        }
    }
}
