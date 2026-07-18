package com.armaninyow.dynamicoutlines;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import com.armaninyow.dynamicoutlines.render.FluidOutlineRenderer;
import com.armaninyow.dynamicoutlines.util.FurnaceMinecartFuelTracker;
import com.armaninyow.dynamicoutlines.util.RespawnAnchorTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class DynamicOutlinesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModConfig.load();
		RespawnAnchorTracker.load();
		WorldRenderEvents.START.register(context -> FluidOutlineRenderer.invalidateFluidCache());
		WorldRenderEvents.AFTER_ENTITIES.register(FluidOutlineRenderer::render);

		ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
			FurnaceMinecartFuelTracker.clear();
			com.armaninyow.dynamicoutlines.util.OutlineLogic.clearCaches();
		});

		DynamicOutlines.LOGGER.info("Dynamic Outlines client initialized");
	}
}