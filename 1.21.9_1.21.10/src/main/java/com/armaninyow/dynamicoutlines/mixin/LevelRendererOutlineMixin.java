package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import com.armaninyow.dynamicoutlines.render.FluidOutlineRenderer;
import com.armaninyow.dynamicoutlines.render.OutlineRenderer;
import com.armaninyow.dynamicoutlines.render.DynamicLineRenderType;
import com.armaninyow.dynamicoutlines.util.OutlineLogic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.state.BlockOutlineRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererOutlineMixin {
	@Shadow
	@Final
	private Minecraft minecraft;

	@Shadow
	private ClientLevel level;

	@Shadow
	@Final
	private RenderBuffers renderBuffers;

	@Inject(method = "renderHitOutline", at = @At("HEAD"), cancellable = true)
	private void dynamicoutlines$renderHitOutline(PoseStack poseStack, VertexConsumer vertexConsumer,
			double camX, double camY, double camZ, BlockOutlineRenderState renderState, int packedLight, CallbackInfo ci) {

		ModConfig cfg = ModConfig.get();
		if (cfg.blockOutlineMode == ModConfig.BlockOutlineMode.NONE) {
			ci.cancel();
			return;
		}

		BlockPos blockPos = renderState.pos();
		BlockState blockState = this.level.getBlockState(blockPos);
		ItemStack mainHand = this.minecraft.player != null ? this.minecraft.player.getMainHandItem() : ItemStack.EMPTY;

		VoxelShape shape = renderState.shape();
		if (shape.isEmpty()) {
			return;
		}

		boolean itemInteractsWithFluid = mainHand.is(Items.BUCKET) || mainHand.is(Items.GLASS_BOTTLE)
				|| mainHand.getItem() instanceof net.minecraft.world.item.BoatItem;
		if (itemInteractsWithFluid && this.minecraft.player != null) {
			BlockHitResult fluidHit = FluidOutlineRenderer.pickFluid(this.minecraft.player, this.level);
			if (fluidHit != null && fluidHit.getType() == HitResult.Type.BLOCK) {
				BlockPos fluidPos = fluidHit.getBlockPos();
				if (this.level.getFluidState(fluidPos).isSource()) {
					Vec3 eyePos = this.minecraft.player.getEyePosition(1.0F);
					double distSqToFluid = eyePos.distanceToSqr(Vec3.atCenterOf(fluidPos));
					double distSqToSolid = eyePos.distanceToSqr(Vec3.atCenterOf(blockPos));
					if (distSqToFluid <= distSqToSolid) {
						ci.cancel();
						return;
					}
				}
			}
		}

		BlockHitResult hitResult = this.minecraft.hitResult instanceof BlockHitResult blockHitResult ? blockHitResult : null;

		boolean interactive = OutlineLogic.isInteractive(blockState)
				|| OutlineLogic.isContainer(blockState, this.level, blockPos)
				|| OutlineLogic.hasBlockInteraction(blockState, mainHand, this.level, blockPos, this.minecraft.player, hitResult);

		int color;
		if (interactive) {
			if (!cfg.blockOutlineMode.showsInteractive()) {
				ci.cancel();
				return;
			}
			color = cfg.interactiveColor;
		} else {
			if (!cfg.blockOutlineMode.showsNonInteractive()) {
				ci.cancel();
				return;
			}
			boolean missingTool = OutlineLogic.isMissingToolForDrops(blockState, mainHand, this.level);
			boolean shouldHide = switch (cfg.missingToolVisibility) {
				case ALWAYS_SHOW -> false;
				case ALWAYS_HIDE -> missingTool;
				case HIDE_UNLESS_PLACING -> missingTool && !OutlineLogic.isPlacementItem(mainHand, blockState);
			};
			if (shouldHide) {
				ci.cancel();
				return;
			}
			color = cfg.nonInteractiveColor;
		}

		float[] rgb = dynamicoutlines$toRgb(color);

		VoxelShape combinedShape = shape;
		Optional<BlockPos> pairedPos = OutlineLogic.getPairedPos(blockPos, blockState);
		if (pairedPos.isPresent()) {
			BlockPos otherPos = pairedPos.get();
			BlockState otherState = this.level.getBlockState(otherPos);
			VoxelShape otherShape = otherState.getShape(this.level, otherPos, CollisionContext.of(this.minecraft.player));
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

		double dx = blockPos.getX() - camX;
		double dy = blockPos.getY() - camY;
		double dz = blockPos.getZ() - camZ;

		VertexConsumer customConsumer = this.renderBuffers.bufferSource().getBuffer(DynamicLineRenderType.get(cfg.strokeWidth));

		float alpha = cfg.outlineAlpha / 100F;

		OutlineRenderer.renderSolid(poseStack, customConsumer, combinedShape, dx, dy, dz, rgb[0], rgb[1], rgb[2], alpha);

		ci.cancel();
	}

	private static float[] dynamicoutlines$toRgb(int color) {
		return new float[]{
				((color >> 16) & 0xFF) / 255F,
				((color >> 8) & 0xFF) / 255F,
				(color & 0xFF) / 255F
		};
	}
}