package config.spawners;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class SpawnerEntryConfig extends ConfigurationPart {

    private final String key;

    public SpawnerEntryConfig(String key) {
        this.key = key;
    }

    @ConfigurationPath(value = "maxNearbyEntities", comments = "Set the maximum number of similar entities that are allowed to be within spawning range of this spawner.")
    private int maxNearbyEntities = 6;

    @ConfigurationPath(value = "maxSpawnDelay", comments = "Set the maximum spawn delay amount (in ticks) (20 ticks = 1 second).")
    private int maxSpawnDelay = 800;

    @ConfigurationPath(value = "minSpawnDelay", comments = "Set the minimum spawn delay amount (in ticks) (20 ticks = 1 second).")
    private int minSpawnDelay = 200;

    @ConfigurationPath(value = "requiredPlayerRange", comments = "Set the maximum distance (squared) a player can be in order for this spawner to be active.")
    private int requiredPlayerRange = 16;

    @ConfigurationPath(value = "spawnCount", comments = "Set how many mobs attempt to spawn.")
    private int spawnCount = 4;

    @ConfigurationPath(value = "spawnRange", comments = "Set the new spawn range.")
    private int spawnRange = 4;

    public int getMaxNearbyEntities() {
        return maxNearbyEntities;
    }

    public int getMaxSpawnDelay() {
        return maxSpawnDelay;
    }

    public int getMinSpawnDelay() {
        return minSpawnDelay;
    }

    public int getRequiredPlayerRange() {
        return requiredPlayerRange;
    }

    public int getSpawnCount() {
        return spawnCount;
    }

    public int getSpawnRange() {
        return spawnRange;
    }

    public String getKey() {
        return key;
    }
}
