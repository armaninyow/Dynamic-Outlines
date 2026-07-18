package com.armaninyow.dynamicoutlines.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.armaninyow.dynamicoutlines.mixin.ShaderManagerCompilationCacheAccessor;
import com.armaninyow.dynamicoutlines.util.ShaderManagerCacheUtil;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.ShaderManager;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
	public enum BlockOutlineMode {
		NONE, INTERACTIVE, NON_INTERACTIVE, ALL;

		public boolean showsInteractive() {
			return this == INTERACTIVE || this == ALL;
		}

		public boolean showsNonInteractive() {
			return this == NON_INTERACTIVE || this == ALL;
		}
	}

	public enum EntityOutlineMode {
		NONE, PASSIVE_NEUTRAL_HOSTILE, ENTITY_INTERACTION, ALL;

		public boolean showsPassiveNeutralHostile() {
			return this == PASSIVE_NEUTRAL_HOSTILE || this == ALL;
		}

		public boolean showsEntityInteraction() {
			return this == ENTITY_INTERACTION || this == ALL;
		}

		public boolean showsAny() {
			return this != NONE;
		}
	}

	public enum MissingToolVisibility {
		ALWAYS_SHOW, ALWAYS_HIDE, HIDE_UNLESS_PLACING
	}

	public MissingToolVisibility missingToolVisibility = MissingToolVisibility.HIDE_UNLESS_PLACING;

	public BlockOutlineMode blockOutlineMode = BlockOutlineMode.ALL;
	public int nonInteractiveColor = 0x000000;
	public int interactiveColor = 0xFFFF55;

	public EntityOutlineMode entityOutlineMode = EntityOutlineMode.ALL;
	public int entityColor = 0x000000;
	public int enemyColor = 0xFF0000;

	public int entityInteractionColor = 0xFFFF55;

	public int strokeWidth = 2;
	public int outlineAlpha = 100;

	// Internal tracking, not user-facing config, but persisted here to avoid a second file.
	public int respawnAnchorX;
	public int respawnAnchorY;
	public int respawnAnchorZ;
	public String respawnAnchorDimension;

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static ModConfig instance;

	private static Path path() {
		return FabricLoader.getInstance().getConfigDir().resolve("dynamicoutlines.json");
	}

	public static ModConfig get() {
		if (instance == null) {
			load();
		}
		return instance;
	}

	public static void load() {
		Path path = path();
		ModConfig loaded = null;
		if (Files.exists(path)) {
			try {
				loaded = GSON.fromJson(Files.readString(path), ModConfig.class);
			} catch (IOException | JsonParseException ignored) {
			}
		}
		instance = loaded != null ? loaded : new ModConfig();
	}

	public static void save() {
		try {
			Files.writeString(path(), GSON.toJson(instance != null ? instance : new ModConfig()));
		} catch (IOException e) {
			throw new RuntimeException("Failed to save Dynamic Outlines config", e);
		}

		dynamicoutlines$invalidateEntityOutlinePostChain();
	}

	private static void dynamicoutlines$invalidateEntityOutlinePostChain() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null) {
			return;
		}
		ShaderManager shaderManager = minecraft.getShaderManager();
		if (shaderManager == null) {
			return;
		}
		Object compilationCache = ShaderManagerCacheUtil.getCompilationCache(shaderManager);
		if (!(compilationCache instanceof ShaderManagerCompilationCacheAccessor cacheAccessor)) {
			return;
		}
		java.util.Map<ResourceLocation, java.util.Optional<PostChain>> postChains = cacheAccessor.dynamicoutlines$getPostChains();
		ResourceLocation entityOutlineId = ResourceLocation.fromNamespaceAndPath("minecraft", "entity_outline");
		java.util.Optional<PostChain> removed = postChains.remove(entityOutlineId);
		if (removed != null) {
			removed.ifPresent(PostChain::close);
		}
	}
}