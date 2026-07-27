package com.armaninyow.dynamicoutlines.util;

import com.armaninyow.dynamicoutlines.config.ModConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.Bucketable;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.nautilus.AbstractNautilus;
import net.minecraft.world.entity.animal.polarbear.PolarBear;
import net.minecraft.world.entity.animal.fish.Pufferfish;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.animal.wolf.Wolf;
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
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.equipment.Equippable;
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
import net.minecraft.world.level.block.CommandBlock;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.item.SpawnEggItem;
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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.core.Direction;

import java.util.Optional;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecartContainer;
import net.minecraft.world.entity.vehicle.minecart.MinecartFurnace;
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

	public static boolean isWaxableCopperContainerOrDoor(Object block) {
		return HoneycombItem.WAXABLES.get().containsKey(block) || HoneycombItem.WAX_OFF_BY_BLOCK.get().containsKey(block);
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
				|| block instanceof DragonEggBlock
				|| block instanceof EnchantingTableBlock
				|| block instanceof BeaconBlock;
	}

	public static boolean hasEntityInteraction(Entity entity, ItemStack mainHand, Entity looker, EntityHitResult hitResult) {
		if (entity instanceof LeashFenceKnotEntity) {
			return true;
		}
		if (entity instanceof Mob mob && !mainHand.isEmpty() && mainHand.is(Items.SHEARS)) {
			for (EquipmentSlot slot : EquipmentSlot.values()) {
				ItemStack equipped = mob.getItemBySlot(slot);
				Equippable equippable = equipped.get(DataComponents.EQUIPPABLE);
				if (equippable != null && equippable.canBeSheared()) {
					return true;
				}
			}
		}
		if (entity instanceof net.minecraft.world.entity.AgeableMob ageableMob && ageableMob.isBaby()) {
			int ageLockCooldown = ((com.armaninyow.dynamicoutlines.mixin.AgeableMobAccessor) ageableMob)
					.dynamicoutlines$getAgeLockParticleTimer();
			if (net.minecraft.world.entity.AgeableMob.canUseGoldenDandelion(mainHand, true, ageLockCooldown, ageableMob)) {
				return true;
			}
		}
		if (entity instanceof net.minecraft.world.entity.animal.panda.Panda panda) {
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
				return !panda.isAgeLocked();
			}
			boolean cooldownLocked = com.armaninyow.dynamicoutlines.util.AnimalLoveTracker.isLocked(panda.getUUID(), panda.level().getGameTime());
			if (!cooldownLocked) {
				return true;
			}
			return !panda.isSitting() && !panda.isInWater();
		}
		if (entity instanceof Villager villager) {
			Holder<VillagerProfession> profession = villager.getVillagerData().profession();
			return !villager.isBaby() && !villager.isSleeping()
					&& !profession.is(VillagerProfession.NONE) && !profession.is(VillagerProfession.NITWIT);
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
		if (entity instanceof net.minecraft.world.entity.animal.dolphin.Dolphin && !mainHand.isEmpty() && mainHand.is(ItemTags.FISHES)) {
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
		if (entity instanceof Animal animal && !(entity instanceof HappyGhast) && !(entity instanceof AbstractHorse)
				&& !mainHand.isEmpty() && animal.isFood(mainHand)
				&& !(animal.isBaby() && animal.isAgeLocked())
				&& !com.armaninyow.dynamicoutlines.util.AnimalLoveTracker.isLocked(animal.getUUID(), animal.level().getGameTime())) {
			return true;
		}
		if (entity instanceof Sheep sheep && !sheep.isSheared() && !mainHand.isEmpty() && mainHand.is(Items.SHEARS)) {
			return true;
		}
		if (entity instanceof SnowGolem snowGolem && snowGolem.hasPumpkin() && !mainHand.isEmpty() && mainHand.is(Items.SHEARS)) {
			return true;
		}
		if (entity instanceof net.minecraft.world.entity.monster.skeleton.Bogged bogged && !mainHand.isEmpty() && mainHand.is(Items.SHEARS) && bogged.readyForShearing()) {
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
		if (entity instanceof net.minecraft.world.entity.monster.cubemob.SulfurCube sulfurCube && !mainHand.isEmpty()) {
			if (mainHand.is(Items.SHEARS) && sulfurCube.readyForShearing() && !sulfurCube.isPrimed()) {
				return true;
			}
			net.minecraft.world.item.component.SulfurCubeContent content =
					sulfurCube.get(net.minecraft.core.component.DataComponents.SULFUR_CUBE_CONTENT);
			if (!sulfurCube.isTiny() && mainHand.is(Items.BUCKET) && !sulfurCube.isPrimed()
					&& sulfurCube.canBePickedUpWithBucket(mainHand)) {
				return true;
			}
			if (mainHand.is(Items.FLINT_AND_STEEL) && !sulfurCube.isPrimed()
					&& content != null && content.absorbedBlockItemStack().item().value() == Items.TNT) {
				return true;
			}
			if (sulfurCube.isTiny() && !sulfurCube.isAgeLocked() && mainHand.is(Items.SLIME_BALL)) {
				return true;
			}
			if (!sulfurCube.isTiny() && !sulfurCube.isPrimed() && !sulfurCube.matchingArchetypes(mainHand).isEmpty()) {
				boolean sameItemAlreadyAbsorbed = content != null
						&& mainHand.is(content.absorbedBlockItemStack().item());
				if (!sameItemAlreadyAbsorbed) {
					return true;
				}
			}
		}
		if (entity instanceof Cow && !mainHand.isEmpty() && mainHand.is(Items.BUCKET)) {
			return true;
		}
		if (!(entity instanceof net.minecraft.world.entity.monster.cubemob.SulfurCube) && entity instanceof Bucketable && mainHand.is(Items.WATER_BUCKET)) {
			return true;
		}
		if (entity instanceof Creeper creeper && !creeper.isIgnited() && !mainHand.isEmpty() && mainHand.is(Items.FLINT_AND_STEEL)) {
			return true;
		}
		if (entity instanceof ZombieVillager zombieVillager && !zombieVillager.isConverting() && !mainHand.isEmpty() && mainHand.is(Items.GOLDEN_APPLE)) {
			return true;
		}
		if (entity instanceof ArmorStand armorStand) {
			Equippable equippable = mainHand.get(DataComponents.EQUIPPABLE);
			boolean isArmorItem = equippable != null && equippable.slot().isArmor();
			if (!mainHand.isEmpty() && (isArmorItem || mainHand.is(Items.ELYTRA))) {
				return true;
			}
			if (mainHand.isEmpty() && hitResult != null && hitResult.getEntity() == armorStand) {
				Vec3 localHit = hitResult.getLocation().subtract(armorStand.position());
				EquipmentSlot slot = getArmorStandClickedSlot(armorStand, localHit);
				return !armorStand.getItemBySlot(slot).isEmpty();
			}
			return false;
		}
		if (entity instanceof net.minecraft.world.entity.animal.equine.ZombieHorse zombieHorse) {
			if (!zombieHorse.isTamed()) {
				if (!mainHand.isEmpty() && zombieHorse.isFood(mainHand)) {
					return canUntamedHorseBeFed(zombieHorse, mainHand);
				}
				return true;
			}
			if (!mainHand.isEmpty() && zombieHorse.isFood(mainHand)) {
				return zombieHorse.getHealth() < zombieHorse.getMaxHealth();
			}
			if (!mainHand.isEmpty() && mainHand.is(Items.SADDLE) && !zombieHorse.isSaddled()) {
				return true;
			}
			return mainHand.isEmpty();
		}
		if (entity instanceof net.minecraft.world.entity.animal.equine.SkeletonHorse skeletonHorse) {
			if (!mainHand.isEmpty() && mainHand.is(Items.SADDLE) && !skeletonHorse.isSaddled()) {
				return true;
			}
			if (skeletonHorse.isTamed()) {
				return mainHand.isEmpty();
			}
			return false;
		}
		if (entity instanceof AbstractHorse horse) {
			if (!mainHand.isEmpty() && horse.isFood(mainHand)) {
				if (!horse.isTamed()) {
					return canUntamedHorseBeFed(horse, mainHand);
				}
				return horse.getHealth() < horse.getMaxHealth();
			}
			if (horse.isTamed()) {
				return !horse.isBaby();
			}
			return mainHand.isEmpty() && !horse.isBaby();
		}
		if (entity instanceof AbstractNautilus nautilus) {
			boolean hasSaddle = !nautilus.getItemBySlot(EquipmentSlot.SADDLE).isEmpty();
			boolean holdingFood = !mainHand.isEmpty() && nautilus.isFood(mainHand);

			if (nautilus.isTame() && !hasSaddle && !mainHand.isEmpty() && mainHand.is(Items.SADDLE)) {
				return true;
			}
			if (nautilus.isTame() && hasSaddle && !mainHand.isEmpty() && mainHand.is(Items.SHEARS)) {
				return true;
			}
			if (holdingFood) {
				if (!nautilus.isTame()) {
					return true;
				}
				boolean canHeal = nautilus.getHealth() < nautilus.getMaxHealth();
				boolean alreadyInLove = ((com.armaninyow.dynamicoutlines.util.AnimalLoveAccessor) nautilus).dynamicoutlines$isInLove();
				boolean canBreed = !alreadyInLove;
				return canHeal || canBreed;
			}
			if (nautilus.isTame()) {
				return true;
			}
			return false;
		}
		boolean isSaddleCapable = entity instanceof AbstractHorse || entity instanceof Pig || entity instanceof Camel || entity instanceof Strider;
		if (isSaddleCapable && entity instanceof LivingEntity livingEntity && !livingEntity.getItemBySlot(EquipmentSlot.SADDLE).isEmpty()) {
			return true;
		}
		if (isSaddleCapable && entity instanceof LivingEntity livingEntity && livingEntity.getItemBySlot(EquipmentSlot.SADDLE).isEmpty()
				&& !mainHand.isEmpty() && mainHand.is(Items.SADDLE)) {
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
		if (entity instanceof HappyGhast happyGhast) {
			if (happyGhast.isBaby() && !happyGhast.isAgeLocked() && !mainHand.isEmpty() && mainHand.is(Items.SNOWBALL)) {
				return true;
			}
			if (!happyGhast.isBaby() && !mainHand.isEmpty() && mainHand.is(ItemTags.HARNESSES)) {
				return true;
			}
			if (!happyGhast.isBaby() && !happyGhast.getItemBySlot(EquipmentSlot.BODY).isEmpty()
					&& looker instanceof Player player && !player.isShiftKeyDown()) {
				return true;
			}
		}
		if (entity instanceof net.minecraft.world.entity.animal.golem.CopperGolem copperGolem) {
			boolean hasFlower = !copperGolem.getItemBySlot(net.minecraft.world.entity.animal.golem.CopperGolem.EQUIPMENT_SLOT_ANTENNA).isEmpty();
			boolean hasItem = !copperGolem.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
			if (hasItem) {
				return true;
			}
			if (hasFlower && !mainHand.isEmpty() && mainHand.is(Items.SHEARS)) {
				return true;
			}
			boolean isWaxed = ((net.fabricmc.fabric.api.attachment.v1.AttachmentTarget) copperGolem)
					.getAttachedOrElse(com.armaninyow.dynamicoutlines.util.CopperGolemAttachments.WAXED, false);
			if (!mainHand.isEmpty() && mainHand.is(Items.HONEYCOMB) && !isWaxed) {
				return true;
			}
			if (!mainHand.isEmpty() && mainHand.is(ItemTags.AXES)
					&& (isWaxed || copperGolem.getWeatherState() != WeatheringCopper.WeatherState.UNAFFECTED)) {
				return true;
			}
			return false;
		}
		if (entity instanceof Mob && !mainHand.isEmpty() && mainHand.is(Items.NAME_TAG)) {
			return true;
		}
		if (entity instanceof Leashable leashable) {
			if (leashable.isLeashed()) {
				if (leashable.getLeashHolder() == looker) {
					return true;
				}
			} else if (!mainHand.isEmpty() && mainHand.is(Items.LEAD) && leashable.canHaveALeashAttachedTo(looker)) {
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

	private static boolean canUntamedHorseBeFed(AbstractHorse horse, ItemStack mainHand) {
		float heal;
		int ageUp;
		int temper;
		if (mainHand.is(Items.WHEAT)) {
			heal = 2F; ageUp = 20; temper = 3;
		} else if (mainHand.is(Items.SUGAR)) {
			heal = 1F; ageUp = 30; temper = 3;
		} else if (mainHand.is(Items.HAY_BLOCK)) {
			heal = 20F; ageUp = 180; temper = 0;
		} else if (mainHand.is(Items.APPLE)) {
			heal = 3F; ageUp = 60; temper = 3;
		} else if (mainHand.is(Items.RED_MUSHROOM)) {
			heal = 3F; ageUp = 0; temper = 3;
		} else if (mainHand.is(Items.CARROT)) {
			heal = 3F; ageUp = 60; temper = 3;
		} else if (mainHand.is(Items.GOLDEN_CARROT)) {
			heal = 4F; ageUp = 60; temper = 5;
		} else if (mainHand.is(Items.GOLDEN_APPLE) || mainHand.is(Items.ENCHANTED_GOLDEN_APPLE)) {
			heal = 10F; ageUp = 240; temper = 10;
		} else {
			return false;
		}

		boolean canHeal = heal > 0F && horse.getHealth() < horse.getMaxHealth();
		boolean canAgeUp = ageUp > 0 && horse.isBaby() && !horse.isAgeLocked();
		boolean canTemper = temper > 0 && ((com.armaninyow.dynamicoutlines.util.HorseTemperAccessor) horse).dynamicoutlines$getSyncedTemper() < horse.getMaxTemper();
		return canHeal || canAgeUp || canTemper;
	}


	private static boolean hasItemToolConversion(BlockState state, ItemStack mainHand, Level level, BlockPos pos) {
		Object block = state.getBlock();

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
		if ((state.is(Blocks.SUSPICIOUS_SAND) || state.is(Blocks.SUSPICIOUS_GRAVEL)) && mainHand.is(Items.BRUSH)) {
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
		if (state.is(Blocks.FARMLAND) && !mainHand.isEmpty() && mainHand.is(ItemTags.VILLAGER_PLANTABLE_SEEDS)
				&& level.getBlockState(pos.above()).isAir()) {
			return true;
		}
		if (block instanceof CampfireBlock && (mainHand.is(Items.FLINT_AND_STEEL) || mainHand.is(Items.FIRE_CHARGE))
				&& CampfireBlock.canLight(state)) {
			return true;
		}
		if (block instanceof CampfireBlock && CampfireBlock.isLitCampfire(state)
				&& mainHand.getItem() instanceof ShovelItem) {
			return true;
		}
		if (block instanceof CandleBlock && (mainHand.is(Items.FLINT_AND_STEEL) || mainHand.is(Items.FIRE_CHARGE))
				&& !AbstractCandleBlock.isLit(state)) {
			return true;
		}
		return false;
	}

	public static boolean isPlacementBlocked(Level level, BlockPos pos, ItemStack mainHand, Player player, BlockHitResult hitResult) {
		if (player == null || hitResult == null || !(mainHand.getItem() instanceof BlockItem blockItem)) {
			return false;
		}

		boolean sneakingWithItem = player.isShiftKeyDown() && !mainHand.isEmpty();
		BlockState lookedAtState = level.getBlockState(pos);
		if (!sneakingWithItem && lookedAtState.getBlock() instanceof CakeBlock
				&& lookedAtState.hasProperty(CakeBlock.BITES) && lookedAtState.getValue(CakeBlock.BITES) == 0
				&& mainHand.is(ItemTags.CANDLES) && blockItem.getBlock() instanceof CandleBlock) {
			return false;
		}

		BlockPlaceContext placeContext = new BlockPlaceContext(level, player, InteractionHand.MAIN_HAND, mainHand, hitResult);
		if (!placeContext.canPlace()) {
			return true;
		}

		BlockPos targetPos = placeContext.getClickedPos();
		BlockState placementState = blockItem.getBlock().getStateForPlacement(placeContext);
		if (placementState == null) {
			return true;
		}
		if (!placementState.canSurvive(level, targetPos)) {
			return true;
		}
		return !level.isUnobstructed(placementState, targetPos, CollisionContext.placementContext(player));
	}

	public static boolean isMainHandOnlyBlockInteraction(BlockState state, ItemStack mainHand) {
		if (mainHand.getItem() instanceof net.minecraft.world.item.BlockItem) {
			return true;
		}
		if (mainHand.is(Items.WATER_BUCKET) || mainHand.is(Items.LAVA_BUCKET) || mainHand.is(Items.POWDER_SNOW_BUCKET)) {
			return true;
		}
		Object block = state.getBlock();
		return block instanceof ChiseledBookShelfBlock
				|| block instanceof net.minecraft.world.level.block.ShelfBlock
				|| block instanceof DecoratedPotBlock
				|| block instanceof FlowerPotBlock;
	}

	public static boolean isCurrentlyBreakingThisBlock(BlockPos pos, net.minecraft.client.Minecraft minecraft) {
		net.minecraft.client.multiplayer.MultiPlayerGameMode gameMode = minecraft.gameMode;
		if (gameMode == null || !gameMode.isDestroying()) {
			return false;
		}
		BlockPos destroyPos = ((com.armaninyow.dynamicoutlines.mixin.MultiPlayerGameModeAccessor) gameMode)
				.dynamicoutlines$getDestroyBlockPos();
		return pos.equals(destroyPos);
	}

	public static boolean isBlockCurrentlyInteractive(BlockState state, ItemStack mainHand, Level level,
			BlockPos pos, Player player, BlockHitResult hitResult) {
		if (hasItemToolConversion(state, mainHand, level, pos)) {
			return true;
		}

		boolean sneakingWithItem = player != null && player.isShiftKeyDown() && !mainHand.isEmpty();
		if (sneakingWithItem) {
			return false;
		}

		return isInteractive(state) || isContainer(state, level, pos)
				|| hasBlockInteraction(state, mainHand, level, pos, player, hitResult);
	}

	public static boolean hasBlockInteraction(BlockState state, ItemStack mainHand, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		Object block = state.getBlock();

		if (block instanceof CandleCakeBlock) {
			boolean lit = state.hasProperty(BlockStateProperties.LIT) && state.getValue(BlockStateProperties.LIT);

			if (!lit) {
				if (mainHand.is(Items.FLINT_AND_STEEL) || mainHand.is(Items.FIRE_CHARGE)) {
					return true;
				}
				return player != null && (player.isCreative() || player.getFoodData().needsFood());
			}

			if (mainHand.is(Items.FLINT_AND_STEEL) || mainHand.is(Items.FIRE_CHARGE)) {
				return false;
			}
			if (mainHand.getItem() instanceof BlockItem) {
				return false;
			}

			boolean candleHit = hitResult != null && hitResult.getLocation().y - hitResult.getBlockPos().getY() > 0.5;
			if (candleHit) {
				return true;
			}

			return player != null && (player.isCreative() || player.getFoodData().needsFood());
		}
		if (block instanceof CakeBlock) {
			boolean wholeCake = state.hasProperty(CakeBlock.BITES) && state.getValue(CakeBlock.BITES) == 0;
			boolean holdingCandle = mainHand.is(ItemTags.CANDLES) && Block.byItem(mainHand.getItem()) instanceof CandleBlock;
			if (wholeCake && holdingCandle) {
				return false;
			}
			return player != null && (player.isCreative() || player.getFoodData().needsFood());
		}
		if (block instanceof SpawnerBlock) {
			return mainHand.getItem() instanceof SpawnEggItem;
		}
		if (block instanceof CommandBlock) {
			return true;
		}

		if (block instanceof ChiseledBookShelfBlock) {
			boolean slotOccupied = false;
			if (hitResult != null) {
				java.util.OptionalInt slot = ((com.armaninyow.dynamicoutlines.mixin.ChiseledBookShelfBlockAccessor) block)
						.dynamicoutlines$getHitSlot(hitResult, state.getValue(ChiseledBookShelfBlock.FACING));
				if (slot.isPresent()) {
					slotOccupied = state.getValue(ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.get(slot.getAsInt()));
				}
			}
			return slotOccupied || mainHand.is(ItemTags.BOOKSHELF_BOOKS);
		}
		if (block instanceof net.minecraft.world.level.block.CopperGolemStatueBlock) {
			return true;
		}
		if (block instanceof net.minecraft.world.level.block.ShelfBlock) {
			if (!mainHand.isEmpty()) {
				return true;
			}
			if (hitResult == null) {
				return false;
			}
			net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(pos);
			if (!(blockEntity instanceof net.minecraft.world.level.block.entity.ShelfBlockEntity)) {
				return false;
			}
			java.util.OptionalInt slot = ((com.armaninyow.dynamicoutlines.mixin.ChiseledBookShelfBlockAccessor) block)
					.dynamicoutlines$getHitSlot(hitResult, state.getValue(net.minecraft.world.level.block.ShelfBlock.FACING));
			if (slot.isEmpty()) {
				return false;
			}
			net.minecraft.core.NonNullList<ItemStack> items = ((com.armaninyow.dynamicoutlines.mixin.ShelfBlockEntityAccessor) blockEntity)
					.dynamicoutlines$getItems();
			return slot.getAsInt() < items.size() && !items.get(slot.getAsInt()).isEmpty();
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

		if (state.is(Blocks.PUMPKIN) && mainHand.is(Items.SHEARS)) {
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
					&& level.recipeAccess().propertySet(RecipePropertySet.CAMPFIRE_INPUT).test(mainHand)
					&& campfireBlockEntity.getItems().stream().anyMatch(ItemStack::isEmpty)) {
				return true;
			}
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
				if (mainHand.has(DataComponents.DYED_COLOR)) {
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
				boolean works = level.dimension() == net.minecraft.world.level.Level.NETHER;
				if (!works || !RespawnAnchorTracker.isCurrentSpawn(pos, level.dimension())) {
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

		if (block instanceof BedBlock && player != null) {
			boolean explodes = level.dimension() == net.minecraft.world.level.Level.NETHER
					|| level.dimension() == net.minecraft.world.level.Level.END;
			if (explodes) {
				return true;
			}
			if (state.hasProperty(BedBlock.OCCUPIED) && state.getValue(BedBlock.OCCUPIED)) {
				return true;
			}
			boolean isDay = (level.getOverworldClockTime() % 24000) < 12000 && !level.isThundering();
			if (isDay) {
				return false;
			}
			if (!bedInRangeAndUnobstructed(state, level, pos, player)) {
				return false;
			}
			if (player.isCreative()) {
				return true;
			}
			BlockPos headPos = pos;
			if (state.hasProperty(BedBlock.PART) && state.getValue(BedBlock.PART) != net.minecraft.world.level.block.state.properties.BedPart.HEAD
					&& state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
				headPos = pos.relative(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
			}
			Vec3 center = Vec3.atBottomCenterOf(headPos);
			net.minecraft.world.phys.AABB monsterSearchBox = new net.minecraft.world.phys.AABB(center.x() - 8.0, center.y() - 5.0, center.z() - 8.0, center.x() + 8.0, center.y() + 5.0, center.z() + 8.0);
			java.util.List<net.minecraft.world.entity.monster.Monster> nearby = level.getEntitiesOfClass(
					net.minecraft.world.entity.monster.Monster.class, monsterSearchBox
			);
			for (net.minecraft.world.entity.monster.Monster monster : nearby) {
				if (isPreventingPlayerRest(monster, level, player)) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	private static boolean isPreventingPlayerRest(net.minecraft.world.entity.monster.Monster monster, Level level, Player player) {
		net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
		net.minecraft.server.MinecraftServer server = minecraft.getSingleplayerServer();
		if (server != null) {
			net.minecraft.server.level.ServerLevel serverLevel = server.getLevel(level.dimension());
			net.minecraft.server.level.ServerPlayer serverPlayer = server.getPlayerList().getPlayer(player.getUUID());
			if (serverLevel != null && serverPlayer != null) {
				net.minecraft.world.entity.Entity serverEntity = serverLevel.getEntity(monster.getId());
				if (serverEntity instanceof net.minecraft.world.entity.monster.Monster serverMonster) {
					return serverMonster.isPreventingPlayerRest(serverLevel, serverPlayer);
				}
			}
		}
		return monster.isAlive();
	}

	private static boolean bedInRangeAndUnobstructed(BlockState state, Level level, BlockPos pos, Player player) {
		if (!state.hasProperty(BlockStateProperties.HORIZONTAL_FACING) || !state.hasProperty(BedBlock.PART)) {
			return true;
		}
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
		BlockPos footPos = state.getValue(BedBlock.PART) == net.minecraft.world.level.block.state.properties.BedPart.FOOT ? pos : pos.relative(facing.getOpposite());
		BlockPos headPos = footPos.relative(facing);
		boolean inRange = isReachableBed(footPos, player) || isReachableBed(headPos, player);
		if (!inRange) {
			return false;
		}
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
		if (state.getBlock() instanceof CakeBlock || state.getBlock() instanceof CandleCakeBlock) {
			return true;
		}
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
			return !(mainHand.is(Items.SHEARS) || mainHand.is(ItemTags.SWORDS) || hasSilkTouch(mainHand, level));
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
				|| Blocks.STAINED_GLASS.asList().contains(state.getBlock())
				|| Blocks.STAINED_GLASS_PANE.asList().contains(state.getBlock());
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
	private static boolean silkTouchLookupFailed;

	private static boolean hasSilkTouch(ItemStack stack, Level level) {
		if (silkTouchHolderCache == null && !silkTouchLookupFailed) {
			try {
				silkTouchHolderCache = level.registryAccess()
						.lookupOrThrow(Registries.ENCHANTMENT)
						.getOrThrow(Enchantments.SILK_TOUCH);
			} catch (RuntimeException e) {
				silkTouchLookupFailed = true;
			}
		}
		if (silkTouchHolderCache == null) {
			return false;
		}
		return EnchantmentHelper.getItemEnchantmentLevel(silkTouchHolderCache, stack) > 0;
	}

	public static void clearCaches() {
		pottableContentCache = null;
		silkTouchHolderCache = null;
		silkTouchLookupFailed = false;
	}

	private static final float MIN_LIGHT_BLEND_FACTOR = 0.15F;

	public static BlockPos lightSamplePos(BlockPos blockPos, Direction hitFace) {
		return hitFace != null ? blockPos.relative(hitFace) : blockPos;
	}

	public static float lightBlendFactor(Level level, BlockPos pos) {
		ModConfig cfg = ModConfig.get();
		if (!cfg.blendOutlinesWithLight) {
			return 1F;
		}
		int light = level.getMaxLocalRawBrightness(pos);
		float normalized = Mth.clamp(light / 15F, 0F, 1F);
		return MIN_LIGHT_BLEND_FACTOR + (1F - MIN_LIGHT_BLEND_FACTOR) * normalized;
	}

	public static float[] applyLightBlend(float[] rgb, Level level, BlockPos pos) {
		float factor = lightBlendFactor(level, pos);
		return new float[]{rgb[0] * factor, rgb[1] * factor, rgb[2] * factor};
	}

	public static int applyLightBlend(int packedRgb, Level level, BlockPos pos) {
		float factor = lightBlendFactor(level, pos);
		int r = Math.round(((packedRgb >> 16) & 0xFF) * factor);
		int g = Math.round(((packedRgb >> 8) & 0xFF) * factor);
		int b = Math.round((packedRgb & 0xFF) * factor);
		return (r << 16) | (g << 8) | b;
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
		if (entity instanceof EnderMan enderMan) {
			return enderMan.isCreepy();
		}
		if (entity instanceof PolarBear polarBear) {
			return polarBear.isStanding();
		}
		if (entity instanceof Pufferfish pufferfish) {
			return pufferfish.getPuffState() > 0;
		}
		if (entity instanceof AbstractNautilus nautilus) {
			return ((MobTargetAccessor) nautilus).dynamicoutlines$isNautilusAngry();
		}
		if (entity instanceof Enemy && !isConditionallyNeutralMonster(entity)) {
			return true;
		}
		if (entity instanceof NeutralMob neutralMob && neutralMob.isAngry()) {
			return true;
		}
		if (entity instanceof Mob mob) {
			return ((MobTargetAccessor) mob).dynamicoutlines$hasTarget();
		}
		return false;
	}

	private static boolean isConditionallyNeutralMonster(Entity entity) {
		return entity instanceof Spider
				|| entity instanceof Piglin
				|| entity instanceof ZombifiedPiglin
				|| entity instanceof Drowned;
	}
}