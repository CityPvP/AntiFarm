package antifarm;

import config.global.settings.SettingsConfig;
import config.global.villager.golem.PreventGolemSpawningConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class AntiVillageGuard implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onCreatureSpawn(CreatureSpawnEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.IRON_GOLEM)) return;

		if (event.getSpawnReason().equals(SpawnReason.VILLAGE_DEFENSE)) {

			if (!PreventGolemSpawningConfig.getInstance().isVillageDefense()) return;

			event.setCancelled(true);

		} else if (event.getSpawnReason().equals(SpawnReason.VILLAGE_INVASION)) {

			if (!PreventGolemSpawningConfig.getInstance().isVillageRaids()) return;

			event.setCancelled(true);

		}

	}

}
