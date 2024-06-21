package antifarm;

import config.global.settings.SettingsConfig;
import config.global.villager.zombie.PreventZombieVillagersCureConfig;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Random;

public class AntiVillagerTransform implements Listener {

	Random random = new Random();

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getPlayer().getWorld())) return;

		if (event.isCancelled() || event.getPlayer() == null || event.getRightClicked() == null || event.getPlayer().getInventory().getItemInMainHand() == null || event.getPlayer().getInventory().getItemInOffHand() == null)
			return;
		if (!event.getRightClicked().getType().equals(EntityType.ZOMBIE_VILLAGER)) return;
		if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_APPLE) && !event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.GOLDEN_APPLE) && !event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ENCHANTED_GOLDEN_APPLE) && !event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_GOLDEN_APPLE))
			return;
		if (!PreventZombieVillagersCureConfig.getInstance().isEnable()) return;
		if (PreventZombieVillagersCureConfig.getInstance().getCurePercent() != 0) return;

		event.setCancelled(true);

	}

	@EventHandler
	private void onEntityTransform(EntityTransformEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (!event.getEntityType().equals(EntityType.VILLAGER) && !event.getEntityType().equals(EntityType.ZOMBIE_VILLAGER))
			return;
		if (event.getEntity().getType().equals(EntityType.VILLAGER) && event.getTransformReason().equals(TransformReason.INFECTION) && random.nextInt(100) < PreventZombieVillagersCureConfig.getInstance().getCurePercent())
			return;
		if (event.getEntity().getType().equals(EntityType.ZOMBIE_VILLAGER) && event.getTransformReason().equals(TransformReason.CURED) && random.nextInt(100) < PreventZombieVillagersCureConfig.getInstance().getCurePercent())
			return;

		LivingEntity transformedEntity = (LivingEntity) event.getTransformedEntity();
		transformedEntity.setHealth(0);

	}

}