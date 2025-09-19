package net.village_taverns.neoforge;

import net.neoforged.fml.common.Mod;

import net.village_taverns.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
