package com.armaninyow.dynamicoutlines.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.minecraft.network.chat.Component;

import java.awt.Color;

public class DynamicOutlinesModMenuApi implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			ModConfig cfg = ModConfig.get();
			ModConfig defaults = new ModConfig();

			YetAnotherConfigLib yacl = YetAnotherConfigLib.createBuilder()
					.title(Component.translatable("config.dynamicoutlines.title"))
					.category(ConfigCategory.createBuilder()
							.name(Component.translatable("config.dynamicoutlines.category.general"))
							.option(Option.<Integer>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.stroke_width"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.stroke_width.tooltip")))
									.binding(defaults.strokeWidth, () -> cfg.strokeWidth, v -> cfg.strokeWidth = v)
									.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 8).step(1))
									.build())
							.option(Option.<Integer>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.outline_alpha"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.outline_alpha.tooltip")))
									.binding(defaults.outlineAlpha, () -> cfg.outlineAlpha, v -> cfg.outlineAlpha = v)
									.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 100).step(1))
									.build())
							.option(Option.<Boolean>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.blend_with_light"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.blend_with_light.tooltip")))
									.binding(defaults.blendOutlinesWithLight, () -> cfg.blendOutlinesWithLight, v -> cfg.blendOutlinesWithLight = v)
									.controller(BooleanControllerBuilder::create)
									.build())
							.build())
					.category(ConfigCategory.createBuilder()
							.name(Component.translatable("config.dynamicoutlines.category.blocks"))
							.option(Option.<ModConfig.BlockOutlineMode>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.block_outline_mode"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.block_outline_mode.tooltip")))
									.binding(defaults.blockOutlineMode, () -> cfg.blockOutlineMode, v -> cfg.blockOutlineMode = v)
									.controller(opt -> EnumControllerBuilder.create(opt)
											.enumClass(ModConfig.BlockOutlineMode.class)
											.formatValue(v -> Component.translatable(
													"config.dynamicoutlines.block_outline_mode." + v.name().toLowerCase())))
									.build())
							.option(Option.<Color>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.interactive_color"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.interactive_color.tooltip")))
									.binding(intToColor(defaults.interactiveColor),
											() -> intToColor(cfg.interactiveColor),
											v -> cfg.interactiveColor = colorToInt(v))
									.controller(ColorControllerBuilder::create)
									.build())
							.option(Option.<Color>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.noninteractive_color"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.noninteractive_color.tooltip")))
									.binding(intToColor(defaults.nonInteractiveColor),
											() -> intToColor(cfg.nonInteractiveColor),
											v -> cfg.nonInteractiveColor = colorToInt(v))
									.controller(ColorControllerBuilder::create)
									.build())
							.option(Option.<ModConfig.MissingToolVisibility>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.missing_tool_visibility"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.missing_tool_visibility.tooltip")))
									.binding(defaults.missingToolVisibility, () -> cfg.missingToolVisibility, v -> cfg.missingToolVisibility = v)
									.controller(opt -> EnumControllerBuilder.create(opt)
											.enumClass(ModConfig.MissingToolVisibility.class)
											.formatValue(v -> Component.translatable(
													"config.dynamicoutlines.missing_tool_visibility." + v.name().toLowerCase())))
									.build())
							.option(Option.<Boolean>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.show_outline_when_placement_blocked"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.show_outline_when_placement_blocked.tooltip")))
									.binding(defaults.showOutlineWhenPlacementBlocked, () -> cfg.showOutlineWhenPlacementBlocked, v -> cfg.showOutlineWhenPlacementBlocked = v)
									.controller(BooleanControllerBuilder::create)
									.build())
							.option(Option.<Boolean>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.currently_breaking_override"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.currently_breaking_override.tooltip")))
									.binding(defaults.currentlyBreakingOverride, () -> cfg.currentlyBreakingOverride, v -> cfg.currentlyBreakingOverride = v)
									.controller(BooleanControllerBuilder::create)
									.build())
							.build())
					.category(ConfigCategory.createBuilder()
							.name(Component.translatable("config.dynamicoutlines.category.entities"))
							.option(Option.<ModConfig.EntityOutlineMode>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.entity_outline_mode"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.entity_outline_mode.tooltip")))
									.binding(defaults.entityOutlineMode, () -> cfg.entityOutlineMode, v -> cfg.entityOutlineMode = v)
									.controller(opt -> EnumControllerBuilder.create(opt)
											.enumClass(ModConfig.EntityOutlineMode.class)
											.formatValue(v -> Component.translatable(
													"config.dynamicoutlines.entity_outline_mode." + v.name().toLowerCase())))
									.build())
							.option(Option.<Color>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.entity_color"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.entity_color.tooltip")))
									.binding(intToColor(defaults.entityColor),
											() -> intToColor(cfg.entityColor),
											v -> cfg.entityColor = colorToInt(v))
									.controller(ColorControllerBuilder::create)
									.build())
							.option(Option.<Color>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.enemy_color"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.enemy_color.tooltip")))
									.binding(intToColor(defaults.enemyColor),
											() -> intToColor(cfg.enemyColor),
											v -> cfg.enemyColor = colorToInt(v))
									.controller(ColorControllerBuilder::create)
									.build())
							.option(Option.<Color>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.entity_interaction_color"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.entity_interaction_color.tooltip")))
									.binding(intToColor(defaults.entityInteractionColor),
											() -> intToColor(cfg.entityInteractionColor),
											v -> cfg.entityInteractionColor = colorToInt(v))
									.controller(ColorControllerBuilder::create)
									.build())
							.option(Option.<Boolean>createBuilder()
									.name(Component.translatable("config.dynamicoutlines.outline_invisible_mobs"))
									.description(OptionDescription.of(Component.translatable("config.dynamicoutlines.outline_invisible_mobs.tooltip")))
									.binding(defaults.outlineInvisibleMobs, () -> cfg.outlineInvisibleMobs, v -> cfg.outlineInvisibleMobs = v)
									.controller(BooleanControllerBuilder::create)
									.build())
							.build())
					.save(ModConfig::save)
					.build();

			return yacl.generateScreen(parent);
		};
	}

	private static Color intToColor(int rgb) {
		return new Color(rgb, false);
	}

	private static int colorToInt(Color color) {
		return color.getRGB() & 0xFFFFFF;
	}
}