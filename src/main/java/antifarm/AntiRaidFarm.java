package antifarm;

import config.global.raidfarms.PreventRaidFarmsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class AntiRaidFarm implements Listener {

	private HashMap<UUID, LocalDateTime> raidTimer = new HashMap<UUID, LocalDateTime>();

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onRaidTrigger(RaidTriggerEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getWorld())) return;

		if (event.isCancelled()) return;
		if (event.getPlayer() == null) return;
		if (!PreventRaidFarmsConfig.getInstance().isEnable()) return;

		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		if (raidTimer.get(uuid) == null) {
			raidTimer.put(uuid, LocalDateTime.now());
		} else if (Duration.between(raidTimer.get(uuid), LocalDateTime.now()).toSeconds() >= PreventRaidFarmsConfig.getInstance().getCooldown()) {
			raidTimer.remove(uuid);
		} else {
			event.setCancelled(true);
		}

	}

}
