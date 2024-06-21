package config.global.villager.zombie;

import config.global.villager.VillagerSettingsConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class PreventZombieVillagersCureConfig extends ConfigurationPart {

    public static PreventZombieVillagersCureConfig getInstance() {
        return VillagerSettingsConfig.getInstance().getPreventZombieVillagersCure();
    }

    @ConfigurationPath(value = "enable", comments = "Enable/Disable option.")
    private boolean enable = true;
    @ConfigurationPath(value = "cure-percent", comments = {
            "Chance of being cured. If this possibility is not realized, will die.",
            "If set to 0, it will not be cured and die."
    })
    private int curePercent = 30;

    public boolean isEnable() {
        return enable;
    }

    public int getCurePercent() {
        return curePercent;
    }
}




