package antifarm;

import config.AntiFarmConfigurations;
import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class AntiEndermanFarm implements Listener {


	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityChangeBlock(EntityChangeBlockEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled()) return;
		if (!event.getEntity().getType().equals(EntityType.ENDERMAN)) return;
		if (!FarmsSettingsConfig.getInstance().isPreventEndermanHarvestingFarms()) return;
		if (!AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(event.getBlock().getType())) return;

		event.setCancelled(true);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityPickupItem(EntityPickupItemEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled()) return;
		if (!event.getEntity().getType().equals(EntityType.ENDERMAN)) return;
		if (!FarmsSettingsConfig.getInstance().isPreventEndermanHarvestingFarms()) return;
		if (!AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(event.getItem().getItemStack().getType())) return;

		event.setCancelled(true);

	}

}
