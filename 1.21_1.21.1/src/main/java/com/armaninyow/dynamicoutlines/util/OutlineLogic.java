package com.armaninyow.dynamicoutlines.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.BeaconBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CartographyTableBlock;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DecoratedPotBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.LoomBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.core.Direction;

import java.util.Optional;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.phys.Vec3;

public final class OutlineLogic {
	private OutlineLogic() {
	}

	private static boolean isWaterBottle(ItemStack stack) {
		if (!stack.is(Items.POTION)) {
			return false;
		}
		PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
		return contents != null && contents.is(Potions.WATER);
	}

	private static Map<Block, Block> pottableContentCache;

	private static boolean isPottable(ItemStack stack) {
		if (!(stack.getItem() instanceof BlockItem blockItem)) {
			return false;
		}
		Block contentBlock = blockItem.getBlock();
		if (contentBlock == Blocks.AIR) {
			return false;
		}
		if (pottableContentCache == null) {
			Map<Block, Block> map = new HashMap<>();
			for (Block candidate : BuiltInRegistries.BLOCK) {
				if (candidate instanceof FlowerPotBlock flowerPotBlock && flowerPotBlock.getPotted() != Blocks.AIR) {
					map.put(flowerPotBlock.getPotted(), candidate);
				}
			}
			pottableContentCache = map;
		}
		return pottableContentCache.containsKey(contentBlock);
	}

	public static boolean isContainer(BlockState state, Level level, BlockPos pos) {
		return state.getMenuProvider(level, pos) != null;
	}

	public static boolean isInteractive(BlockState state) {
		Object block = state.getBlock();

		if (block instanceof JukeboxBlock) {
			return state.hasProperty(BlockStateProperties.HAS_RECORD) && state.getValue(BlockStateProperties.HAS_RECORD);
		}
		if (block instanceof LecternBlock) {
			return state.hasProperty(BlockStateProperties.HAS_BOOK) && state.getValue(BlockStateProperties.HAS_BOOK);
		}

		return (block instanceof DoorBlock && block != Blocks.IRON_DOOR)
				|| (block instanceof TrapDoorBlock && block != Blocks.IRON_TRAPDOOR)
				|| block instanceof FenceGateBlock
				|| block instanceof LeverBlock
				|| block instanceof CraftingTableBlock
				|| block instanceof AnvilBlock
				|| block instanceof CartographyTableBlock
				|| block instanceof GrindstoneBlock
				|| block instanceof StonecutterBlock
				|| block instanceof LoomBlock
				|| block instanceof NoteBlock
				|| block instanceof CakeBlock
				|| block instanceof CandleCakeBlock
				|| block instanceof DragonEggBlock
				|| block instanceof EnchantingTableBlock
				|| block instanceof BeaconBlock;
	}

