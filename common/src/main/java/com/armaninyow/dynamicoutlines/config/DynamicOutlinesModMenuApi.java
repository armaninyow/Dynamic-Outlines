package com.armaninyow.dynamicoutlines.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public class DynamicOutlinesModMenuApi implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			ModConfig cfg = ModConfig.get();
			ModConfig defaults = new ModConfig();

			ConfigBuilder builder = ConfigBuilder.create()
					.setParentScreen(parent)
					.setTitle(Component.translatable("config.dynamicoutlines.title"));

			builder.setSavingRunnable(ModConfig::save);

			ConfigEntryBuilder eb = builder.entryBuilder();

			ConfigCategory settings = builder.getOrCreateCategory(Component.translatable("config.dynamicoutlines.category.settings"));

			settings.addEntry(eb.startIntSlider(Component.translatable("config.dynamicoutlines.stroke_width"), cfg.strokeWidth, 1, 8)
					.setDefaultValue(defaults.strokeWidth)
					.setTooltip(Component.translatable("config.dynamicoutlines.stroke_width.tooltip"))
					.setSaveConsumer(v -> cfg.strokeWidth = v)
					.build());
			settings.addEntry(eb.startIntSlider(Component.translatable("config.dynamicoutlines.outline_alpha"), cfg.outlineAlpha, 0, 100)
					.setDefaultValue(defaults.outlineAlpha)
					.setTooltip(Component.translatable("config.dynamicoutlines.outline_alpha.tooltip"))
					.setSaveConsumer(v -> cfg.outlineAlpha = v)
					.build());

			settings.addEntry(eb.startEnumSelector(Component.translatable("config.dynamicoutlines.block_outline_mode"),
							ModConfig.BlockOutlineMode.class, cfg.blockOutlineMode)
					.setDefaultValue(defaults.blockOutlineMode)
					.setTooltip(Component.translatable("config.dynamicoutlines.block_outline_mode.tooltip"))
					.setEnumNameProvider(v -> Component.translatable(
							"config.dynamicoutlines.block_outline_mode." + ((ModConfig.BlockOutlineMode) v).name().toLowerCase()))
					.setSaveConsumer(v -> cfg.blockOutlineMode = v)
					.build());
			settings.addEntry(eb.startColorField(Component.translatable("config.dynamicoutlines.interactive_color"), cfg.interactiveColor)
					.setDefaultValue(defaults.interactiveColor)
					.setTooltip(Component.translatable("config.dynamicoutlines.interactive_color.tooltip"))
					.setSaveConsumer(v -> cfg.interactiveColor = v)
					.build());
			settings.addEntry(eb.startColorField(Component.translatable("config.dynamicoutlines.noninteractive_color"), cfg.nonInteractiveColor)
					.setDefaultValue(defaults.nonInteractiveColor)
					.setTooltip(Component.translatable("config.dynamicoutlines.noninteractive_color.tooltip"))
					.setSaveConsumer(v -> cfg.nonInteractiveColor = v)
					.build());
			settings.addEntry(eb.startEnumSelector(Component.translatable("config.dynamicoutlines.missing_tool_visibility"),
						ModConfig.MissingToolVisibility.class, cfg.missingToolVisibility)
					.setDefaultValue(defaults.missingToolVisibility)
					.setTooltip(Component.translatable("config.dynamicoutlines.missing_tool_visibility.tooltip"))
					.setEnumNameProvider(v -> Component.translatable(
							"config.dynamicoutlines.missing_tool_visibility." + ((ModConfig.MissingToolVisibility) v).name().toLowerCase()))
					.setSaveConsumer(v -> cfg.missingToolVisibility = v)
					.build());

			settings.addEntry(eb.startEnumSelector(Component.translatable("config.dynamicoutlines.entity_outline_mode"),
							ModConfig.EntityOutlineMode.class, cfg.entityOutlineMode)
					.setDefaultValue(defaults.entityOutlineMode)
					.setTooltip(Component.translatable("config.dynamicoutlines.entity_outline_mode.tooltip"))
					.setEnumNameProvider(v -> Component.translatable(
							"config.dynamicoutlines.entity_outline_mode." + ((ModConfig.EntityOutlineMode) v).name().toLowerCase()))
					.setSaveConsumer(v -> cfg.entityOutlineMode = v)
					.build());
			settings.addEntry(eb.startColorField(Component.translatable("config.dynamicoutlines.entity_color"), cfg.entityColor)
					.setDefaultValue(defaults.entityColor)
					.setTooltip(Component.translatable("config.dynamicoutlines.entity_color.tooltip"))
					.setSaveConsumer(v -> cfg.entityColor = v)
					.build());
			settings.addEntry(eb.startColorField(Component.translatable("config.dynamicoutlines.enemy_color"), cfg.enemyColor)
					.setDefaultValue(defaults.enemyColor)
					.setTooltip(Component.translatable("config.dynamicoutlines.enemy_color.tooltip"))
					.setSaveConsumer(v -> cfg.enemyColor = v)
					.build());
			settings.addEntry(eb.startColorField(Component.translatable("config.dynamicoutlines.entity_interaction_color"), cfg.entityInteractionColor)
					.setDefaultValue(defaults.entityInteractionColor)
					.setTooltip(Component.translatable("config.dynamicoutlines.entity_interaction_color.tooltip"))
					.setSaveConsumer(v -> cfg.entityInteractionColor = v)
					.build());

			return builder.build();
		};
	}
}