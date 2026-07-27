package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.CopperGolemAttachments;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CopperGolem.class)
public abstract class CopperGolemWaxSyncMixin {

	private static final long IGNORE_WEATHERING_TICK = -2L;

	@Shadow
	private long nextWeatheringTick;

	@Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
	private void dynamicoutlines$syncWaxedOnLoad(ValueInput valueInput, CallbackInfo ci) {
		dynamicoutlines$syncWaxed();
	}

	@Inject(method = "mobInteract", at = @At("RETURN"))
	private void dynamicoutlines$syncWaxedOnInteract(Player player, InteractionHand hand,
			CallbackInfoReturnable<InteractionResult> cir) {
		dynamicoutlines$syncWaxed();
	}

	private void dynamicoutlines$syncWaxed() {
		if (((Entity) (Object) this).level().isClientSide()) {
			return;
		}
		((AttachmentTarget) (Object) this).setAttached(CopperGolemAttachments.WAXED,
				this.nextWeatheringTick == IGNORE_WEATHERING_TICK);
	}
}