	public static boolean hasEntityInteraction(Entity entity, ItemStack mainHand, Entity looker, EntityHitResult hitResult) {
		if (entity instanceof LeashFenceKnotEntity) {
			return true;
		}
		if (entity instanceof net.minecraft.world.entity.animal.Panda panda) {
			if (panda.isScared()) {
				return false;
			}
			if (panda.isOnBack()) {
				return true;
			}
			if (mainHand.isEmpty() || !panda.isFood(mainHand)) {
				return false;
			}
			if (panda.isBaby()) {
				return true;
			}
			boolean cooldownLocked = com.armaninyow.dynamicoutlines.util.AnimalLoveTracker.isLocked(panda.getUUID(), panda.level().getGameTime());
			if (!cooldownLocked) {
				return true;
			}
			return !panda.isSitting() && !panda.isInWater();
		}
		if (entity instanceof Villager villager) {
			VillagerProfession profession = villager.getVillagerData().getProfession();
			return !villager.isBaby() && !villager.isSleeping()
					&& profession != VillagerProfession.NONE && profession != VillagerProfession.NITWIT;
		}
		if (entity instanceof WanderingTrader trader) {
			return !trader.isBaby();
		}
		if (entity instanceof Parrot parrot) {
			if (!parrot.isTame() && !mainHand.isEmpty() && mainHand.is(ItemTags.PARROT_FOOD)) {
				return true;
			}
			if (parrot.isTame() && !parrot.isFlying() && looker instanceof Player player && parrot.isOwnedBy(player)) {
				return true;
			}
		}
		if (entity instanceof net.minecraft.world.entity.animal.goat.Goat goat && !goat.isBaby() && mainHand.is(Items.BUCKET)) {
			return true;
		}
		if (entity instanceof net.minecraft.world.entity.animal.Dolphin && !mainHand.isEmpty() && mainHand.is(ItemTags.FISHES)) {
			return true;
		}
		if (entity instanceof Cat cat && cat.isTame() && looker instanceof Player player && cat.isOwnedBy(player)) {
			return true;
		}
		if (entity instanceof Allay allay) {
			ItemStack allayItem = allay.getItemInHand(InteractionHand.MAIN_HAND);
			if (allay.isDancing() && mainHand.is(Items.AMETHYST_SHARD)) {
				return true;
			}
			if (allayItem.isEmpty() && !mainHand.isEmpty()) {
				return true;
			}
			if (!allayItem.isEmpty() && mainHand.isEmpty()) {
				return true;
			}
		}
		if (entity instanceof Animal animal && !mainHand.isEmpty() && animal.isFood(mainHand)
				&& !com.armaninyow.dynamicoutlines.util.AnimalLoveTracker.isLocked(animal.getUUID(), animal.level().getGameTime())) {
			return true;
		}
		if (entity instanceof Sheep sheep && !sheep.isSheared() && !mainHand.isEmpty() && mainHand.is(Items.SHEARS)) {
			return true;
		}
		if (entity instanceof SnowGolem snowGolem && snowGolem.hasPumpkin() && !mainHand.isEmpty() && mainHand.is(Items.SHEARS)) {
			return true;
		}
		if (entity instanceof net.minecraft.world.entity.monster.Bogged bogged && !mainHand.isEmpty() && mainHand.is(Items.SHEARS) && bogged.readyForShearing()) {
			return true;
		}
		if (entity instanceof net.minecraft.world.entity.monster.piglin.Piglin piglin && !piglin.isBaby()
				&& piglin.getArmPose() != net.minecraft.world.entity.monster.piglin.PiglinArmPose.ADMIRING_ITEM
				&& !mainHand.isEmpty() && mainHand.is(Items.GOLD_INGOT)) {
			return true;
		}
		if (entity instanceof MushroomCow && !mainHand.isEmpty() && (mainHand.is(Items.BOWL) || mainHand.is(Items.BUCKET) || mainHand.is(Items.SHEARS))) {
			return true;
		}
		if (entity instanceof Cow && !mainHand.isEmpty() && mainHand.is(Items.BUCKET)) {
			return true;
		}
		if (entity instanceof Bucketable && mainHand.is(Items.WATER_BUCKET)) {
			return true;
		}
		if (entity instanceof Creeper creeper && !creeper.isIgnited() && !mainHand.isEmpty() && mainHand.is(Items.FLINT_AND_STEEL)) {
			return true;
		}
		if (entity instanceof ZombieVillager zombieVillager && !zombieVillager.isConverting() && !mainHand.isEmpty() && mainHand.is(Items.GOLDEN_APPLE)) {
			return true;
		}
		if (entity instanceof ArmorStand armorStand) {
			if (!mainHand.isEmpty() && (mainHand.getItem() instanceof ArmorItem || mainHand.is(Items.ELYTRA))) {
				return true;
			}
			if (mainHand.isEmpty() && hitResult != null && hitResult.getEntity() == armorStand) {
				Vec3 localHit = hitResult.getLocation().subtract(armorStand.position());
				EquipmentSlot slot = getArmorStandClickedSlot(armorStand, localHit);
				return !armorStand.getItemBySlot(slot).isEmpty();
			}
			return false;
		}
		if (entity instanceof AbstractHorse horse) {
			if (horse.isTamed()) {
				return true;
			}
			return mainHand.isEmpty();
		}
		if (entity instanceof Saddleable saddleable && saddleable.isSaddled()) {
			return true;
		}
		if (entity instanceof Saddleable saddleable && !saddleable.isSaddled() && !mainHand.isEmpty() && mainHand.is(Items.SADDLE)) {
			return true;
		}
		if (entity instanceof Wolf wolf && wolf.isTame() && looker instanceof Player player && wolf.isOwnedBy(player)) {
			return true;
		}
		if (entity instanceof Wolf wolf && !wolf.isTame() && !mainHand.isEmpty() && mainHand.is(Items.BONE)) {
			return true;
		}
		if (entity instanceof Armadillo && !mainHand.isEmpty() && mainHand.is(Items.BRUSH)) {
			return true;
		}
		if (entity instanceof Mob && !mainHand.isEmpty() && mainHand.is(Items.NAME_TAG)) {
			return true;
		}
		if (entity instanceof Leashable leashable) {
			if (leashable.isLeashed()) {
				if (leashable.getLeashHolder() == looker) {
					return true;
				}
			} else if (!mainHand.isEmpty() && mainHand.is(Items.LEAD) && leashable.canHaveALeashAttachedToIt()) {
				return true;
			}
		}
		if (entity instanceof ItemFrame frame && (!frame.getItem().isEmpty() || !mainHand.isEmpty())) {
			return true;
		}
		if (entity instanceof IronGolem golem && mainHand.is(Items.IRON_INGOT) && golem.getHealth() < golem.getMaxHealth()) {
			return true;
		}
		if (entity instanceof ChestBoat) {
			return true;
		}
		if (entity instanceof MinecartFurnace furnace
				&& (mainHand.is(net.minecraft.world.item.Items.COAL) || mainHand.is(net.minecraft.world.item.Items.CHARCOAL))) {
			return com.armaninyow.dynamicoutlines.util.FurnaceMinecartFuelTracker.canAcceptMoreFuel(furnace);
		}
		if (entity instanceof AbstractMinecartContainer) {
			return true;
		}
		if (entity instanceof Boat boat && !(entity instanceof ChestBoat) && looker instanceof Player player) {
			return boat.getPassengers().size() < 2 && !player.isShiftKeyDown();
		}
		if (entity instanceof AbstractMinecart minecart && !(minecart instanceof AbstractMinecartContainer) && !(minecart instanceof MinecartFurnace)
				&& looker instanceof Player player) {
			return !minecart.isVehicle() && !player.isShiftKeyDown();
		}
		return false;
	}

