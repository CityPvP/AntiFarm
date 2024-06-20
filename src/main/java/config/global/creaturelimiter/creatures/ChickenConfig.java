package config.global.creaturelimiter.creatures;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class ChickenConfig extends ConfigurationPart {

    @ConfigurationPath(value = "enable", comments = "")
    private boolean enable = true;
    @ConfigurationPath(value = "egg-min", comments = "")
    private int eggMin = 5;
    @ConfigurationPath(value = "egg-max", comments = "")
    private int eggMax = 10;
    @ConfigurationPath(value = "feed-msg", comments = "")
    private String feedMsg = "&aChicken successfully fed.";

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