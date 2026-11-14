package com.armaninyow.dynamicoutlines.mixin;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(AxeItem.class)
public interface AxeItemAccessor {
	@org.spongepowered.asm.mixin.gen.Accessor("STRIPPABLES")
	static Map<Block, Block> dynamicoutlines$getStrippables() {
		throw new AssertionError();
	}
}