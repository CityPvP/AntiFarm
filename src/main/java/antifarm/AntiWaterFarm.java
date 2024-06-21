package antifarm;

import config.AntiFarmConfigurations;
import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class AntiWaterFarm implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockFromTo(BlockFromToEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.getBlock() == null || event.getToBlock() == null || event.getFace() == null) return;
		if (!event.getBlock().getType().equals(Material.WATER)) return;
		if (!FarmsSettingsConfig.getInstance().isPreventWaterHarvestingFarms()) return;
		if (!AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(event.getBlock().getRelative(event.getBlock().getFace(event.getToBlock())).getType()))
			return;

		event.setCancelled(true);

		if (!SettingsConfig.getInstance().isBreakBlocks()) return;

		event.getBlock().getRelative(event.getBlock().getFace(event.getToBlock())).setType(Material.AIR);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.getPlayer() == null || event.getBlock() == null || event.getBlockClicked() == null || event.getBlockFace() == null || event.getBucket() == null)
			return;
		if (!event.getBucket().equals(Material.WATER_BUCKET)) return;
		if (!FarmsSettingsConfig.getInstance().isPreventWaterHarvestingFarms()) return;
		if (!AntiFarmConfigurations.GLOBAL.getFarmBlocks().contains(event.getBlockClicked().getRelative(event.getBlockFace()).getType()))
			return;

		event.setCancelled(true);

	}

}