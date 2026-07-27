package com.armaninyow.dynamicoutlines.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;

public enum EntityOutlineCategory {
	ENTITY_INTERACTION,
	PASSIVE_NEUTRAL_HOSTILE;

	public static EntityOutlineCategory of(Entity entity, ItemStack mainHand, Minecraft minecraft) {
		EntityHitResult hitResult = minecraft.hitResult instanceof EntityHitResult entityHitResult ? entityHitResult : null;
		if (OutlineLogic.hasEntityInteraction(entity, mainHand, minecraft.player, hitResult)) {
			return ENTITY_INTERACTION;
		}
		ItemStack offHand = minecraft.player != null ? minecraft.player.getOffhandItem() : ItemStack.EMPTY;
		if (!offHand.isEmpty() && OutlineLogic.hasEntityInteraction(entity, offHand, minecraft.player, hitResult)) {
			return ENTITY_INTERACTION;
		}
		return PASSIVE_NEUTRAL_HOSTILE;
	}
}