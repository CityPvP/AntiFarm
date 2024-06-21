package antifarm;

import config.AntiFarmConfigurations;
import config.global.dispenser.AntiDispenserConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockShearEntityEvent;

public class AntiDispenser implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onDispense(BlockDispenseEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || !event.getBlock().getType().equals(Material.DISPENSER)) return;
		if (!AntiDispenserConfig.getInstance().isEnable() || !AntiDispenserConfig.getInstance().getBlockedItems().contains(event.getItem().getType()))
			return;

		Dispenser dispenser = (Dispenser) event.getBlock().getBlockData();
		Block block = event.getBlock().getRelative(dispenser.getFacing());

		if (!AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(block.getType())) return;

		event.setCancelled(true);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockShearEntity(BlockShearEntityEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || !event.getEntity().getType().equals(EntityType.SHEEP)) return;
		if (!AntiDispenserConfig.getInstance().isEnable() || !AntiDispenserConfig.getInstance().isPreventDispenserShearing())
			return;

		event.setCancelled(true);

	}

}
