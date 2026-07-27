package com.armaninyow.dynamicoutlines.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AnimalLoveTracker {
	private AnimalLoveTracker() {
	}

	private static final long LOCK_DURATION_TICKS = 600;

	private static final Map<UUID, Long> LOCKED_UNTIL = new HashMap<>();

	public static void markJustSucceeded(UUID entityUuid, long gameTime) {
		LOCKED_UNTIL.put(entityUuid, gameTime + LOCK_DURATION_TICKS);
	}

	public static boolean isLocked(UUID entityUuid, long gameTime) {
		Long until = LOCKED_UNTIL.get(entityUuid);
		return until != null && gameTime < until;
	}
}