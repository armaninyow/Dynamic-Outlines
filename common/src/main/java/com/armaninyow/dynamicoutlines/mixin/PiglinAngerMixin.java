package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.MobTargetAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractPiglin.class)
public abstract class PiglinAngerMixin extends Monster implements MobTargetAccessor {

	@SuppressWarnings("unchecked")
	protected PiglinAngerMixin(EntityType type, Level level) {
		super(type, level);
	}

	@Unique
	private static final EntityDataAccessor<Boolean> DYNAMICOUTLINES_PIGLIN_ANGRY =
			SynchedEntityData.defineId(AbstractPiglin.class, EntityDataSerializers.BOOLEAN);

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void dynamicoutlines$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
		builder.define(DYNAMICOUTLINES_PIGLIN_ANGRY, false);
	}

	@Inject(method = "customServerAiStep", at = @At("TAIL"))
	private void dynamicoutlines$customServerAiStep(ServerLevel level, CallbackInfo ci) {
		boolean angry = this.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET)
				|| this.getBrain().hasMemoryValue(MemoryModuleType.ANGRY_AT);
		this.entityData.set(DYNAMICOUTLINES_PIGLIN_ANGRY, angry);
	}

	@Unique
	@Override
	public boolean dynamicoutlines$hasTarget() {
		return this.entityData.get(DYNAMICOUTLINES_PIGLIN_ANGRY);
	}
}