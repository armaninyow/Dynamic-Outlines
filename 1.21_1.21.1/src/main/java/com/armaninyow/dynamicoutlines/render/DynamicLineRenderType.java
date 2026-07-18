package com.armaninyow.dynamicoutlines.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;

import java.util.OptionalDouble;

public final class DynamicLineRenderType {
	private DynamicLineRenderType() {
	}

	private static RenderType cached;
	private static float cachedWidth = -1F;

	public static RenderType get(float width) {
		if (cached == null || cachedWidth != width) {
			cached = RenderType.create(
					"dynamicoutlines_lines",
					DefaultVertexFormat.POSITION_COLOR_NORMAL,
					VertexFormat.Mode.LINES,
					1536,
					RenderType.CompositeState.builder()
							.setShaderState(RenderStateShard.RENDERTYPE_LINES_SHADER)
							.setLineState(new RenderStateShard.LineStateShard(OptionalDouble.of(width)))
							.setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
							.setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
							.setCullState(RenderStateShard.NO_CULL)
							.createCompositeState(false)
			);
			cachedWidth = width;
		}
		return cached;
	}
}