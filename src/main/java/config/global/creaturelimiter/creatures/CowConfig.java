package config.global.creaturelimiter.creatures;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class CowConfig extends ConfigurationPart {

    @ConfigurationPath(value = "enable", comments = "")
    private boolean enable = true;
    @ConfigurationPath(value = "milk-cooldown-sec", comments = "")
    private int milkCooldownSec = 600;
    @ConfigurationPath(value = "milk-msg", comments = "")
    private String milkMsg = "&aCow successfully milked.";
    @ConfigurationPath(value = "feed-msg", comments = "")
    private String feedMsg = "&aCow successfully fed.";
    @ConfigurationPath(value = "cooldown-msg", comments = "")
    private String cooldownMsg = "&cThis cow can be milked again after &f%time% &cseconds.";
    @ConfigurationPath(value = "malnutrition-warning-msg", comments = "")
    private String malnutritionWarningMsg = "&cMilking is unsuccessful because the cow is malnourished.";
}