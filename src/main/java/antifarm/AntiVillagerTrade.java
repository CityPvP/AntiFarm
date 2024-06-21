package antifarm;

import config.global.settings.SettingsConfig;
import config.global.villager.VillagerSettingsConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class AntiVillagerTrade implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPlayerInteractAtEntity(PlayerInteractEntityEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getPlayer().getWorld())) return;

		if (event.isCancelled() || !event.getRightClicked().getType().equals(EntityType.VILLAGER) && !event.getRightClicked().getType().equals(EntityType.WANDERING_TRADER))
			return;

		if (event.getRightClicked().getType().equals(EntityType.VILLAGER))
			event.setCancelled(VillagerSettingsConfig.getInstance().isPreventVillagerTrade());
		if (event.getRightClicked().getType().equals(EntityType.WANDERING_TRADER))
			event.setCancelled(VillagerSettingsConfig.getInstance().isPreventWanderingTraderTrade());

	}

}
