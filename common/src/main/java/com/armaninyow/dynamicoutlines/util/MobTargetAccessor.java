package com.armaninyow.dynamicoutlines.util;

public interface MobTargetAccessor {
	boolean dynamicoutlines$hasTarget();

	default boolean dynamicoutlines$isNautilusAngry() {
		return false;
	}
}