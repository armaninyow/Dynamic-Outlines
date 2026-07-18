package com.armaninyow.dynamicoutlines.mixin;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.SelectableSlotContainer;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.OptionalInt;

@Mixin(SelectableSlotContainer.class)
public interface ChiseledBookShelfBlockAccessor {
	@Invoker("getHitSlot")
	OptionalInt dynamicoutlines$getHitSlot(BlockHitResult blockHitResult, Direction direction);
}