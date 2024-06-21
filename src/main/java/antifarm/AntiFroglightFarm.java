package antifarm;

import config.global.creaturelimiter.creatures.FrogConfig;
import config.global.settings.SettingsConfig;
import core.AntiFarmPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.entity.Frog.Variant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.Random;

public class AntiFroglightFarm implements Listener {

	private final AntiFarmPlugin plugin;
	Random random = new Random();

	public AntiFroglightFarm(AntiFarmPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityDeath(EntityDeathEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.getEntity().getKiller() != null || !event.getEntity().getType().equals(EntityType.MAGMA_CUBE) || !FrogConfig.getInstance().isEnable())
			return;

		MagmaCube magmaCube = (MagmaCube) event.getEntity();

		if (magmaCube.getSize() != 1) return;

		event.getDrops().clear();

		Collection<Entity> entities = event.getEntity().getNearbyEntities(1, 1, 1);

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (magmaCube.getPose().equals(Pose.DYING)) {

				String killerUID = magmaCube.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "killerUID"), PersistentDataType.STRING, null);

				for (Entity entity : entities) {
					if (entity.getType().equals(EntityType.FROG)) {
						if (entity.getUniqueId().toString().equals(killerUID)) {

							Frog frog = (Frog) entity;
							Variant variant = frog.getVariant();
							Location location = frog.getLocation();

							int currentTongue = frog.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "currentTongue"), PersistentDataType.INTEGER, 0);
							int maxTongue = frog.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "maxTongue"), PersistentDataType.INTEGER, 0);

							if (maxTongue == 0) {
								maxTongue = random.nextInt(FrogConfig.getInstance().getFroglightMax() - FrogConfig.getInstance().getFroglightMin()) + FrogConfig.getInstance().getFroglightMin();
								frog.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxTongue"), PersistentDataType.INTEGER, maxTongue);
							}

							if (currentTongue < maxTongue) {

								frog.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentTongue"), PersistentDataType.INTEGER, currentTongue + 1);

								if (variant.equals(Variant.WARM)) {
									location.getWorld().dropItem(location, new ItemStack(Material.PEARLESCENT_FROGLIGHT, 1));
								} else if (variant.equals(Variant.TEMPERATE)) {
									location.getWorld().dropItem(location, new ItemStack(Material.OCHRE_FROGLIGHT, 1));
								} else if (variant.equals(Variant.COLD)) {
									location.getWorld().dropItem(location, new ItemStack(Material.VERDANT_FROGLIGHT, 1));
								}

							}
						}
					}
				}
            }
        }, 1);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onDamage(EntityDamageByEntityEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() == null || event.getDamager() == null) return;

		Entity attacker = event.getDamager();
		Entity victim = event.getEntity();

		if (!attacker.getType().equals(EntityType.FROG) || !victim.getType().equals(EntityType.MAGMA_CUBE) || !FrogConfig.getInstance().isEnable())
			return;

		Entity magmaCube = victim;
		Entity frog = attacker;

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (magmaCube.getPose().equals(Pose.DYING)) {
				magmaCube.getPersistentDataContainer().set(new NamespacedKey(plugin, "killerUID"), PersistentDataType.STRING, frog.getUniqueId().toString());
            }
        }, 1);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityEnterLoveMode(EntityEnterLoveModeEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getHumanEntity() == null || !FrogConfig.getInstance().isEnable() || !event.getEntity().getType().equals(EntityType.FROG))
			return;

		Entity frog = event.getEntity();
		int currentTongue = frog.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "currentTongue"), PersistentDataType.INTEGER, 0);
		int maxTongue = frog.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "maxTongue"), PersistentDataType.INTEGER, 0);

		if (currentTongue >= maxTongue) {
			maxTongue = random.nextInt(FrogConfig.getInstance().getFroglightMax() - FrogConfig.getInstance().getFroglightMin()) + FrogConfig.getInstance().getFroglightMin();
			frog.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentTongue"), PersistentDataType.INTEGER, 0);
			frog.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxTongue"), PersistentDataType.INTEGER, maxTongue);
			event.getHumanEntity().sendMessage(SettingsConfig.getInstance().getPrefix() + FrogConfig.getInstance().getFeedMsg());
		}

	}

}
