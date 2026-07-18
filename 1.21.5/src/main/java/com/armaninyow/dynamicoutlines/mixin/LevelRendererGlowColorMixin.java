package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import com.armaninyow.dynamicoutlines.util.EntityOutlineCategory;
import com.armaninyow.dynamicoutlines.util.OutlineLogic;
import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.blaze3d.systems.RenderPass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererGlowColorMixin {
	@Shadow
	@Final
	private Minecraft minecraft;

	@Redirect(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/PostChain;addToFrame(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;IILnet/minecraft/client/renderer/PostChain$TargetBundle;Ljava/util/function/Consumer;)V", ordinal = 0))
	private void dynamicoutlines$setStrokeWidthUniform(PostChain chain, FrameGraphBuilder frameGraphBuilder, int width, int height, PostChain.TargetBundle targetBundle, Consumer<RenderPass> ignoredNullConsumer) {
		ModConfig cfg = ModConfig.get();
		chain.addToFrame(frameGraphBuilder, width, height, targetBundle,
				renderPass -> renderPass.setUniform("Radius", cfg.strokeWidth * 0.25F));
	}

	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getTeamColor()I"))
	private int dynamicoutlines$modifyGlowColor(Entity entity) {
		ModConfig cfg = ModConfig.get();
		if (entity.isCurrentlyGlowing()) {
			return entity.getTeamColor();
		}
		if (cfg.entityOutlineMode.showsAny() && entity == this.minecraft.crosshairPickEntity) {
			ItemStack mainHand = this.minecraft.player != null ? this.minecraft.player.getMainHandItem() : ItemStack.EMPTY;
			EntityOutlineCategory category = EntityOutlineCategory.of(entity, mainHand, this.minecraft);

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

	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OutlineBufferSource;setColor(IIII)V"))
	private void dynamicoutlines$modifyGlowAlpha(OutlineBufferSource outlineBufferSource, int r, int g, int b, int a) {
		ModConfig cfg = ModConfig.get();
		int adjustedAlpha = Math.round(255 * (cfg.outlineAlpha / 100F));
		outlineBufferSource.setColor(r, g, b, adjustedAlpha);
	}
}