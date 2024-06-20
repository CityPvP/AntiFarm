package config.global.villager.infection;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class PreventVillagersInfectionConfig extends ConfigurationPart {

    @ConfigurationPath(value = "enable", comments = "Enable/Disable option.")
    private boolean enable = true;
    @ConfigurationPath(value = "infection-percent", comments = {
            "Chance of being infected. If this possibility is not realized, will die.",
            "If set to 0, it will not be infected and die.",
            "Even if the chance of infection is set to 100, there is always a risk of death."
    })
    private int infectionPercent = 30;

    public boolean isEnable() {
        return enable;
    }

    public int getInfectionPercent() {
        return infectionPercent;
    }
}