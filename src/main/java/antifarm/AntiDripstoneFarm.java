package antifarm;

import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class AntiDripstoneFarm implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockPhysics(BlockPhysicsEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || !event.getBlock().getType().equals(Material.POINTED_DRIPSTONE) || !FarmsSettingsConfig.getInstance().isPreventDripstoneFarms())
			return;

		Block block = event.getBlock();
		PointedDripstone dripstone = (PointedDripstone) block.getBlockData();

		if (dripstone.getVerticalDirection().equals(BlockFace.UP)) {
			if (!block.getRelative(BlockFace.DOWN).getType().equals(Material.STONE)) {
				block.setType(Material.AIR);
			}
		}

	}


}
