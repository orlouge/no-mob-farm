package io.github.orlouge.nomobfarm.forge;

import io.github.orlouge.nomobfarm.NoMobFarmMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(NoMobFarmMod.MOD_ID)
public final class NoMobFarmForge {
    public NoMobFarmForge() {
        // Run our common setup.
        NoMobFarmMod.init(FMLPaths.CONFIGDIR.get());
    }
}
