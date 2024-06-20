package config.global.dispenser;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

import java.util.ArrayList;
import java.util.List;

public class AntiDispenserConfig extends ConfigurationPart {

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

    public boolean isEnable() {
        return enable;
    }

    public boolean isPreventDispenserShearing() {
        return preventDispenserShearing;
    }

    public List<String> getBlockedItemList() {
        return blockedItemList;
    }
}