package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.AnimalLoveAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.nautilus.AbstractNautilus;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractNautilus.class)
public abstract class NautilusLoveSyncMixin extends TamableAnimal implements AnimalLoveAccessor {

	@SuppressWarnings("unchecked")
	protected NautilusLoveSyncMixin(EntityType type, Level level) {
		super(type, level);
	}

	@Unique
	private static final EntityDataAccessor<Boolean> DYNAMICOUTLINES_IN_LOVE =
			SynchedEntityData.defineId(AbstractNautilus.class, EntityDataSerializers.BOOLEAN);

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void dynamicoutlines$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
		builder.define(DYNAMICOUTLINES_IN_LOVE, false);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void dynamicoutlines$syncInLove(CallbackInfo ci) {
		if (!this.level().isClientSide()) {
			Animal self = (Animal) (Object) this;
			this.entityData.set(DYNAMICOUTLINES_IN_LOVE, self.isInLove());
		}
	}

	@Unique
	@Override
	public boolean dynamicoutlines$isInLove() {
		return this.entityData.get(DYNAMICOUTLINES_IN_LOVE);
	}
}