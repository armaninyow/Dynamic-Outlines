package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.AnimalLoveTracker;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public abstract class AnimalHandleEntityEventMixin {

	@Inject(method = "handleEntityEvent", at = @At("HEAD"))
	private void dynamicoutlines$trackLoveSuccess(byte b, CallbackInfo ci) {
		if (b == 18) {
			Animal self = (Animal) (Object) this;
			AnimalLoveTracker.markJustSucceeded(self.getUUID(), self.level().getGameTime());
		}
	}
}