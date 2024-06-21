package config.spawners;

import config.AbstractConfig;
import config.AntiFarmConfigurations;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationList;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationKeys;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpawnersConfig extends AbstractConfig {

    private final Map<String, SpawnerEntryConfig> spawners = new HashMap<>();
    @ConfigurationPath(value = "spawners", comments = "Spawner's type (zombie, skeleton, etc).")
    @ConfigurationKeys(defaultKeys = {"exampleSpawner", "zombie"})
    private ConfigurationList<SpawnerEntryConfig> spawnerList = new ConfigurationList<>(List.of(new SpawnerEntryConfig("exampleSpawner"), new SpawnerEntryConfig("zombie"))) {
        @Override
        public ConfigurationPart create(String key) {
            return new SpawnerEntryConfig(key);
        }
    };

    public static SpawnerEntryConfig getSpawner(String type) {
        return AntiFarmConfigurations.SPAWNERS.spawners.get(type);
    }

    @Override
    public void loaded() {
        this.spawners.clear();
        for (SpawnerEntryConfig spawnerEntryConfig : this.spawnerList) {
            this.spawners.put(spawnerEntryConfig.getKey().toUpperCase().replace("-", "_"), spawnerEntryConfig);
        }
    }

    @Override
    public String name() {
        return "spawners";
    }
}
