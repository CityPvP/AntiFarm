package antifarm;

import config.global.mobspawner.MobSpawnerSettingsConfig;
import config.global.settings.SettingsConfig;
import config.spawners.SpawnerEntryConfig;
import config.spawners.SpawnersConfig;
import core.AntiFarmPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;


public class AntiMobSpawner implements Listener {

	private final AntiFarmPlugin plugin;
	Random random = new Random();

	public AntiMobSpawner(AntiFarmPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onSpawnerSpawn(SpawnerSpawnEvent event) {

		if (event.isCancelled() || event.getSpawner() == null) return;

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getSpawner().getWorld())) return;

		if (MobSpawnerSettingsConfig.getInstance().isPreventSpawn()) {
			event.setCancelled(true);
			return;
		}

		if (!MobSpawnerSettingsConfig.getInstance().isSpawnerData()) return;

		CreatureSpawner spawner = event.getSpawner();
		String spawnerType = spawner.getSpawnedType().toString();

		final SpawnerEntryConfig config = SpawnersConfig.getSpawner(spawnerType);

		if (config == null) return;

		String world = event.getSpawner().getWorld().getName();

		if (world.equalsIgnoreCase("world")) world = "overworld";
		if (world.equalsIgnoreCase("world_nether")) world = "the_nether";
		if (world.equalsIgnoreCase("world_the_end")) world = "the_end";

		Location location = event.getSpawner().getLocation();
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();

		boolean match = spawner.getMaxNearbyEntities() == config.getMaxNearbyEntities();


        if (spawner.getMaxSpawnDelay() != config.getMaxSpawnDelay()) match = false;
		if (spawner.getMinSpawnDelay() != config.getMinSpawnDelay()) match = false;
		if (spawner.getRequiredPlayerRange() != config.getRequiredPlayerRange()) match = false;
		if (spawner.getSpawnCount() != config.getSpawnCount()) match = false;
		if (spawner.getSpawnRange() != config.getSpawnRange()) match = false;

		if (match) return;

		int maxNearbyEntities = config.getMaxNearbyEntities();
		int maxSpawnDelay = config.getMaxSpawnDelay();
		int minSpawnDelay = config.getMinSpawnDelay();
		int requiredPlayerRange = config.getRequiredPlayerRange();
		int spawnCount = config.getSpawnCount();
		int spawnRange = config.getSpawnRange();
		int delay = random.nextInt(maxSpawnDelay - minSpawnDelay) + minSpawnDelay;

		String nbt = "SpawnData:{entity:{id:" + spawnerType + "}}" + ",MaxNearbyEntities:" + maxNearbyEntities + ",MaxSpawnDelay:" + maxSpawnDelay + ",MinSpawnDelay:" + minSpawnDelay + ",RequiredPlayerRange:" + requiredPlayerRange + ",SpawnCount:" + spawnCount + ",SpawnRange:" + spawnRange + ",Delay:" + delay;

		event.getSpawner().getBlock().setType(Material.AIR);
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "execute in minecraft:" + world + " run setblock " + x + " " + y + " " + z + " minecraft:spawner{" + nbt + "}");

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockBreak(BlockBreakEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled()) return;
		if (!event.getBlock().getType().equals(Material.SPAWNER)) return;
		if (!MobSpawnerSettingsConfig.getInstance().isPreventBreak()) return;

		if (event.getPlayer().hasPermission("antifarm.admin") || event.getPlayer().isOp()) {
			if (!event.getPlayer().isSneaking()) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(SettingsConfig.getInstance().getPrefix() + MobSpawnerSettingsConfig.getInstance().getAdminWarnMessage());
			}
		} else {
			event.setCancelled(true);
			event.getPlayer().sendMessage(SettingsConfig.getInstance().getPrefix() + MobSpawnerSettingsConfig.getInstance().getPlayerWarnMessage());
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onRightClick(PlayerInteractEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getPlayer().getWorld())) return;

		if (event.getItem() == null || event.getClickedBlock() == null)
			return;
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if (!event.getClickedBlock().getType().equals(Material.SPAWNER)) return;
		if (!event.getItem().getType().toString().contains("SPAWN_EGG")) return;
		if (!MobSpawnerSettingsConfig.getInstance().isPreventTransformation()) return;

		event.setCancelled(true);
		event.getPlayer().sendMessage(SettingsConfig.getInstance().getPrefix() + MobSpawnerSettingsConfig.getInstance().getTransformationWarnMessage());

	}

}