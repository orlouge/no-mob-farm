package io.github.orlouge.nomobfarm.fabric;

import io.github.orlouge.nomobfarm.NoMobFarmMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class NoMobFarmFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NoMobFarmMod.init(FabricLoader.getInstance().getConfigDir());
    }
}
