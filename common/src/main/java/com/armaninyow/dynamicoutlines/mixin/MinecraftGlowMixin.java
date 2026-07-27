package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import com.armaninyow.dynamicoutlines.util.EntityOutlineCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftGlowMixin {
	@Shadow
	public Entity crosshairPickEntity;

	@Shadow
	public net.minecraft.client.player.LocalPlayer player;

	@Inject(method = "shouldEntityAppearGlowing", at = @At("HEAD"), cancellable = true)
	private void dynamicoutlines$forceGlowOnCrosshairTarget(Entity entity, CallbackInfoReturnable<Boolean> cir) {
		ModConfig cfg = ModConfig.get();
		if (!cfg.entityOutlineMode.showsAny() || entity != this.crosshairPickEntity) {
			return;
		}
		if (!cfg.outlineInvisibleMobs && entity.isInvisible()) {
			return;
		}

		ItemStack mainHand = this.player != null ? this.player.getMainHandItem() : ItemStack.EMPTY;
		EntityOutlineCategory category = EntityOutlineCategory.of(entity, mainHand, (Minecraft) (Object) this);

		boolean shouldGlow = category == EntityOutlineCategory.ENTITY_INTERACTION
				? cfg.entityOutlineMode.showsEntityInteraction()
				: cfg.entityOutlineMode.showsPassiveNeutralHostile();

		if (shouldGlow) {
			cir.setReturnValue(true);
		}
	}
}