package com.armaninyow.dynamicoutlines.mixin;

import net.minecraft.world.entity.vehicle.MinecartFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecartFurnace.class)
public interface MinecartFurnaceAccessor {
	@Accessor("fuel")
	int dynamicoutlines$getFuel();

	@Accessor("fuel")
	void dynamicoutlines$setFuel(int fuel);
}