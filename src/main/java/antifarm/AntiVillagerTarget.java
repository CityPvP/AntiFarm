package antifarm;

import config.global.settings.SettingsConfig;
import config.global.villager.VillagerSettingsConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class AntiVillagerTarget implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityTarget(EntityTargetEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null || event.getTarget() == null) return;
		if (!event.getTarget().getType().equals(EntityType.VILLAGER)) return;
		if (!VillagerSettingsConfig.getInstance().isPreventTargetingVillager()) return;

		event.setCancelled(true);

	}

}
