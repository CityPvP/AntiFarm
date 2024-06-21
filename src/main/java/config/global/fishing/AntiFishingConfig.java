package config.global.fishing;

import config.AntiFarmConfigurations;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationReplacement;

public class AntiFishingConfig extends ConfigurationPart {

    @ConfigurationPath(value = "warn-msg", comments = "Warning message.")
    @ConfigurationReplacement
    private String warnMsg = "&cYou have reached the fishing limit in the same area, go to different areas to continue fishing!";

    @ConfigurationPath(value = "enable", comments = "Enable/Disable option.")
    private boolean enable = true;
    @ConfigurationPath(value = "warn", comments = "Send warning message when player is blocked.")
    private boolean warn = true;
    @ConfigurationPath(value = "kick-msg", comments = "Kicking message.")
    @ConfigurationReplacement
    private String kickMsg = "&cYou were kicked from the server due to afk fish farm!";
    @ConfigurationPath(value = "kick", comments = "Get kicked off the server if the player keeps fishing.")
    private boolean kick = false;

    public static AntiFishingConfig getInstance() {
        return AntiFarmConfigurations.GLOBAL.getAntiFishing();
    }
    @ConfigurationPath(value = "caught-fish-per-chunk", comments = "How many times can the player fish per chunk? (Fails do not count.)")
    private int caughtFishPerChunk = 10;
    @ConfigurationPath(value = "chunk-cooldown", comments = "Time to wait to fish again in the same chunk. (Default: 10 min = 600 seconds.)")
    private int chunkCooldown = 600;

    public boolean isEnable() {
        return enable;
    }

    public boolean isWarn() {
        return warn;
    }

    public String getWarnMsg() {
        return warnMsg;
    }

    public boolean isKick() {
        return kick;
    }

    public String getKickMsg() {
        return kickMsg;
    }

    public int getCaughtFishPerChunk() {
        return caughtFishPerChunk;
    }

    public int getChunkCooldown() {
        return chunkCooldown;
    }
}
