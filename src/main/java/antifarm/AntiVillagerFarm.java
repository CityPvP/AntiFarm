package antifarm;

import config.global.settings.SettingsConfig;
import config.global.villager.VillagerSettingsConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class AntiVillagerFarm implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityChangeBlock(EntityChangeBlockEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled()) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (!VillagerSettingsConfig.getInstance().isPreventVillagersHarvestingFarms()) return;

		event.setCancelled(true);

	}

}
