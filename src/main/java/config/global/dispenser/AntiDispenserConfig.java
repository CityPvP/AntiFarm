package config.global.dispenser;

import config.AntiFarmConfigurations;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;
import org.bukkit.Material;
import util.TypeUtil;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class AntiDispenserConfig extends ConfigurationPart {

    private final Set<Material> blockedItems = EnumSet.noneOf(Material.class);

    @ConfigurationPath(value = "enable", comments = "Enable/Disable option.")
    private boolean enable = true;
    @ConfigurationPath(value = "prevent-dispenser-shearing", comments = "Prevent shearing sheep with dispenser.")
    private boolean preventDispenserShearing = true;
    @ConfigurationPath(value = "blocked-item-list", comments = "Blocked item list. (Case sensitive list.)")
    private List<String> blockedItemList = new ArrayList<>(List.of(
            "BONE_MEAL",
            "GLASS_BOTTLE",
            "WATER_BUCKET",
            "LAVA_BUCKET"
    ));

    public static AntiDispenserConfig getInstance() {
        return AntiFarmConfigurations.GLOBAL.getAntiDispenser();
    }

    @Override
    public void loaded() {
        for (String s : this.blockedItemList) {
            TypeUtil.parseMaterial(s, this.blockedItems::add);
        }
    }

    public boolean isEnable() {
        return enable;
    }

    public boolean isPreventDispenserShearing() {
        return preventDispenserShearing;
    }

    public Set<Material> getBlockedItems() {
        return blockedItems;
    }
}