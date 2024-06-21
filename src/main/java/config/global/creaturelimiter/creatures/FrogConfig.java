package config.global.creaturelimiter.creatures;

import config.global.creaturelimiter.CreatureProductLimiterConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class FrogConfig extends ConfigurationPart {

    public static FrogConfig getInstance() {
        return CreatureProductLimiterConfig.getInstance().getFrog();
    }

    @ConfigurationPath(value = "enable", comments = "")
    private boolean enable = true;
    @ConfigurationPath(value = "froglight-min", comments = "")
    private int froglightMin = 5;
    @ConfigurationPath(value = "froglight-max", comments = "")
    private int froglightMax = 10;
    @ConfigurationPath(value = "feed-msg", comments = "")
    private String feedMsg = "&aFrog successfully fed.";

    public boolean isEnable() {
        return enable;
    }

    public int getFroglightMin() {
        return froglightMin;
    }

    public int getFroglightMax() {
        return froglightMax;
    }

    public String getFeedMsg() {
        return feedMsg;
    }
}