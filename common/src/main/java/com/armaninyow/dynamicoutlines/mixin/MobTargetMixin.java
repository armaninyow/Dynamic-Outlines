package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.MobTargetAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class MobTargetMixin extends LivingEntity implements MobTargetAccessor {

	@SuppressWarnings("unchecked")
	protected MobTargetMixin(EntityType type, Level level) {
		super(type, level);
	}

	@Unique
	private static final EntityDataAccessor<Boolean> DYNAMICOUTLINES_HAS_TARGET =
			SynchedEntityData.defineId(Mob.class, EntityDataSerializers.BOOLEAN);

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void dynamicoutlines$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
		builder.define(DYNAMICOUTLINES_HAS_TARGET, false);
	}

	@Inject(method = "setTarget", at = @At("TAIL"))
	private void dynamicoutlines$setTarget(@Nullable LivingEntity target, CallbackInfo ci) {
		this.entityData.set(DYNAMICOUTLINES_HAS_TARGET, target != null);
	}

	@Unique
	@Override
	public boolean dynamicoutlines$hasTarget() {
		return this.entityData.get(DYNAMICOUTLINES_HAS_TARGET);
	}
}