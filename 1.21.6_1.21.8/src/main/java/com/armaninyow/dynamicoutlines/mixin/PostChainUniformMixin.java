package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostChainConfig;
import net.minecraft.client.renderer.UniformValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(PostChain.class)
public abstract class PostChainUniformMixin {

	@Redirect(method = "createPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/PostChainConfig$Pass;uniforms()Ljava/util/Map;", ordinal = 1))
	private static Map<String, List<UniformValue>> dynamicoutlines$overrideRadiusUniform(PostChainConfig.Pass pass) {
		Map<String, List<UniformValue>> original = pass.uniforms();
		List<UniformValue> blurConfig = original.get("BlurConfig");
		if (blurConfig == null || blurConfig.size() < 2) {
			return original;
		}
		ModConfig cfg = ModConfig.get();
		List<UniformValue> modifiedBlurConfig = new ArrayList<>(blurConfig);
		modifiedBlurConfig.set(1, new UniformValue.FloatUniform(cfg.strokeWidth * 0.25F));

		Map<String, List<UniformValue>> modified = new HashMap<>(original);
		modified.put("BlurConfig", modifiedBlurConfig);
		return modified;
	}
}