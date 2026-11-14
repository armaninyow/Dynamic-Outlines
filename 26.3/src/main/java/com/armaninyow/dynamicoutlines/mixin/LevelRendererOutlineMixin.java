package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import com.armaninyow.dynamicoutlines.render.FluidOutlineRenderer;
import com.armaninyow.dynamicoutlines.util.OutlineLogic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererOutlineMixin {

	@Inject(method = "submitBlockOutline", at = @At("HEAD"), cancellable = true)
	private void dynamicoutlines$submitBlockOutline(PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
			LevelRenderState levelRenderState, CallbackInfo ci) {

		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		if (level == null) {
			return;
		}

		FluidOutlineRenderer.invalidateFluidCache();

		Vec3 camPos = levelRenderState.cameraRenderState.pos;
		ItemStack mainHand = minecraft.player != null ? minecraft.player.getMainHandItem() : ItemStack.EMPTY;
		ModConfig cfg = ModConfig.get();

		FluidOutlineRenderer.render(poseStack, submitNodeCollector, camPos);

		BlockOutlineRenderState state = levelRenderState.blockOutlineRenderState;
		if (state == null) {
			ci.cancel();
			return;
		}

		if (cfg.blockOutlineMode == ModConfig.BlockOutlineMode.NONE) {
			ci.cancel();
			return;
		}

		BlockPos blockPos = state.pos();
		BlockState blockState = level.getBlockState(blockPos);
		VoxelShape shape = state.shape();
		if (shape.isEmpty()) {
			ci.cancel();
			return;
		}

		boolean itemInteractsWithFluid = mainHand.is(Items.BUCKET) || mainHand.is(Items.GLASS_BOTTLE)
				|| mainHand.getItem() instanceof BoatItem;
		if (itemInteractsWithFluid && minecraft.player != null) {
			BlockHitResult fluidHit = FluidOutlineRenderer.pickFluid(minecraft.player, level);
			if (fluidHit != null && fluidHit.getType() == HitResult.Type.BLOCK) {
				BlockPos fluidPos = fluidHit.getBlockPos();
				if (level.getFluidState(fluidPos).isSource()) {
					Vec3 eyePos = minecraft.player.getEyePosition(1.0F);
					double distSqToFluid = eyePos.distanceToSqr(Vec3.atCenterOf(fluidPos));
					double distSqToSolid = eyePos.distanceToSqr(Vec3.atCenterOf(blockPos));
					if (distSqToFluid <= distSqToSolid) {
						ci.cancel();
						return;
					}
				}
			}
		}

		BlockHitResult hitResult = minecraft.hitResult instanceof BlockHitResult blockHitResult ? blockHitResult : null;

		boolean interactive = OutlineLogic.isBlockCurrentlyInteractive(blockState, mainHand, level, blockPos, minecraft.player, hitResult);
		if (!interactive && !OutlineLogic.isMainHandOnlyBlockInteraction(blockState, mainHand)) {
			ItemStack offHand = minecraft.player != null ? minecraft.player.getOffhandItem() : ItemStack.EMPTY;
			if (!offHand.isEmpty()) {
				interactive = OutlineLogic.isBlockCurrentlyInteractive(blockState, offHand, level, blockPos, minecraft.player, hitResult);
			}
		}
		boolean currentlyBreaking = cfg.currentlyBreakingOverride && OutlineLogic.isCurrentlyBreakingThisBlock(blockPos, minecraft);
		if (interactive && currentlyBreaking) {
			interactive = false;
		}

		int rgb;
		if (interactive) {
			if (!cfg.blockOutlineMode.showsInteractive()) {
				ci.cancel();
				return;
			}
			rgb = cfg.interactiveColor;
		} else {
			if (!cfg.blockOutlineMode.showsNonInteractive()) {
				ci.cancel();
				return;
			}
			if (!cfg.showOutlineWhenPlacementBlocked
					&& OutlineLogic.isPlacementBlocked(level, blockPos, mainHand, minecraft.player, hitResult)) {
				ci.cancel();
				return;
			}
			boolean missingTool = OutlineLogic.isMissingToolForDrops(blockState, mainHand, level);
			boolean shouldHide = switch (cfg.missingToolVisibility) {
				case ALWAYS_SHOW -> false;
				case ALWAYS_HIDE -> missingTool;
			case HIDE_UNLESS_PLACING -> missingTool && (currentlyBreaking || !OutlineLogic.isPlacementItem(mainHand, blockState));
			};
			if (shouldHide) {
				ci.cancel();
				return;
			}
			rgb = cfg.nonInteractiveColor;
		}

		rgb = OutlineLogic.applyLightBlend(rgb, level,
				OutlineLogic.lightSamplePos(blockPos, hitResult != null ? hitResult.getDirection() : null));

		int alpha = Math.round(cfg.outlineAlpha / 100F * 255F);
		int argbColor = (alpha << 24) | (rgb & 0xFFFFFF);

		VoxelShape combinedShape = shape;
		Optional<BlockPos> pairedPos = OutlineLogic.getPairedPos(blockPos, blockState);
		if (pairedPos.isPresent()) {
			BlockPos otherPos = pairedPos.get();
			BlockState otherState = level.getBlockState(otherPos);
			VoxelShape otherShape = otherState.getShape(level, otherPos, CollisionContext.of(minecraft.player));
			if (!otherShape.isEmpty()) {
				double ox = otherPos.getX() - blockPos.getX();
				double oy = otherPos.getY() - blockPos.getY();
				double oz = otherPos.getZ() - blockPos.getZ();
				VoxelShape movedOtherShape = otherShape.move(ox, oy, oz);
				combinedShape = Shapes.or(shape, movedOtherShape);
			}
		}
		if (combinedShape != shape) {
			combinedShape = combinedShape.optimize();
		}

		poseStack.pushPose();
		poseStack.translate(blockPos.getX() - camPos.x, blockPos.getY() - camPos.y, blockPos.getZ() - camPos.z);
		submitNodeCollector.submitShapeOutline(poseStack, combinedShape, RenderTypes.lines(), argbColor,
				cfg.strokeWidth, state.isTranslucent());
		poseStack.popPose();

		ci.cancel();
	}
}