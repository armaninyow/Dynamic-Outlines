package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.HorseTemperAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorse.class)
public abstract class HorseTemperSyncMixin extends Animal implements HorseTemperAccessor {

	@SuppressWarnings("unchecked")
	protected HorseTemperSyncMixin(EntityType type, Level level) {
		super(type, level);
	}

	@Unique
	private static final EntityDataAccessor<Integer> DYNAMICOUTLINES_TEMPER =
			SynchedEntityData.defineId(AbstractHorse.class, EntityDataSerializers.INT);

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void dynamicoutlines$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
		builder.define(DYNAMICOUTLINES_TEMPER, 0);
	}

	@Inject(method = "setTemper", at = @At("TAIL"))
	private void dynamicoutlines$setTemper(int temper, CallbackInfo ci) {
		this.entityData.set(DYNAMICOUTLINES_TEMPER, temper);
	}

	@Unique
	@Override
	public int dynamicoutlines$getSyncedTemper() {
		return this.entityData.get(DYNAMICOUTLINES_TEMPER);
	}
}