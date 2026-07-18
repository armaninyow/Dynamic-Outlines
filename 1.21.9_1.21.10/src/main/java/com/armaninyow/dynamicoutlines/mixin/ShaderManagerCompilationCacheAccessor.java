package com.armaninyow.dynamicoutlines.mixin;

import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Optional;

@Mixin(targets = "net/minecraft/client/renderer/ShaderManager$CompilationCache")
public interface ShaderManagerCompilationCacheAccessor {
	@Accessor("postChains")
	Map<ResourceLocation, Optional<PostChain>> dynamicoutlines$getPostChains();
}