package antifarm;

import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import core.AntiFarmPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AntiCactusFarm implements Listener {

	private final AntiFarmPlugin plugin;

	public AntiCactusFarm(AntiFarmPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockGrow(BlockGrowEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getBlock() == null) return;
		if (!event.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.CACTUS)) return;
		if (!FarmsSettingsConfig.getInstance().isPreventCactusFarms()) return;

		Block block = event.getBlock();
		Location bLoc = block.getLocation();
		List<Block> domeBlocks = new ArrayList<Block>();
		Collections.addAll(domeBlocks, block, block.getRelative(BlockFace.UP), block.getRelative(BlockFace.NORTH), block.getRelative(BlockFace.SOUTH), block.getRelative(BlockFace.EAST), block.getRelative(BlockFace.WEST));

		for (Block dBlock : domeBlocks) {
			final Material dBlockType = dBlock.getType();
			if (!(dBlockType.equals(Material.CACTUS)) && !(dBlockType.equals(Material.AIR)) && !(dBlockType.equals(Material.CAVE_AIR))) {

				event.setCancelled(true);

				if (SettingsConfig.getInstance().isBreakBlocks()) {
					for (int i = 0; i < 4; i++) {

						Block replace = block.getWorld().getBlockAt(bLoc.getBlockX(), bLoc.getBlockY() - i, bLoc.getBlockZ());
						final Material replaceBlockType = replace.getType();

						if (replaceBlockType.equals(Material.CACTUS) || replaceBlockType.equals(Material.SAND)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
								replace.breakNaturally();
								replace.setType(Material.AIR);
							}, 1);
						}
					}
				}

				break;
			}
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPistonExtend(BlockPistonExtendEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getBlock() == null || event.getDirection() == null) return;
		if (FarmsSettingsConfig.getInstance().isPreventPistonFarms()) return;
		if (!FarmsSettingsConfig.getInstance().isPreventCactusFarms()) return;

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
		if (FarmsSettingsConfig.getInstance().isPreventPistonFarms()) return;
		if (!FarmsSettingsConfig.getInstance().isPreventCactusFarms()) return;

		Block piston = event.getBlock();
		BlockFace direction = event.getDirection();
		List<Block> pistonBlocks;

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
			Block checkBlock = block.getRelative(direction);
			if (checkBlock.getRelative(BlockFace.NORTH).getType().equals(Material.CACTUS) || checkBlock.getRelative(BlockFace.SOUTH).getType().equals(Material.CACTUS) || checkBlock.getRelative(BlockFace.EAST).getType().equals(Material.CACTUS) || checkBlock.getRelative(BlockFace.WEST).getType().equals(Material.CACTUS)) {
				return true;
			}
		}

		return false;

	}

}