package antifarm;

import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import core.AntiFarmPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class AntiBlockPhysics implements Listener {

    private final AntiFarmPlugin plugin;

    public AntiBlockPhysics(AntiFarmPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBlockPhysics(BlockPhysicsEvent event) {
        if (event.isCancelled()) return;
        if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

        final Material sourceBlockType = event.getSourceBlock().getType();
        final Material material = event.getBlock().getType();

        if (!sourceBlockType.equals(Material.SAND) && !sourceBlockType.equals(Material.RED_SAND) && !sourceBlockType.toString().contains("CONCRETE_POWDER") && !sourceBlockType.equals(Material.DRAGON_EGG)) {
            if (material.equals(Material.CACTUS) && FarmsSettingsConfig.getInstance().isPreventCactusFarms()) {
                event.setCancelled(true);
                event.getSourceBlock().breakNaturally();
                event.getSourceBlock().setType(Material.AIR);

                if (SettingsConfig.getInstance().isBreakBlocks()) {
                    Block block = event.getBlock();
                    Location bLoc = block.getLocation();

                    for (int i = 0; i < 4; i++) {

                        Block replace = block.getWorld().getBlockAt(bLoc.getBlockX(), bLoc.getBlockY() - i, bLoc.getBlockZ());
                        final Material replaceType = replace.getType();

                        if (replaceType.equals(Material.CACTUS) || replaceType.equals(Material.SAND)) {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                replace.breakNaturally();
                                replace.setType(Material.AIR);
                            }, 1);
                        }
                    }
                }


            }

            return;
        }

        if (sourceBlockType.equals(Material.WATER) || sourceBlockType.equals(Material.LAVA) || sourceBlockType.toString().contains("TRAPDOOR") || sourceBlockType.equals(Material.TRIPWIRE)) {
            if (FarmsSettingsConfig.getInstance().isPreventStringDupe()) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                return;
            }
        }

        if (material.equals(Material.POINTED_DRIPSTONE) && FarmsSettingsConfig.getInstance().isPreventDripstoneFarms()) {
            Block block = event.getBlock();
            PointedDripstone dripstone = (PointedDripstone) block.getBlockData();

            if (dripstone.getVerticalDirection().equals(BlockFace.UP)) {
                if (!block.getRelative(BlockFace.DOWN).getType().equals(Material.STONE)) {
                    block.setType(Material.AIR);
                }
            }
        }
    }
}
