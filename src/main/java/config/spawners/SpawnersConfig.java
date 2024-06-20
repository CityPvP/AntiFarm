package config.spawners;

import config.AbstractConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationList;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationKeys;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

import java.util.List;

public class SpawnersConfig extends AbstractConfig {

    @ConfigurationPath(value = "spawners", comments = "Spawner's type (zombie, skeleton, etc).")
    @ConfigurationKeys(defaultKeys = {"exampleSpawner", "zombie"})
    private ConfigurationList<SpawnerEntryConfig> spawners = new ConfigurationList<>(List.of(new SpawnerEntryConfig("exampleSpawner"), new SpawnerEntryConfig("zombie"))) {
        @Override
        public ConfigurationPart create(String key) {
            return new SpawnerEntryConfig(key);
        }
    };

    public ConfigurationList<SpawnerEntryConfig> getSpawners() {
        return spawners;
    }

    @Override
    public String name() {
        return "spawners";
    }
}
