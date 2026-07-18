package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.render.FluidOutlineRenderer;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererFluidOutlineMixin {

	@Inject(method = "renderLevel", at = @At("HEAD"))
	private void dynamicoutlines$invalidateFluidCache(GraphicsResourceAllocator graphicsResourceAllocator,
			DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, Matrix4f frustumMatrix,
			Matrix4f projectionMatrix, Matrix4f cullingFrustumMatrix, GpuBufferSlice fogBuffer,
			Vector4f fogColor, boolean isFoggy, CallbackInfo ci) {
		FluidOutlineRenderer.invalidateFluidCache();
	}

	@Inject(method = "renderBlockOutline", at = @At("HEAD"))
	private void dynamicoutlines$renderFluidOutline(MultiBufferSource.BufferSource bufferSource,
			PoseStack poseStack, boolean isTranslucent, LevelRenderState levelRenderState, CallbackInfo ci) {
		if (isTranslucent) {
			return;
		}
		Vec3 camPos = levelRenderState.cameraRenderState.pos;
		FluidOutlineRenderer.render(poseStack, bufferSource, camPos);
	}
}