package antifarm;

import config.global.fishing.AntiFishingConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class AntiFishFarm implements Listener {

	private HashMap<String, Integer> fishCount = new HashMap<String, Integer>();
	private HashMap<String, LocalDateTime> fishTime = new HashMap<String, LocalDateTime>();
	private LocalDateTime clearHashMapsTimer;

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPlayerFish(PlayerFishEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getPlayer().getWorld())) return;

		if (event.isCancelled()) return;
		if (!AntiFishingConfig.getInstance().isEnable()) return;

		if (clearHashMapsTimer == null) {
			clearHashMapsTimer = LocalDateTime.now();
		} else if (Duration.between(clearHashMapsTimer, LocalDateTime.now()).toHours() >= 2) {
			fishCount.clear();
			fishTime.clear();
			clearHashMapsTimer = LocalDateTime.now();
		}

		Player player = event.getPlayer();
		Chunk chunk = player.getLocation().getChunk();
		String key = player.getName() + "." + String.valueOf(chunk.getX()) + "." + String.valueOf(chunk.getZ());

		if (fishTime.get(key) != null) {
			if (Duration.between(fishTime.get(key), LocalDateTime.now()).toSeconds() >= AntiFishingConfig.getInstance().getChunkCooldown()) {
				fishCount.remove(key);
				fishTime.remove(key);
			}
		}

		if (fishCount.get(key) == null) {
			fishCount.put(key, 0);
		}

		int value = fishCount.get(key);

		if (event.getState().equals(State.CAUGHT_FISH)) {
			if (fishTime.get(key) == null) {
				fishTime.put(key, LocalDateTime.now());
			}
			fishCount.replace(key, value, value + 1);
		}

		if (value >= AntiFishingConfig.getInstance().getCaughtFishPerChunk()) {

			event.setCancelled(true);
			fishCount.replace(key, fishCount.get(key), value + 1);

			if (AntiFishingConfig.getInstance().isWarn()) {
				player.sendMessage(SettingsConfig.getInstance().getPrefix() + AntiFishingConfig.getInstance().getWarnMsg());
			}

			if (AntiFishingConfig.getInstance().isKick()) {
				if (value >= (AntiFishingConfig.getInstance().getCaughtFishPerChunk() + 10)) {
					player.kickPlayer(SettingsConfig.getInstance().getPrefix() + AntiFishingConfig.getInstance().getKickMsg());
					fishCount.replace(key, fishCount.get(key), AntiFishingConfig.getInstance().getCaughtFishPerChunk());
				}
			}

		}

	}

}