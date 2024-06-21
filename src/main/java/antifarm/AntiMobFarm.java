package antifarm;

import config.global.mobfarms.PreventMobFarmsConfig;
import config.global.settings.SettingsConfig;
import core.AntiFarmPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class AntiMobFarm implements Listener {

	private final AntiFarmPlugin plugin;

	public AntiMobFarm(AntiFarmPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityDeath(EntityDeathEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.getEntity().getLastDamageCause() == null || event.getEntity() instanceof Player || event.getEntity() instanceof ArmorStand || event.getEntity() instanceof Villager || event.getEntity() instanceof ChestedHorse)
			return;

		if (!PreventMobFarmsConfig.getInstance().isEnable()) return;
		if (event.getEntity().getLastDamageCause().getCause().equals(DamageCause.CUSTOM) && PreventMobFarmsConfig.getInstance().isAllowCustomDeathDrops())
			return;
		if (PreventMobFarmsConfig.getInstance().isBlacklist() && !PreventMobFarmsConfig.getInstance().getMobs().contains(event.getEntity().getType()))
			return;
		if (!PreventMobFarmsConfig.getInstance().isBlacklist() && PreventMobFarmsConfig.getInstance().getMobs().contains(event.getEntity().getType()))
			return;

		double damageTaken = event.getEntity().getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "damageTaken"), PersistentDataType.DOUBLE, 0.0);
		double maxHealth = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		double damagePercentage = PreventMobFarmsConfig.getInstance().getRequiredDamagePercentForLoot() / 100D;
		if ((maxHealth * damagePercentage) <= damageTaken) return;

		if (PreventMobFarmsConfig.getInstance().isBlockDropXp()) event.setDroppedExp(0);
		if (PreventMobFarmsConfig.getInstance().isBlockDropItem()) event.getDrops().clear();

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onDamage(EntityDamageEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getEntity().getWorld())) return;

		if (event.isCancelled() || event.getEntity() instanceof Player || event.getEntity() instanceof ArmorStand || event.getEntity() instanceof Villager || event.getEntity() instanceof ChestedHorse)
			return;

		if (!event.getCause().equals(DamageCause.ENTITY_ATTACK) && !event.getCause().equals(DamageCause.PROJECTILE) && !event.getCause().equals(DamageCause.FIRE_TICK))
			return;

		if (!PreventMobFarmsConfig.getInstance().isEnable()) return;
		if (PreventMobFarmsConfig.getInstance().isBlacklist() && !PreventMobFarmsConfig.getInstance().getMobs().contains(event.getEntity().getType()))
			return;
		if (!PreventMobFarmsConfig.getInstance().isBlacklist() && PreventMobFarmsConfig.getInstance().getMobs().contains(event.getEntity().getType()))
			return;

		Player player = null;
		Entity entity = event.getEntity();
		double eventDamage = event.getFinalDamage();

		if (event.getCause().equals(DamageCause.PROJECTILE)) {
			Projectile projectile = (Projectile) ((EntityDamageByEntityEvent) event).getDamager();
			if (!((Entity) projectile.getShooter() instanceof Player)) return;
			player = (Player) projectile.getShooter();
		} else if (event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
			if (!(((EntityDamageByEntityEvent) event).getDamager() instanceof Player)) return;
			player = (Player) ((EntityDamageByEntityEvent) event).getDamager();
		} else if (event.getCause().equals(DamageCause.FIRE_TICK)) {
			Long lastPlayerHit = entity.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "lastPlayerHit"), PersistentDataType.LONG, new Date().getTime() - 10000);
			LocalDateTime lphTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastPlayerHit), TimeZone.getDefault().toZoneId());
			if (Duration.between(lphTime, LocalDateTime.now()).toSeconds() > 5) return;
		}

		if (player != null) {
			if (player.getInventory().getItemInMainHand().getEnchantments().toString().contains("FIRE")) {
				event.getEntity().getPersistentDataContainer().set(new NamespacedKey(plugin, "lastPlayerHit"), PersistentDataType.LONG, new Date().getTime());
			}
		}

		double damageTaken = entity.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "damageTaken"), PersistentDataType.DOUBLE, 0.0);
		damageTaken = damageTaken + eventDamage;
		entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "damageTaken"), PersistentDataType.DOUBLE, damageTaken);

	}

}