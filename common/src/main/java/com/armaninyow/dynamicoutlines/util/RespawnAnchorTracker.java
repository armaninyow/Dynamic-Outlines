package com.armaninyow.dynamicoutlines.util;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

public final class RespawnAnchorTracker {
	private RespawnAnchorTracker() {
	}

	private static BlockPos lastSetPos;
	private static ResourceKey<Level> lastSetDimension;

	public static void load() {
		ModConfig cfg = ModConfig.get();
		if (cfg.respawnAnchorDimension == null) {
			return;
		}
		int colonIdx = cfg.respawnAnchorDimension.indexOf(':');
		if (colonIdx <= 0) {
			return;
		}
		String namespace = cfg.respawnAnchorDimension.substring(0, colonIdx);
		String path = cfg.respawnAnchorDimension.substring(colonIdx + 1);
		lastSetPos = new BlockPos(cfg.respawnAnchorX, cfg.respawnAnchorY, cfg.respawnAnchorZ);
		lastSetDimension = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(namespace, path));
	}

	public static void recordSpawnSet(BlockPos pos, ResourceKey<Level> dimension) {
		lastSetPos = pos;
		lastSetDimension = dimension;

		ModConfig cfg = ModConfig.get();
		cfg.respawnAnchorX = pos.getX();
		cfg.respawnAnchorY = pos.getY();
		cfg.respawnAnchorZ = pos.getZ();
		cfg.respawnAnchorDimension = dimension.identifier().toString();
		ModConfig.save();
	}

	public static void clear() {
		lastSetPos = null;
		lastSetDimension = null;
	}

	public static boolean isCurrentSpawn(BlockPos pos, ResourceKey<Level> dimension) {
		return lastSetPos != null && lastSetPos.equals(pos) && dimension.equals(lastSetDimension);
	}
}