package com.armaninyow.dynamicoutlines.mixin;

import net.minecraft.world.entity.animal.golem.CopperGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CopperGolem.class)
public interface CopperGolemAccessor {
	@Accessor("nextWeatheringTick")
	long dynamicoutlines$getNextWeatheringTick();
}