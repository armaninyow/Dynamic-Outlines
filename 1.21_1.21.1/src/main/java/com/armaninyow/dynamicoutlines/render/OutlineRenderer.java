package com.armaninyow.dynamicoutlines.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class OutlineRenderer {
	private OutlineRenderer() {
	}

	public static void renderSolid(PoseStack poseStack, VertexConsumer vertexConsumer, VoxelShape shape,
			double dx, double dy, double dz, float r, float g, float b, float alpha) {
		PoseStack.Pose pose = poseStack.last();
		shape.forAllEdges((x1, y1, z1, x2, y2, z2) -> {
			float nx = (float) (x2 - x1);
			float ny = (float) (y2 - y1);
			float nz = (float) (z2 - z1);
			float len = Mth.sqrt(nx * nx + ny * ny + nz * nz);
			if (len > 1.0E-6F) {
				nx /= len;
				ny /= len;
				nz /= len;
			}
			vertexConsumer.addVertex(pose, (float) (x1 + dx), (float) (y1 + dy), (float) (z1 + dz)).setColor(r, g, b, alpha).setNormal(pose, nx, ny, nz);
			vertexConsumer.addVertex(pose, (float) (x2 + dx), (float) (y2 + dy), (float) (z2 + dz)).setColor(r, g, b, alpha).setNormal(pose, nx, ny, nz);
		});
	}
}