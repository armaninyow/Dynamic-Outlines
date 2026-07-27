package com.armaninyow.dynamicoutlines.render;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public final class DynamicLineRenderType {
	private DynamicLineRenderType() {
	}

	public static RenderType get() {
		return RenderTypes.lines();
	}
}