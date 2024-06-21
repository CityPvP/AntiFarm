package config.global.creaturelimiter.creatures;

import config.global.creaturelimiter.CreatureProductLimiterConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationReplacement;

public class CowConfig extends ConfigurationPart {

    @ConfigurationPath(value = "milk-msg", comments = "")
    @ConfigurationReplacement
    private String milkMsg = "&aCow successfully milked.";

    @ConfigurationPath(value = "enable", comments = "")
    private boolean enable = true;
    @ConfigurationPath(value = "milk-cooldown-sec", comments = "")
    private int milkCooldownSec = 600;
    @ConfigurationPath(value = "feed-msg", comments = "")
    @ConfigurationReplacement
    private String feedMsg = "&aCow successfully fed.";
    @ConfigurationPath(value = "cooldown-msg", comments = "")
    @ConfigurationReplacement
    private String cooldownMsg = "&cThis cow can be milked again after &f%time% &cseconds.";
    @ConfigurationPath(value = "malnutrition-warning-msg", comments = "")
    @ConfigurationReplacement
    private String malnutritionWarningMsg = "&cMilking is unsuccessful because the cow is malnourished.";

    public static CowConfig getInstance() {
        return CreatureProductLimiterConfig.getInstance().getCow();
    }

    public boolean isEnable() {
        return enable;
    }

    public int getMilkCooldownSec() {
        return milkCooldownSec;
    }

    public String getMilkMsg() {
        return milkMsg;
    }

    public String getFeedMsg() {
        return feedMsg;
    }

    public String getCooldownMsg() {
        return cooldownMsg;
    }

    public String getMalnutritionWarningMsg() {
        return malnutritionWarningMsg;
    }
}