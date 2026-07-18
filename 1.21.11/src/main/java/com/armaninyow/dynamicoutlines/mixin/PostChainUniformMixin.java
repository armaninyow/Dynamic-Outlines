package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostChainConfig;
import net.minecraft.client.renderer.UniformValue;
import org.joml.Vector4f;
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
	private static Map<String, List<UniformValue>> dynamicoutlines$overridePassUniforms(PostChainConfig.Pass pass) {
		Map<String, List<UniformValue>> original = pass.uniforms();
		Map<String, List<UniformValue>> modified = null;
		ModConfig cfg = ModConfig.get();

		List<UniformValue> blurConfig = original.get("BlurConfig");
		if (blurConfig != null && blurConfig.size() >= 2) {
			List<UniformValue> modifiedBlurConfig = new ArrayList<>(blurConfig);
			modifiedBlurConfig.set(1, new UniformValue.FloatUniform(cfg.strokeWidth * 0.25F));
			modified = new HashMap<>(original);
			modified.put("BlurConfig", modifiedBlurConfig);
		}

		List<UniformValue> blitConfig = original.get("BlitConfig");
		if (blitConfig != null && !blitConfig.isEmpty()) {
			List<UniformValue> modifiedBlitConfig = new ArrayList<>(blitConfig);
			float alpha = cfg.outlineAlpha / 100F;
			modifiedBlitConfig.set(0, new UniformValue.Vec4Uniform(new Vector4f(1.0F, 1.0F, 1.0F, alpha)));
			if (modified == null) {
				modified = new HashMap<>(original);
			}
			modified.put("BlitConfig", modifiedBlitConfig);
		}

		return modified != null ? modified : original;
	}
}