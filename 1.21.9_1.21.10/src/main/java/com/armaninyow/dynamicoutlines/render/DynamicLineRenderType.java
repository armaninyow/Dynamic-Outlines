package com.armaninyow.dynamicoutlines.render;

import net.minecraft.client.renderer.RenderPipelines;
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
					1536,
					RenderPipelines.LINES,
					RenderType.CompositeState.builder()
							.setLineState(new RenderStateShard.LineStateShard(OptionalDouble.of(width)))
							.createCompositeState(false)
			);
			cachedWidth = width;
		}
		return cached;
	}
}