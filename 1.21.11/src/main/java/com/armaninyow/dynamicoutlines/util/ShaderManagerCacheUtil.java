package com.armaninyow.dynamicoutlines.util;

import net.minecraft.client.renderer.ShaderManager;

import java.lang.reflect.Field;

public final class ShaderManagerCacheUtil {
	private ShaderManagerCacheUtil() {
	}

	private static volatile Field compilationCacheField;

	public static Object getCompilationCache(ShaderManager shaderManager) {
		try {
			Field field = compilationCacheField;
			if (field == null) {
				field = ShaderManager.class.getDeclaredField("compilationCache");
				field.setAccessible(true);
				compilationCacheField = field;
			}
			return field.get(shaderManager);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException("Failed to access ShaderManager.compilationCache", e);
		}
	}
}