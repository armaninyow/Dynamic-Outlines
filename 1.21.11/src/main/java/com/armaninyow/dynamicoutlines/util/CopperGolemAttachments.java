package com.armaninyow.dynamicoutlines.util;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public final class CopperGolemAttachments {
	private CopperGolemAttachments() {
	}

	public static final AttachmentType<Boolean> WAXED = AttachmentRegistry.<Boolean>builder()
			.initializer(() -> Boolean.FALSE)
			.syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
			.buildAndRegister(Identifier.fromNamespaceAndPath("dynamicoutlines", "copper_golem_waxed"));

	public static void init() {
	}
}