package com.armaninyow.dynamicoutlines.mixin;

import net.minecraft.world.entity.AgeableMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AgeableMob.class)
public interface AgeableMobAccessor {
	@Accessor("ageLockParticleTimer")
	int dynamicoutlines$getAgeLockParticleTimer();
}