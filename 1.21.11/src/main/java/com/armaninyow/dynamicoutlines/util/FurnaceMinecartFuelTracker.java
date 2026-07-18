package com.armaninyow.dynamicoutlines.util;

import com.armaninyow.dynamicoutlines.mixin.MinecartFurnaceAccessor;
import net.minecraft.world.entity.vehicle.minecart.MinecartFurnace;

import java.util.HashMap;
import java.util.Map;

public final class FurnaceMinecartFuelTracker {
	private FurnaceMinecartFuelTracker() {
	}

	private static final int MAX_FUEL = 32000;
	private static final int FUEL_PER_ITEM = 3600;

	private static final Map<Integer, Long> LAST_DECAY_GAME_TIME = new HashMap<>();

	public static boolean canAcceptMoreFuel(MinecartFurnace furnace) {
		int id = furnace.getId();
		long gameTime = furnace.level().getGameTime();
		MinecartFurnaceAccessor accessor = (MinecartFurnaceAccessor) furnace;

		Long lastDecay = LAST_DECAY_GAME_TIME.get(id);
		if (lastDecay != null) {
			long elapsed = gameTime - lastDecay;
			if (elapsed > 0) {
				int current = accessor.dynamicoutlines$getFuel();
				accessor.dynamicoutlines$setFuel(Math.max(0, current - (int) elapsed));
			}
		}
		LAST_DECAY_GAME_TIME.put(id, gameTime);

		return accessor.dynamicoutlines$getFuel() + FUEL_PER_ITEM <= MAX_FUEL;
	}

	public static void clear() {
		LAST_DECAY_GAME_TIME.clear();
	}
}