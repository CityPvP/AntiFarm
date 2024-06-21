package core;

import antifarm.*;
import config.AntiFarmConfigurations;
import config.global.settings.SettingsConfig;
import metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import update.UpdateChecker;

public class AntiFarmPlugin extends JavaPlugin implements Listener {

	private static AntiFarmPlugin instance;

	public static AntiFarmPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;

		AntiFarmConfigurations.reload();

		registerEvents(this, new AntiPistonFarm(), new AntiVillagerFarm(), new AntiWaterFarm(), new AntiCactusFarm(this),
				new AntiEndermanFarm(), new AntiVillagerBreed(), new AntiMobFarm(this), new AntiLightlessFarm(),
				new AntiDispenser(), new AntiFishFarm(), new AntiWaterlessFarm(), new AntiMobSpawner(this),
				new AntiVillagerTransform(), new AntiVillagerTarget(), new AntiVillageGuard(), new AntiSnowballFarm(),
				new AntiRaidFarm(), new AntiBerryFarm(), new AntiZeroTickFarm(), new AntiBlockPhysics(this),
				new AntiFroglightFarm(this), new AntiVillagerCareer(), new AntiVillagerTrade(), new AntiStringDupe(),
				new AntiChickenEggFarm(this), new AntiCowMilk(this), new AntiLavaFarm(this), new OptimizeInteraction(this));

		getCommand("antifarm").setExecutor(new Commands(this));

		final SettingsConfig settings = SettingsConfig.getInstance();

		if (settings.isBreakBlocks()) {
			@SuppressWarnings("unused")
			Metrics metrics = new Metrics(this, 14827);
		}

		if (settings.isUpdateCheck()) {
			updateCheck();
		}

	}

	@Override
	public void onDisable() {
	}

	private static void registerEvents(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, plugin);
		}
	}

	private void updateCheck() {
		new UpdateChecker(this, 99472).getVersion(version -> {
			getLogger().info("Checking update...");
			if (this.getDescription().getVersion().equals(version)) {
				getLogger().info("There is not a new update available.");
			} else {
				getLogger().info("There is a new update available.");
				getLogger().info("Current version: " + getDescription().getVersion());
				getLogger().info("Latest version: " + version);
				getLogger().info("Spigot: https://www.spigotmc.org/resources/anti-farm.99472/");
			}
		});
	}
}