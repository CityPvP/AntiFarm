package antifarm;

import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.inventory.ItemStack;

public class AntiStringDupe implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockBreak(BlockBreakEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getPlayer() == null || event.getBlock() == null || !event.getBlock().getType().equals(Material.TRIPWIRE))
			return;
		if (!FarmsSettingsConfig.getInstance().isPreventStringDupe()) return;

		event.getBlock().setType(Material.AIR);
		event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.STRING));

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockFromTo(BlockFromToEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;


		if (event.getBlock() == null || event.getToBlock() == null) return;
		final Material blockType = event.getBlock().getType();
		if (!blockType.equals(Material.WATER) && !blockType.equals(Material.LAVA) && !blockType.toString().contains("TRAPDOOR") || !event.getToBlock().getType().equals(Material.TRIPWIRE))
			return;
		if (!FarmsSettingsConfig.getInstance().isPreventStringDupe()) return;

		event.setCancelled(true);
		event.getToBlock().setType(Material.AIR);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockDispense(BlockDispenseEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getBlock() == null || event.getItem() == null || event.getVelocity() == null || !event.getBlock().getType().equals(Material.DISPENSER))
			return;
		if (!event.getItem().getType().equals(Material.WATER_BUCKET) && !event.getItem().getType().equals(Material.LAVA_BUCKET))
			return;

		Dispenser dispenser = (Dispenser) event.getBlock().getBlockData();
		Block block = event.getBlock().getRelative(dispenser.getFacing());

		if (!block.getType().equals(Material.TRIPWIRE) || !FarmsSettingsConfig.getInstance().isPreventStringDupe())
			return;

		event.setCancelled(true);

	}

}
