package antifarm;

import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import core.AntiFarmPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent.ChangeReason;

public class AntiLavaFarm implements Listener {

	private final AntiFarmPlugin plugin;

	public AntiLavaFarm(AntiFarmPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onCauldronLevelChange(CauldronLevelChangeEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || !event.getBlock().getType().equals(Material.CAULDRON) || !event.getReason().equals(ChangeReason.NATURAL_FILL) || !FarmsSettingsConfig.getInstance().isPreventInfinityLava())
			return;

		Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!event.getBlock().getType().equals(Material.LAVA_CAULDRON)) return;
            for (int i = 1; i < 25; i++) {
                Block block = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY() + i, event.getBlock().getZ()).getBlock();
                final Material blockType = block.getType();
				if (!blockType.equals(Material.AIR) && !blockType.equals(Material.CAVE_AIR) && !blockType.equals(Material.POINTED_DRIPSTONE) && !blockType.equals(Material.DRIPSTONE_BLOCK) && !blockType.equals(Material.LAVA))
                    return;
                if (blockType.equals(Material.LAVA)) {
                    block.setType(Material.AIR);
                    return;
                }
            }
        }, 2);


	}

}
