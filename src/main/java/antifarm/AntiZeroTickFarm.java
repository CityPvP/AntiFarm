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

public class AntiZeroTickFarm implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPistonExtend(BlockPistonExtendEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled()) return;
		if (!FarmsSettingsConfig.getInstance().isPreventZerotickFarms()) return;

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

		if (event.isCancelled()) return;
		if (!FarmsSettingsConfig.getInstance().isPreventZerotickFarms()) return;

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
			if (AntiFarmConfigurations.GLOBAL.getFarmlandBlocks().contains(block.getType())) {
				if (AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(block.getRelative(BlockFace.UP).getType())) {
					return true;
				}
			}
		}

		return false;

	}

}
