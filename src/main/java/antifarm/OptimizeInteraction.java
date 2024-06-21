package antifarm;

import core.AntiFarmPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Switch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class OptimizeInteraction implements Listener {

    private static final long COOLDOWN_TIME = 150; // Represent 6 clicks per seconds max
    private static final EnumSet<Material> ACTIVATION_MATERIALS = EnumSet.of(
            Material.LEVER,
            Material.DAYLIGHT_DETECTOR,
            Material.COMPARATOR,
            Material.REPEATER
    );

    static {
        for (Material value : Material.values()) {
            if (value.data == Switch.class) ACTIVATION_MATERIALS.add(value);
        }
    }

    private final Map<Block, Long> cooldownMap = new HashMap<>();

    public OptimizeInteraction(AntiFarmPlugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, this::clearCache, 20L * 60 * 60L, 20L * 60L * 60L);
    }

    private void clearCache() {
        Set<Block> toRemove = new HashSet<>();
        long time = System.currentTimeMillis();
        for (Map.Entry<Block, Long> entry : cooldownMap.entrySet()) {
            final Block block = entry.getKey();
            final long timeStamp = entry.getValue();
            if (timeStamp - time >= COOLDOWN_TIME) {
                toRemove.add(block);
            }
        }

        for (Block block : toRemove) {
            this.cooldownMap.remove(block);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick() || event.getClickedBlock() == null) return;
        if (!ACTIVATION_MATERIALS.contains(event.getClickedBlock().getType())) return;
        Long timeStamp = this.cooldownMap.get(event.getClickedBlock());
        if (timeStamp == null) {
            this.cooldownMap.put(event.getClickedBlock(), System.currentTimeMillis());
            return;
        }

        if (System.currentTimeMillis() - timeStamp >= COOLDOWN_TIME) {
            this.cooldownMap.remove(event.getClickedBlock());
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        this.cooldownMap.remove(event.getBlock());
    }
}
