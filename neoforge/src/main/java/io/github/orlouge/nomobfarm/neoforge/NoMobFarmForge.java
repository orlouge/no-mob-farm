package io.github.orlouge.nomobfarm.neoforge;

import net.neoforged.fml.common.Mod;

import io.github.orlouge.nomobfarm.NoMobFarmMod;
import net.neoforged.fml.loading.FMLPaths;

@Mod(NoMobFarmMod.MOD_ID)
public final class NoMobFarmForge {
    public NoMobFarmForge() {
        // Run our common setup.
        NoMobFarmMod.init(FMLPaths.CONFIGDIR.get());
    }
}