	public static boolean hasBlockInteraction(BlockState state, ItemStack mainHand, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		Object block = state.getBlock();

		if (block instanceof ChiseledBookShelfBlock) {
			boolean slotOccupied = false;
			if (hitResult != null) {
				java.util.OptionalInt slot = ((com.armaninyow.dynamicoutlines.mixin.ChiseledBookShelfBlockAccessor) block)
						.dynamicoutlines$getHitSlot(hitResult, state);
				if (slot.isPresent()) {
					slotOccupied = state.getValue(ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.get(slot.getAsInt()));
				}
			}
			return slotOccupied || mainHand.is(ItemTags.BOOKSHELF_BOOKS);
		}

		if (block instanceof BellBlock) {
			return hitResult != null && isBellProperHit(state, hitResult);
		}

		if (block instanceof net.minecraft.world.level.block.EnderChestBlock) {
			BlockPos abovePos = pos.above();
			return !level.getBlockState(abovePos).isRedstoneConductor(level, abovePos);
		}

		if (block instanceof ButtonBlock && !state.getValue(ButtonBlock.POWERED)) {
			return true;
		}

		if (block instanceof SignBlock) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof SignBlockEntity signBlockEntity && !signBlockEntity.isWaxed()) {
				return true;
			}
		}

		if (block instanceof SweetBerryBushBlock && state.getValue(SweetBerryBushBlock.AGE) > 1) {
			return true;
		}

		if (block instanceof CaveVines && state.hasProperty(CaveVines.BERRIES) && state.getValue(CaveVines.BERRIES)) {
			return true;
		}

		if (state.is(Blocks.POWDER_SNOW) && mainHand.is(Items.BUCKET)) {
			return true;
		}

		if ((state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.ROOTED_DIRT)) && isWaterBottle(mainHand)) {
			return true;
		}

		if (mainHand.getItem() instanceof HoeItem
				&& (state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT_PATH) || state.is(Blocks.ROOTED_DIRT))
				&& level.getBlockState(pos.above()).isAir()) {
			return true;
		}

		if (mainHand.is(Items.BONE_MEAL) && block instanceof BonemealableBlock bonemealable
				&& bonemealable.isValidBonemealTarget(level, pos, state)) {
			return true;
		}

		if (state.is(Blocks.PUMPKIN) && mainHand.is(Items.SHEARS)) {
			return true;
		}

		if ((state.is(Blocks.SUSPICIOUS_SAND) || state.is(Blocks.SUSPICIOUS_GRAVEL)) && mainHand.is(Items.BRUSH)) {
			return true;
		}

		if (block instanceof BeehiveBlock && (mainHand.is(Items.GLASS_BOTTLE) || mainHand.is(Items.SHEARS))
				&& state.getValue(BeehiveBlock.HONEY_LEVEL) >= 5) {
			return true;
		}
		if (block instanceof ComposterBlock) {
			int fillLevel = state.getValue(ComposterBlock.LEVEL);
			if (fillLevel == 8) {
				return true;
			}
			if (fillLevel < 7 && !mainHand.isEmpty() && ComposterBlock.COMPOSTABLES.containsKey(mainHand.getItem())) {
				return true;
			}
		}
		if (block instanceof CampfireBlock && !mainHand.isEmpty()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof CampfireBlockEntity campfireBlockEntity
					&& campfireBlockEntity.getCookableRecipe(mainHand).isPresent()) {
				return true;
			}
		}
		if (block instanceof CampfireBlock && (mainHand.is(Items.FLINT_AND_STEEL) || mainHand.is(Items.FIRE_CHARGE))
				&& CampfireBlock.canLight(state)) {
			return true;
		}
		if (block instanceof CampfireBlock && CampfireBlock.isLitCampfire(state)
				&& mainHand.getItem() instanceof ShovelItem) {
			return true;
		}
		if (block instanceof TntBlock && (mainHand.is(Items.FLINT_AND_STEEL) || mainHand.is(Items.FIRE_CHARGE))) {
			return true;
		}
		if (block instanceof JukeboxBlock && mainHand.has(DataComponents.JUKEBOX_PLAYABLE)) {
			return true;
		}
		if (block instanceof AbstractCauldronBlock) {
			boolean isEmptyCauldron = state.is(Blocks.CAULDRON);
			boolean isWater = state.is(Blocks.WATER_CAULDRON);
			boolean isLava = state.is(Blocks.LAVA_CAULDRON);
			boolean isPowderSnow = state.is(Blocks.POWDER_SNOW_CAULDRON);
			int cauldronLevel = state.hasProperty(BlockStateProperties.LEVEL_CAULDRON) ? state.getValue(BlockStateProperties.LEVEL_CAULDRON) : 0;
			boolean isFull = isLava || ((isWater || isPowderSnow) && cauldronLevel == 3);

			if (mainHand.is(Items.BUCKET) && isFull) {
				return true;
			}
			if (mainHand.is(Items.WATER_BUCKET) && (isEmptyCauldron || (isWater && cauldronLevel < 3))) {
				return true;
			}
			if (isWaterBottle(mainHand) && (isEmptyCauldron || (isWater && cauldronLevel < 3))) {
				return true;
			}
			if ((mainHand.is(Items.LAVA_BUCKET) || mainHand.is(Items.POWDER_SNOW_BUCKET)) && isEmptyCauldron) {
				return true;
			}
			if (mainHand.is(Items.GLASS_BOTTLE) && isWater) {
				return true;
			}
			if (isWater) {
				if (mainHand.is(ItemTags.DYEABLE) && mainHand.has(DataComponents.DYED_COLOR)) {
					return true;
				}
				BannerPatternLayers patterns = mainHand.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
				if (!patterns.layers().isEmpty()) {
					return true;
				}
				if (net.minecraft.world.level.block.Block.byItem(mainHand.getItem()) instanceof ShulkerBoxBlock) {
					return true;
				}
			}
		}
		if (block instanceof FenceBlock && player != null) {
			java.util.List<Entity> nearby = level.getEntitiesOfClass(Entity.class,
					new net.minecraft.world.phys.AABB(pos).inflate(7.0));
			for (Entity nearbyEntity : nearby) {
				if (nearbyEntity instanceof Leashable leashable && leashable.isLeashed() && leashable.getLeashHolder() == player) {
					return true;
				}
			}
		}

		if (state.is(Blocks.LODESTONE) && mainHand.is(Items.COMPASS)) {
			return true;
		}
		if (block instanceof FlowerPotBlock) {
			boolean isFilled = !state.is(Blocks.FLOWER_POT);
			if (!isFilled && isPottable(mainHand)) {
				return true;
			}
			if (isFilled && (mainHand.isEmpty() || !isPottable(mainHand))) {
				return true;
			}
		}
		if (block instanceof DecoratedPotBlock && !mainHand.isEmpty()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof DecoratedPotBlockEntity decoratedPotBlockEntity) {
				ItemStack existing = decoratedPotBlockEntity.getTheItem();
				if (existing.isEmpty()
						|| (ItemStack.isSameItemSameComponents(existing, mainHand) && existing.getCount() < existing.getMaxStackSize())) {
					return true;
				}
			}
		}
		if (block instanceof LecternBlock && (mainHand.is(Items.WRITABLE_BOOK) || mainHand.is(Items.WRITTEN_BOOK))) {
			return true;
		}
		if (block instanceof CandleBlock && (mainHand.is(Items.FLINT_AND_STEEL) || mainHand.is(Items.FIRE_CHARGE))
				&& !AbstractCandleBlock.isLit(state)) {
			return true;
		}
		if (block instanceof CandleBlock && mainHand.isEmpty() && AbstractCandleBlock.isLit(state)) {
			return true;
		}
		if (block instanceof EndPortalFrameBlock && mainHand.is(Items.ENDER_EYE) && !state.getValue(EndPortalFrameBlock.HAS_EYE)) {
			return true;
		}
		if (block instanceof VaultBlock && (mainHand.is(Items.TRIAL_KEY) || mainHand.is(Items.OMINOUS_TRIAL_KEY))
				&& state.getValue(VaultBlock.STATE) == VaultState.ACTIVE) {
			return true;
		}
		if (block instanceof RespawnAnchorBlock) {
			int charges = state.hasProperty(BlockStateProperties.RESPAWN_ANCHOR_CHARGES) ? state.getValue(BlockStateProperties.RESPAWN_ANCHOR_CHARGES) : 0;
			if (charges > 0) {
				if (!RespawnAnchorBlock.canSetSpawn(level) || !RespawnAnchorTracker.isCurrentSpawn(pos, level.dimension())) {
					return true;
				}
			}
			if (mainHand.is(Items.GLOWSTONE) && charges < 4) {
				return true;
			}
		}

		if (block instanceof RedStoneWireBlock && isRedstoneDotOrCross(state)) {
			return true;
		}
		if (block instanceof RepeaterBlock || block instanceof ComparatorBlock || block instanceof DaylightDetectorBlock) {
			return true;
		}

		if (mainHand.getItem() instanceof net.minecraft.world.item.AxeItem) {
			boolean strippable = com.armaninyow.dynamicoutlines.mixin.AxeItemAccessor.dynamicoutlines$getStrippables().containsKey(block);
			boolean scrapesOxidation = block instanceof WeatheringCopper && WeatheringCopper.getPrevious(state).isPresent();
			boolean waxOff = HoneycombItem.WAX_OFF_BY_BLOCK.get().containsKey(block);
			if (strippable || scrapesOxidation || waxOff) {
				return true;
			}
		}
		if (mainHand.is(Items.HONEYCOMB) && HoneycombItem.WAXABLES.get().containsKey(block)) {
			return true;
		}

		if (block instanceof BedBlock && player != null) {
			if (!BedBlock.canSetSpawn(level)) {
				return true;
			}
			if (state.hasProperty(BedBlock.OCCUPIED) && state.getValue(BedBlock.OCCUPIED)) {
				return true;
			}
			boolean isDay = (level.getDayTime() % 24000) < 12000 && !level.isThundering();
			if (isDay) {
				return false;
			}
			if (!bedInRangeAndUnobstructed(state, level, pos, player)) {
				return false;
			}
			Vec3 center = Vec3.atBottomCenterOf(pos);
			java.util.List<net.minecraft.world.entity.monster.Monster> nearby = level.getEntitiesOfClass(
					net.minecraft.world.entity.monster.Monster.class,
					new net.minecraft.world.phys.AABB(center.x() - 8.0, center.y() - 5.0, center.z() - 8.0, center.x() + 8.0, center.y() + 5.0, center.z() + 8.0)
			);
			for (net.minecraft.world.entity.monster.Monster monster : nearby) {
				if (monster.isAlive()) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	private static boolean bedInRangeAndUnobstructed(BlockState state, Level level, BlockPos pos, Player player) {
		if (!state.hasProperty(BlockStateProperties.HORIZONTAL_FACING) || !state.hasProperty(BedBlock.PART)) {
			return true;
		}
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
		boolean inRange = isReachableBed(pos, player) || isReachableBed(pos.relative(facing.getOpposite()), player);
		if (!inRange) {
			return false;
		}
		BlockPos footPos = state.getValue(BedBlock.PART) == net.minecraft.world.level.block.state.properties.BedPart.FOOT ? pos : pos.relative(facing.getOpposite());
		BlockPos headPos = footPos.relative(facing);
		BlockPos footAbove = footPos.above();
		BlockPos headAbove = headPos.above();
		return !level.getBlockState(footAbove).isSuffocating(level, footAbove)
				&& !level.getBlockState(headAbove).isSuffocating(level, headAbove);
	}

	private static boolean isReachableBed(BlockPos pos, Player player) {
		Vec3 v = Vec3.atBottomCenterOf(pos);
		return Math.abs(player.getX() - v.x()) <= 3.0 && Math.abs(player.getY() - v.y()) <= 2.0 && Math.abs(player.getZ() - v.z()) <= 3.0;
	}

	private static EquipmentSlot getArmorStandClickedSlot(ArmorStand armorStand, Vec3 localHit) {
		EquipmentSlot slot = EquipmentSlot.MAINHAND;
		boolean small = armorStand.isSmall();
		double d = localHit.y / (armorStand.getScale() * armorStand.getAgeScale());
		if (d >= 0.1 && d < 0.1 + (small ? 0.8 : 0.45) && armorStand.hasItemInSlot(EquipmentSlot.FEET)) {
			slot = EquipmentSlot.FEET;
		} else if (d >= 0.9 + (small ? 0.3 : 0.0) && d < 0.9 + (small ? 1.0 : 0.7) && armorStand.hasItemInSlot(EquipmentSlot.CHEST)) {
			slot = EquipmentSlot.CHEST;
		} else if (d >= 0.4 && d < 0.4 + (small ? 1.0 : 0.8) && armorStand.hasItemInSlot(EquipmentSlot.LEGS)) {
			slot = EquipmentSlot.LEGS;
		} else if (d >= 1.6 && armorStand.hasItemInSlot(EquipmentSlot.HEAD)) {
			slot = EquipmentSlot.HEAD;
		} else if (!armorStand.hasItemInSlot(EquipmentSlot.MAINHAND) && armorStand.hasItemInSlot(EquipmentSlot.OFFHAND)) {
			slot = EquipmentSlot.OFFHAND;
		}
		return slot;
	}

	private static boolean isBellProperHit(BlockState state, BlockHitResult hit) {
		Direction direction = hit.getDirection();
		double relativeY = hit.getLocation().y - hit.getBlockPos().getY();
		if (direction.getAxis() == Direction.Axis.Y || relativeY > 0.8124) {
			return false;
		}
		Direction facing = state.getValue(BellBlock.FACING);
		BellAttachType attach = state.getValue(BellBlock.ATTACHMENT);
		return switch (attach) {
			case FLOOR -> facing.getAxis() == direction.getAxis();
			case SINGLE_WALL, DOUBLE_WALL -> facing.getAxis() != direction.getAxis();
			case CEILING -> true;
		};
	}

	public static boolean isPlacementItem(ItemStack mainHand, BlockState state) {
		if (mainHand.getItem() instanceof net.minecraft.world.item.BlockItem) {
			return true;
		}
		if (mainHand.is(Items.WATER_BUCKET) || mainHand.is(Items.LAVA_BUCKET) || mainHand.is(Items.POWDER_SNOW_BUCKET)
				|| mainHand.getItem() instanceof net.minecraft.world.item.MobBucketItem) {
			return true;
		}
		if (mainHand.getItem() instanceof net.minecraft.world.item.HangingEntityItem) {
			return true;
		}
		if (mainHand.getItem() instanceof BoatItem) {
			return true;
		}
		if (mainHand.is(Items.ARMOR_STAND)) {
			return true;
		}
		if (mainHand.is(Items.END_CRYSTAL)) {
			return state.is(Blocks.OBSIDIAN) || state.is(Blocks.BEDROCK);
		}
		return false;
	}

	private static boolean isRedstoneDotOrCross(BlockState state) {
		boolean north = state.getValue(RedStoneWireBlock.NORTH).isConnected();
		boolean south = state.getValue(RedStoneWireBlock.SOUTH).isConnected();
		boolean east = state.getValue(RedStoneWireBlock.EAST).isConnected();
		boolean west = state.getValue(RedStoneWireBlock.WEST).isConnected();
		boolean allConnected = north && south && east && west;
		boolean noneConnected = !north && !south && !east && !west;
		return allConnected || noneConnected;
	}

	public static boolean isMissingToolForDrops(BlockState state, ItemStack mainHand, Level level) {
		if (state.is(Blocks.SPAWNER)) {
			return true;
		}
		if (state.requiresCorrectToolForDrops() && !mainHand.isCorrectToolForDrops(state)) {
			return true;
		}
		if (isShearsOrSilkTouchGated(state)) {
			return !(mainHand.is(Items.SHEARS) || hasSilkTouch(mainHand, level));
		}
		if (isSilkTouchOnlyGated(state)) {
			return !hasSilkTouch(mainHand, level);
		}
		if (state.is(Blocks.COBWEB)) {
			return !(mainHand.is(Items.SHEARS) || mainHand.getItem() instanceof SwordItem || hasSilkTouch(mainHand, level));
		}
		return false;
	}

	private static boolean isSilkTouchOnlyGated(BlockState state) {
		return state.is(Blocks.GLASS)
				|| state.is(Blocks.TINTED_GLASS)
				|| state.is(Blocks.GLASS_PANE)
				|| state.is(Blocks.ICE)
				|| state.is(Blocks.INFESTED_STONE)
				|| state.is(Blocks.INFESTED_COBBLESTONE)
				|| state.is(Blocks.INFESTED_STONE_BRICKS)
				|| state.is(Blocks.INFESTED_MOSSY_STONE_BRICKS)
				|| state.is(Blocks.INFESTED_CRACKED_STONE_BRICKS)
				|| state.is(Blocks.INFESTED_CHISELED_STONE_BRICKS)
				|| state.is(Blocks.INFESTED_DEEPSLATE)
				|| state.is(Blocks.TURTLE_EGG)
				|| state.is(Blocks.SCULK_SENSOR)
				|| state.is(Blocks.SCULK_SHRIEKER)
				|| state.is(Blocks.SCULK_CATALYST)
				|| state.is(Blocks.TUBE_CORAL_BLOCK)
				|| state.is(Blocks.BRAIN_CORAL_BLOCK)
				|| state.is(Blocks.BUBBLE_CORAL_BLOCK)
				|| state.is(Blocks.FIRE_CORAL_BLOCK)
				|| state.is(Blocks.HORN_CORAL_BLOCK)
				|| state.is(Blocks.TUBE_CORAL)
				|| state.is(Blocks.BRAIN_CORAL)
				|| state.is(Blocks.BUBBLE_CORAL)
				|| state.is(Blocks.FIRE_CORAL)
				|| state.is(Blocks.HORN_CORAL)
				|| state.is(Blocks.TUBE_CORAL_FAN)
				|| state.is(Blocks.BRAIN_CORAL_FAN)
				|| state.is(Blocks.BUBBLE_CORAL_FAN)
				|| state.is(Blocks.FIRE_CORAL_FAN)
				|| state.is(Blocks.HORN_CORAL_FAN)
				|| state.is(Blocks.TUBE_CORAL_WALL_FAN)
				|| state.is(Blocks.BRAIN_CORAL_WALL_FAN)
				|| state.is(Blocks.BUBBLE_CORAL_WALL_FAN)
				|| state.is(Blocks.FIRE_CORAL_WALL_FAN)
				|| state.is(Blocks.HORN_CORAL_WALL_FAN)
				|| state.is(Blocks.WHITE_STAINED_GLASS)
				|| state.is(Blocks.ORANGE_STAINED_GLASS)
				|| state.is(Blocks.MAGENTA_STAINED_GLASS)
				|| state.is(Blocks.LIGHT_BLUE_STAINED_GLASS)
				|| state.is(Blocks.YELLOW_STAINED_GLASS)
				|| state.is(Blocks.LIME_STAINED_GLASS)
				|| state.is(Blocks.PINK_STAINED_GLASS)
				|| state.is(Blocks.GRAY_STAINED_GLASS)
				|| state.is(Blocks.LIGHT_GRAY_STAINED_GLASS)
				|| state.is(Blocks.CYAN_STAINED_GLASS)
				|| state.is(Blocks.PURPLE_STAINED_GLASS)
				|| state.is(Blocks.BLUE_STAINED_GLASS)
				|| state.is(Blocks.BROWN_STAINED_GLASS)
				|| state.is(Blocks.GREEN_STAINED_GLASS)
				|| state.is(Blocks.RED_STAINED_GLASS)
				|| state.is(Blocks.BLACK_STAINED_GLASS)
				|| state.is(Blocks.WHITE_STAINED_GLASS_PANE)
				|| state.is(Blocks.ORANGE_STAINED_GLASS_PANE)
				|| state.is(Blocks.MAGENTA_STAINED_GLASS_PANE)
				|| state.is(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE)
				|| state.is(Blocks.YELLOW_STAINED_GLASS_PANE)
				|| state.is(Blocks.LIME_STAINED_GLASS_PANE)
				|| state.is(Blocks.PINK_STAINED_GLASS_PANE)
				|| state.is(Blocks.GRAY_STAINED_GLASS_PANE)
				|| state.is(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE)
				|| state.is(Blocks.CYAN_STAINED_GLASS_PANE)
				|| state.is(Blocks.PURPLE_STAINED_GLASS_PANE)
				|| state.is(Blocks.BLUE_STAINED_GLASS_PANE)
				|| state.is(Blocks.BROWN_STAINED_GLASS_PANE)
				|| state.is(Blocks.GREEN_STAINED_GLASS_PANE)
				|| state.is(Blocks.RED_STAINED_GLASS_PANE)
				|| state.is(Blocks.BLACK_STAINED_GLASS_PANE);
	}

	private static boolean isShearsOrSilkTouchGated(BlockState state) {
		return state.is(net.minecraft.tags.BlockTags.LEAVES)
				|| state.is(Blocks.SHORT_GRASS)
				|| state.is(Blocks.FERN)
				|| state.is(Blocks.TALL_GRASS)
				|| state.is(Blocks.LARGE_FERN)
				|| state.is(Blocks.VINE)
				|| state.is(Blocks.SCULK_VEIN);
	}

	private static Holder<Enchantment> silkTouchHolderCache;

	private static boolean hasSilkTouch(ItemStack stack, Level level) {
		if (silkTouchHolderCache == null) {
			silkTouchHolderCache = level.registryAccess()
					.registryOrThrow(Registries.ENCHANTMENT)
					.getHolderOrThrow(Enchantments.SILK_TOUCH);
		}
		return EnchantmentHelper.getItemEnchantmentLevel(silkTouchHolderCache, stack) > 0;
	}

	public static void clearCaches() {
		pottableContentCache = null;
		silkTouchHolderCache = null;
	}

	public static Optional<BlockPos> getPairedPos(BlockPos pos, BlockState state) {
		if (state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)) {
			DoubleBlockHalf half = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
			return Optional.of(half == DoubleBlockHalf.UPPER ? pos.below() : pos.above());
		}

		if (state.hasProperty(BlockStateProperties.BED_PART) && state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
			BedPart part = state.getValue(BlockStateProperties.BED_PART);
			Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
			return Optional.of(part == BedPart.FOOT ? pos.relative(facing) : pos.relative(facing.getOpposite()));
		}

		if (state.hasProperty(BlockStateProperties.CHEST_TYPE) && state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
			ChestType type = state.getValue(BlockStateProperties.CHEST_TYPE);
			if (type == ChestType.SINGLE) {
				return Optional.empty();
			}
			Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
			Direction toOther = type == ChestType.LEFT ? facing.getClockWise() : facing.getCounterClockWise();
			return Optional.of(pos.relative(toOther));
		}

		return Optional.empty();
	}

	public static boolean isHostile(Entity entity) {
		return entity instanceof Enemy;
	}
}