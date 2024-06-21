package antifarm;

import config.AntiFarmConfigurations;
import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntiPistonFarm implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPistonExtend(BlockPistonExtendEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getBlock() == null || event.getDirection() == null) return;
		if (!FarmsSettingsConfig.getInstance().isPreventPistonFarms()) return;

		Block piston = event.getBlock();
		BlockFace direction = event.getDirection();
		List<Block> pistonBlocks = new ArrayList<Block>();

		if (event.getBlocks().isEmpty()) {
			pistonBlocks = Arrays.asList(event.getBlock());
		} else {
			pistonBlocks = event.getBlocks();
		}

		if (!checkPistonBlocks(piston, direction, pistonBlocks)) return;

		event.setCancelled(true);

		if (!SettingsConfig.getInstance().isBreakPistons()) return;

		piston.breakNaturally();
		piston.setType(Material.AIR);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPistonRetract(BlockPistonRetractEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getBlock() == null || event.getDirection() == null) return;
		if (!FarmsSettingsConfig.getInstance().isPreventPistonFarms()) return;

		Block piston = event.getBlock();
		BlockFace direction = event.getDirection();
		List<Block> pistonBlocks = new ArrayList<Block>();

		if (event.getBlocks().isEmpty()) {
			pistonBlocks = Arrays.asList(event.getBlock());
		} else {
			pistonBlocks = event.getBlocks();
		}

		if (!checkPistonBlocks(piston, direction, pistonBlocks)) return;

		event.setCancelled(true);

		if (!SettingsConfig.getInstance().isBreakPistons()) return;

		piston.breakNaturally();
		piston.setType(Material.AIR);

	}

	private boolean checkPistonBlocks(Block piston, BlockFace direction, List<Block> pistonBlocks) {

		for (Block block : pistonBlocks) {

			final Material type = block.getType();

			if (AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(type)) return true;

			if (AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(Material.COCOA)) {
				if (block.getType().equals(Material.JUNGLE_LOG)) {
					if (block.getRelative(BlockFace.EAST).getType().equals(Material.COCOA) || block.getRelative(BlockFace.NORTH).getType().equals(Material.COCOA) || block.getRelative(BlockFace.SOUTH).getType().equals(Material.COCOA) || block.getRelative(BlockFace.WEST).getType().equals(Material.COCOA)) {
						return true;
					}
				}
			}

			if (FarmsSettingsConfig.getInstance().isPreventCactusFarms()) {
				if (AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(Material.CACTUS)) {
					Block checkBlock = block.getRelative(direction);
					if (checkBlock.getRelative(BlockFace.NORTH).getType().equals(Material.CACTUS) || checkBlock.getRelative(BlockFace.SOUTH).getType().equals(Material.CACTUS) || checkBlock.getRelative(BlockFace.EAST).getType().equals(Material.CACTUS) || checkBlock.getRelative(BlockFace.WEST).getType().equals(Material.CACTUS)) {
						return true;
					}
				}
			}

		}

		return false;

	}

}
