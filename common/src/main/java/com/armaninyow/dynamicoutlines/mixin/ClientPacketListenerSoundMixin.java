package com.armaninyow.dynamicoutlines.mixin;

import com.armaninyow.dynamicoutlines.util.RespawnAnchorTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerSoundMixin {

	@Inject(method = "handleSoundEvent", at = @At("HEAD"))
	private void dynamicoutlines$trackRespawnAnchorSetSpawn(ClientboundSoundPacket packet, CallbackInfo ci) {
		if (packet.getSound().value() == SoundEvents.RESPAWN_ANCHOR_SET_SPAWN) {
			BlockPos pos = BlockPos.containing(packet.getX(), packet.getY(), packet.getZ());
			if (Minecraft.getInstance().level != null) {
				RespawnAnchorTracker.recordSpawnSet(pos, Minecraft.getInstance().level.dimension());
			}
		}
	}
}