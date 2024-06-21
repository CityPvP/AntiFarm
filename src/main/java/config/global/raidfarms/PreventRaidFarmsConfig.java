package config.global.raidfarms;

import config.AntiFarmConfigurations;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class PreventRaidFarmsConfig extends ConfigurationPart {

    public static PreventRaidFarmsConfig getInstance() {
        return AntiFarmConfigurations.GLOBAL.getPreventRaidFarms();
    }

    @ConfigurationPath(value = "enable", comments = "Enable/Disable option.")
    private boolean enable = true;
    @ConfigurationPath(value = "cooldown", comments = "Cooldown between raids. (Seconds.)")
    private int cooldown = 600;

    public boolean isEnable() {
        return enable;
    }

    public int getCooldown() {
        return cooldown;
    }
}