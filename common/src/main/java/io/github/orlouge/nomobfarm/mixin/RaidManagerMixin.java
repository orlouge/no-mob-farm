package io.github.orlouge.nomobfarm.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.orlouge.nomobfarm.NoMobFarmMod;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.poi.PointOfInterest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

@Mixin(net.minecraft.village.raid.RaidManager.class)
public class RaidManagerMixin {
    @Inject(method = "startRaid",
        at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"),
        cancellable = true)
    public void checkPOIExtent(ServerPlayerEntity player, BlockPos pos, CallbackInfoReturnable<Raid> cir, @Local List<PointOfInterest> poiList) {
        Iterator<PointOfInterest> iter = poiList.iterator();

        if (iter.hasNext()) {
            BlockPos firstPoi = iter.next().getPos();
            int ax = firstPoi.getX(), az = firstPoi.getZ(), bx = ax, bz = az;

            while (iter.hasNext()) {
                BlockPos poi = iter.next().getPos();

                ax = Integer.min(ax, poi.getX());
                az = Integer.min(az, poi.getZ());
                bx = Integer.max(bx, poi.getX());
                bz = Integer.max(bz, poi.getZ());
            }

            if ((bx - ax) * (bz - az) >= NoMobFarmMod.RAID_MIN_SIZE) {
                return;
            }
        }

        cir.setReturnValue(null);
        cir.cancel();
    }

    @ModifyArg(method = "startRaid",
               at = @At(value = "INVOKE", target = "Lnet/minecraft/village/raid/RaidManager;getOrCreateRaid(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/village/raid/Raid;"),
               index = 1)
    public BlockPos modifyRaidCenter(ServerWorld world, BlockPos pos) {
        return pos.add(
                randomizeCenterCoord(world),
                randomizeCenterCoord(world),
                randomizeCenterCoord(world)
        );
    }

    private int randomizeCenterCoord(ServerWorld world) {
        int extent = NoMobFarmMod.RAID_CENTER_MAX_RANDOMIZATION -
                     NoMobFarmMod.RAID_CENTER_MIN_RANDOMIZATION;
        if (extent < 1) {
            return 0;
        }
        int offset2 = world.random.nextInt(2 * extent),
            offset = offset2 - extent;
        return offset + Integer.signum(offset) * NoMobFarmMod.RAID_CENTER_MIN_RANDOMIZATION;

    }
}
