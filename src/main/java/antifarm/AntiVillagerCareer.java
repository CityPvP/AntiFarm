package antifarm;

import config.global.settings.SettingsConfig;
import config.global.villager.VillagerSettingsConfig;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent.ChangeReason;

public class AntiVillagerCareer implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onVillagerCareerChange(VillagerCareerChangeEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null || event.getReason() == null) return;
		if (!event.getReason().equals(ChangeReason.EMPLOYED)) return;
		if (!VillagerSettingsConfig.getInstance().isPreventVillagersProfessionChange()) return;

		Villager villager = event.getEntity();
		villager.setVillagerExperience(villager.getVillagerExperience() + 1);

	}

}
