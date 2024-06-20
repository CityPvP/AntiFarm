package config.global.creaturelimiter;

import config.global.creaturelimiter.creatures.ChickenConfig;
import config.global.creaturelimiter.creatures.CowConfig;
import config.global.creaturelimiter.creatures.FrogConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class CreatureProductLimiterConfig extends ConfigurationPart {

    @ConfigurationPath(value = "frog", comments = "")
    private FrogConfig frog = new FrogConfig();
    
    @ConfigurationPath(value = "chicken", comments = "")
    private ChickenConfig chicken = new ChickenConfig();
    
    @ConfigurationPath(value = "cow", comments = "")
    private CowConfig cow = new CowConfig();

    public FrogConfig getFrog() {
        return frog;
    }

    public ChickenConfig getChicken() {
        return chicken;
    }

    public CowConfig getCow() {
        return cow;
    }
}