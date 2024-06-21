package antifarm;

import config.global.creaturelimiter.creatures.ChickenConfig;
import config.global.settings.SettingsConfig;
import core.AntiFarmPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class AntiChickenEggFarm implements Listener {

	private final AntiFarmPlugin plugin;
	Random random = new Random();

	public AntiChickenEggFarm(AntiFarmPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityDropItem(EntityDropItemEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.getEntity() == null || event.getItemDrop() == null || !event.getEntityType().equals(EntityType.CHICKEN) || !event.getItemDrop().getType().equals(EntityType.EGG) || !ChickenConfig.getInstance().isEnable())
			return;

		Entity chicken = event.getEntity();
		int currentEgg = chicken.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "currentEgg"), PersistentDataType.INTEGER, 0);
		int maxEgg = chicken.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "maxEgg"), PersistentDataType.INTEGER, 0);

		if (maxEgg == 0) {
			maxEgg = random.nextInt(ChickenConfig.getInstance().getEggMax() - ChickenConfig.getInstance().getEggMin()) + ChickenConfig.getInstance().getEggMin();
			chicken.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxEgg"), PersistentDataType.INTEGER, maxEgg);
		}

		if (currentEgg < maxEgg) {
			chicken.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentEgg"), PersistentDataType.INTEGER, currentEgg + 1);
		} else {
			event.setCancelled(true);
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityEnterLoveMode(EntityEnterLoveModeEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getHumanEntity() == null || event.getEntity() == null || !ChickenConfig.getInstance().isEnable() || !event.getEntity().getType().equals(EntityType.CHICKEN))
			return;

		Entity chicken = event.getEntity();
		int currentEgg = chicken.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "currentEgg"), PersistentDataType.INTEGER, 0);
		int maxEgg = chicken.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "maxEgg"), PersistentDataType.INTEGER, 0);

		if (currentEgg >= maxEgg) {
			maxEgg = random.nextInt(ChickenConfig.getInstance().getEggMax() - ChickenConfig.getInstance().getEggMin()) + ChickenConfig.getInstance().getEggMin();
			chicken.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentEgg"), PersistentDataType.INTEGER, 0);
			chicken.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxEgg"), PersistentDataType.INTEGER, maxEgg);
			event.getHumanEntity().sendMessage(SettingsConfig.getInstance().getPrefix() + ChickenConfig.getInstance().getFeedMsg());
		}

	}

}
