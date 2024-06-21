package antifarm;

import config.global.settings.SettingsConfig;
import config.global.villager.VillagerSettingsConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class AntiVillagerBreed implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerPickup(EntityPickupItemEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (!VillagerSettingsConfig.getInstance().isPreventVillagersBreed()) return;

		Villager villager = (Villager) event.getEntity();
		villager.setBreed(false);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerDrop(EntityDropItemEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (!VillagerSettingsConfig.getInstance().isPreventVillagersBreed()) return;

		Villager villager = (Villager) event.getEntity();
		villager.setBreed(false);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerBreed(EntityBreedEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (event.getMother() == null || event.getFather() == null) return;
		if (!VillagerSettingsConfig.getInstance().isPreventVillagersBreed()) return;

		event.setCancelled(true);

		Villager mother = (Villager) event.getMother();
		Villager father = (Villager) event.getFather();

		mother.setBreed(false);
		father.setBreed(false);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerSpawn(CreatureSpawnEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (!event.getSpawnReason().equals(SpawnReason.BREEDING)) return;
		if (!VillagerSettingsConfig.getInstance().isPreventVillagersBreed()) return;

		event.setCancelled(true);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityEnterLoveMode(EntityEnterLoveModeEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || !VillagerSettingsConfig.getInstance().isPreventVillagersBreed() || !event.getEntity().getType().equals(EntityType.VILLAGER))
			return;

		Villager villager = (Villager) event.getEntity();
		villager.setBreed(false);

	}

}
