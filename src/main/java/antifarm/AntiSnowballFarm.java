package antifarm;

import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;

public class AntiSnowballFarm implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityBlockForm(EntityBlockFormEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.SNOWMAN)) return;
		if (!event.getNewState().getType().equals(Material.SNOW)) return;
		if (!FarmsSettingsConfig.getInstance().isPreventSnowballFarms()) return;

		event.setCancelled(true);

	}

}
