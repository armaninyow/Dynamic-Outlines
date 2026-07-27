package com.armaninyow.dynamicoutlines.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class OutlineRenderer {
	private OutlineRenderer() {
	}

	public static void renderSolid(PoseStack poseStack, VertexConsumer vertexConsumer, VoxelShape shape,
			double dx, double dy, double dz, float r, float g, float b, float alpha, float lineWidth) {
		PoseStack.Pose pose = poseStack.last();
		float overshoot = 0.0001875F * lineWidth;

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

			double ex1 = x1 - nx * overshoot;
			double ey1 = y1 - ny * overshoot;
			double ez1 = z1 - nz * overshoot;
			double ex2 = x2 + nx * overshoot;
			double ey2 = y2 + ny * overshoot;
			double ez2 = z2 + nz * overshoot;

			vertexConsumer.addVertex(pose, (float) (ex1 + dx), (float) (ey1 + dy), (float) (ez1 + dz))
					.setColor(r, g, b, alpha).setNormal(pose, nx, ny, nz).setLineWidth(lineWidth);
			vertexConsumer.addVertex(pose, (float) (ex2 + dx), (float) (ey2 + dy), (float) (ez2 + dz))
					.setColor(r, g, b, alpha).setNormal(pose, nx, ny, nz).setLineWidth(lineWidth);
		});
	}
}