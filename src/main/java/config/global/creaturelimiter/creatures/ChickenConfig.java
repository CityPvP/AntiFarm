package config.global.creaturelimiter.creatures;

import config.global.creaturelimiter.CreatureProductLimiterConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationReplacement;

public class ChickenConfig extends ConfigurationPart {

    @ConfigurationPath(value = "feed-msg", comments = "")
    @ConfigurationReplacement
    private String feedMsg = "&aChicken successfully fed.";

    @ConfigurationPath(value = "enable", comments = "")
    private boolean enable = true;
    @ConfigurationPath(value = "egg-min", comments = "")
    private int eggMin = 5;
    @ConfigurationPath(value = "egg-max", comments = "")
    private int eggMax = 10;

    public static ChickenConfig getInstance() {
        return CreatureProductLimiterConfig.getInstance().getChicken();
    }

    public boolean isEnable() {
        return enable;
    }

    public int getEggMin() {
        return eggMin;
    }

    public int getEggMax() {
        return eggMax;
    }

    public String getFeedMsg() {
        return feedMsg;
    }
}