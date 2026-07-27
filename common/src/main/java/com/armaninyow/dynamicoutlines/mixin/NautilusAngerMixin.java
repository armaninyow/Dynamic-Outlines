package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.MobTargetAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.nautilus.AbstractNautilus;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractNautilus.class)
public abstract class NautilusAngerMixin extends TamableAnimal implements MobTargetAccessor {

	@SuppressWarnings("unchecked")
	protected NautilusAngerMixin(EntityType type, Level level) {
		super(type, level);
	}

	@Unique
	private static final EntityDataAccessor<Boolean> DYNAMICOUTLINES_NAUTILUS_ANGRY =
			SynchedEntityData.defineId(AbstractNautilus.class, EntityDataSerializers.BOOLEAN);

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void dynamicoutlines$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
		builder.define(DYNAMICOUTLINES_NAUTILUS_ANGRY, false);
	}

	@Inject(method = "hurtServer", at = @At("TAIL"))
	private void dynamicoutlines$hurtServer(ServerLevel level, DamageSource source, float damage,
			CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue() && source.getEntity() instanceof LivingEntity) {
			this.entityData.set(DYNAMICOUTLINES_NAUTILUS_ANGRY, true);
		}
	}

	@Inject(method = "customServerAiStep", at = @At("TAIL"))
	private void dynamicoutlines$customServerAiStep(ServerLevel level, CallbackInfo ci) {
		boolean angry = this.getBrain().hasMemoryValue(MemoryModuleType.ANGRY_AT)
				|| this.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET);
		this.entityData.set(DYNAMICOUTLINES_NAUTILUS_ANGRY, angry);
	}

	@Unique
	@Override
	public boolean dynamicoutlines$isNautilusAngry() {
		return this.entityData.get(DYNAMICOUTLINES_NAUTILUS_ANGRY);
	}

	@Unique
	@Override
	public boolean dynamicoutlines$hasTarget() {
		return false;
	}
}