package com.armaninyow.dynamicoutlines.render;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;

public final class FluidOutlineRenderer {
	private FluidOutlineRenderer() {
	}

	public static void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, Vec3 camPos) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		ClientLevel level = minecraft.level;
		if (player == null || level == null) {
			return;
		}

		ItemStack mainHand = player.getMainHandItem();
		ModConfig cfg = ModConfig.get();

		boolean isBucket = mainHand.is(Items.BUCKET);
		boolean isBottle = mainHand.is(Items.GLASS_BOTTLE);
		boolean isBoat = mainHand.getItem() instanceof BoatItem;

		if (!isBucket && !isBottle && !isBoat) {
			return;
		}

		boolean wantsInteractiveColor = isBucket || isBottle;
		if (wantsInteractiveColor && !cfg.blockOutlineMode.showsInteractive()) {
			return;
		}
		if (isBoat && !cfg.blockOutlineMode.showsNonInteractive()) {
			return;
		}

		BlockHitResult fluidHit = pickFluid(player, level);
		if (fluidHit == null || fluidHit.getType() != HitResult.Type.BLOCK) {
			return;
		}

		BlockPos fluidPos = fluidHit.getBlockPos();
		FluidState hitFluidState = level.getFluidState(fluidPos);
		if (!hitFluidState.isSource()) {
			return;
		}
		if (isBottle && !hitFluidState.is(FluidTags.WATER)) {
			return;
		}

		Vec3 eyePos = player.getEyePosition(1.0F);

		if (minecraft.hitResult instanceof BlockHitResult solidHit && solidHit.getType() == HitResult.Type.BLOCK) {
			double distSqToSolid = eyePos.distanceToSqr(Vec3.atCenterOf(solidHit.getBlockPos()));
			double distSqToFluid = eyePos.distanceToSqr(Vec3.atCenterOf(fluidPos));
			if (distSqToSolid < distSqToFluid) {
				return;
			}
		}

		int rgb = wantsInteractiveColor ? cfg.interactiveColor : cfg.nonInteractiveColor;
		rgb = com.armaninyow.dynamicoutlines.util.OutlineLogic.applyLightBlend(rgb, level,
				com.armaninyow.dynamicoutlines.util.OutlineLogic.lightSamplePos(fluidPos, fluidHit.getDirection()));

		int alpha = Math.round(cfg.outlineAlpha / 100F * 255F);
		int argbColor = (alpha << 24) | (rgb & 0xFFFFFF);

		poseStack.pushPose();
		poseStack.translate(fluidPos.getX() - camPos.x, fluidPos.getY() - camPos.y, fluidPos.getZ() - camPos.z);
		submitNodeCollector.submitShapeOutline(poseStack, Shapes.block(), RenderTypes.lines(), argbColor,
				cfg.strokeWidth, false);
		poseStack.popPose();
	}

	public static BlockHitResult pickFluid(Player player, ClientLevel level) {
		if (cacheValid && cachedForPlayer == player) {
			return cachedResult;
		}
		double reach = player.blockInteractionRange();
		Vec3 eyePos = player.getEyePosition(1.0F);
		Vec3 viewVec = player.getViewVector(1.0F);
		Vec3 endPos = eyePos.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);
		ClipContext ctx = new ClipContext(eyePos, endPos, ClipContext.Block.OUTLINE, ClipContext.Fluid.SOURCE_ONLY, player);
		BlockHitResult result = level.clip(ctx);
		cachedResult = result;
		cachedForPlayer = player;
		cacheValid = true;
		return result;
	}

	private static BlockHitResult cachedResult;
	private static Player cachedForPlayer;
	private static boolean cacheValid;

	public static void invalidateFluidCache() {
		cacheValid = false;
		cachedResult = null;
		cachedForPlayer = null;
	}
}