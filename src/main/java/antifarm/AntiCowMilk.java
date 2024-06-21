package antifarm;

import config.global.creaturelimiter.creatures.CowConfig;
import config.global.settings.SettingsConfig;
import core.AntiFarmPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class AntiCowMilk implements Listener {

	private final AntiFarmPlugin plugin;
	Random random = new Random();

	public AntiCowMilk(AntiFarmPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getPlayer().getWorld())) return;

		if (event.isCancelled() || event.getPlayer() == null || event.getRightClicked() == null || !CowConfig.getInstance().isEnable() || !event.getRightClicked().getType().equals(EntityType.COW))
			return;

		Entity cow = event.getRightClicked();
		int milked = cow.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "milked"), PersistentDataType.INTEGER, 0);
		Long lastFeed = cow.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "lastFeed"), PersistentDataType.LONG, new Date().getTime() - 600000);
		int elapsedTime = (int) (Duration.between(LocalDateTime.ofInstant(Instant.ofEpochMilli(lastFeed), TimeZone.getDefault().toZoneId()), LocalDateTime.now()).toSeconds());
		int cooldown = CowConfig.getInstance().getMilkCooldownSec();
		int remainingTime = cooldown - elapsedTime;

		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BUCKET) || event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.BUCKET)) {
			if (milked == 0 && elapsedTime >= cooldown) {
				cow.getPersistentDataContainer().set(new NamespacedKey(plugin, "milked"), PersistentDataType.INTEGER, 1);
				event.getPlayer().sendMessage(SettingsConfig.getInstance().getPrefix() + CowConfig.getInstance().getMilkMsg().replaceAll("%time%", String.valueOf(remainingTime)));
			} else if (elapsedTime < cooldown) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(SettingsConfig.getInstance().getPrefix() + CowConfig.getInstance().getCooldownMsg().replaceAll("%time%", String.valueOf(remainingTime)));
			} else if (milked == 1) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(SettingsConfig.getInstance().getPrefix() + CowConfig.getInstance().getMalnutritionWarningMsg().replaceAll("%time%", String.valueOf(remainingTime)));
			}
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityEnterLoveMode(EntityEnterLoveModeEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getHumanEntity() == null || event.getEntity() == null || !CowConfig.getInstance().isEnable() || !event.getEntity().getType().equals(EntityType.COW))
			return;

		Entity cow = event.getEntity();
		int milked = cow.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "milked"), PersistentDataType.INTEGER, 0);

		cow.getPersistentDataContainer().set(new NamespacedKey(plugin, "lastFeed"), PersistentDataType.LONG, new Date().getTime());

		if (milked == 1) {
			cow.getPersistentDataContainer().set(new NamespacedKey(plugin, "milked"), PersistentDataType.INTEGER, 0);
			event.getHumanEntity().sendMessage(SettingsConfig.getInstance().getPrefix() + CowConfig.getInstance().getFeedMsg());
		}

	}

}
