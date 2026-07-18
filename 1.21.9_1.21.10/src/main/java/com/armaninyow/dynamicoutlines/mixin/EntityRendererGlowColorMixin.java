package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import com.armaninyow.dynamicoutlines.util.EntityOutlineCategory;
import com.armaninyow.dynamicoutlines.util.OutlineLogic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererGlowColorMixin {

	@Redirect(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getTeamColor()I"))
	private int dynamicoutlines$modifyGlowColor(Entity entity) {
		Minecraft minecraft = Minecraft.getInstance();
		ModConfig cfg = ModConfig.get();
		if (entity.isCurrentlyGlowing()) {
			return entity.getTeamColor();
		}
		if (cfg.entityOutlineMode.showsAny() && entity == minecraft.crosshairPickEntity) {
			ItemStack mainHand = minecraft.player != null ? minecraft.player.getMainHandItem() : ItemStack.EMPTY;
			EntityOutlineCategory category = EntityOutlineCategory.of(entity, mainHand, minecraft);

			int color;
			if (category == EntityOutlineCategory.ENTITY_INTERACTION) {
				color = cfg.entityInteractionColor;
			} else {
				color = OutlineLogic.isHostile(entity) ? cfg.enemyColor : cfg.entityColor;
			}
			return 0xFF000000 | (color & 0xFFFFFF);
		}
		return entity.getTeamColor();
	}
}