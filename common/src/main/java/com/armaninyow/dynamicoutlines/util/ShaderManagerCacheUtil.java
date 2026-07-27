package com.armaninyow.dynamicoutlines.util;

import com.armaninyow.dynamicoutlines.mixin.ShaderManagerCompilationCacheAccessor;
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
				field = findCompilationCacheField(shaderManager.getClass());
				field.setAccessible(true);
				compilationCacheField = field;
			}
			return field.get(shaderManager);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException("Failed to access ShaderManager.compilationCache", e);
		}
	}

	private static Field findCompilationCacheField(Class<?> shaderManagerClass) throws NoSuchFieldException {
		for (Field field : shaderManagerClass.getDeclaredFields()) {
			if (ShaderManagerCompilationCacheAccessor.class.isAssignableFrom(field.getType())) {
				return field;
			}
		}
		throw new NoSuchFieldException("Could not locate ShaderManager's CompilationCache field by type");
	}
}