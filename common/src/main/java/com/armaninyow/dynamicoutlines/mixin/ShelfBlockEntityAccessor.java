package com.armaninyow.dynamicoutlines.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ShelfBlockEntity.class)
public interface ShelfBlockEntityAccessor {
	@Accessor("items")
	NonNullList<ItemStack> dynamicoutlines$getItems();
}