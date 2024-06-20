package config.global.mobspawner;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class MobSpawnerSettingsConfig extends ConfigurationPart {

    @ConfigurationPath(value = "prevent-spawn", comments = "Prevent mob spawner from spawning creatures.")
    private boolean preventSpawn = false;
    @ConfigurationPath(value = "prevent-break", comments = "Prevent players from breaking mob spawner. (Admins can still break it by sneak and hitting.)")
    private boolean preventBreak = true;
    @ConfigurationPath(value = "prevent-transformation", comments = "Preventing mob spawners from being converted by clicking with mob eggs.")
    private boolean preventTransformation = true;
    @ConfigurationPath(value = "player-warn-message", comments = "Warning message to player.")
    private String playerWarnMessage = "&cYou can't break mob spawner, this action blocked on server!";
    @ConfigurationPath(value = "admin-warn-message", comments = "Warning message to admin.")
    private String adminWarnMessage = "&cMob spawner break is disabled on server. But you are an admin and if you want to break, you can. (Sneak + Attack)";
    @ConfigurationPath(value = "transformation-warn-message", comments = "Transformation blocking message to admin.")
    private String transformationWarnMessage = "&cYou can't convert mob spawner, this action is blocked on server!";
    @ConfigurationPath(value = "spawner-data", comments = "Spawner data customization. (Spawner settings are in spawners.yml)")
    private boolean spawnerData = false;

    public boolean isPreventSpawn() {
        return preventSpawn;
    }

    public boolean isPreventBreak() {
        return preventBreak;
    }

    public boolean isPreventTransformation() {
        return preventTransformation;
    }

    public String getPlayerWarnMessage() {
        return playerWarnMessage;
    }

    public String getAdminWarnMessage() {
        return adminWarnMessage;
    }

    public String getTransformationWarnMessage() {
        return transformationWarnMessage;
    }

    public boolean isSpawnerData() {
        return spawnerData;
    }
}
