package antifarm;

import config.global.farm.FarmsSettingsConfig;
import config.global.settings.SettingsConfig;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;

public class AntiBerryFarm implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityChangeBlock(EntityChangeBlockEvent event) {

		if (SettingsConfig.getInstance().getDisabledWorlds().contains(event.getBlock().getWorld())) return;

		if (event.isCancelled() || event.getBlock() == null || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.FOX)) return;
		if (!event.getBlock().getType().equals(Material.SWEET_BERRY_BUSH)) return;
		if (!FarmsSettingsConfig.getInstance().isPreventBerryFarms()) return;

		event.setCancelled(true);

		event.getEntity().getWorld().playSound(event.getEntity(), Sound.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 1.0f, 1.0f);
		Ageable sweetBerryBush = (Ageable) event.getBlock().getBlockData();
		sweetBerryBush.setAge(1);
		event.getBlock().setBlockData(sweetBerryBush);

		LivingEntity fox = (LivingEntity) event.getEntity();

		if (!fox.getEquipment().getItemInMainHand().getType().equals(Material.AIR)) return;

		fox.getEquipment().setItemInMainHand(new ItemStack(Material.SWEET_BERRIES, 1));

	}

}
