package config;

import config.global.GlobalConfig;
import config.spawners.SpawnersConfig;

public interface AntiFarmConfigurations {

    GlobalConfig GLOBAL = new GlobalConfig();
    SpawnersConfig SPAWNERS = new SpawnersConfig();

    static void reload() {
        GLOBAL.reload();
        SPAWNERS.reload();
    }
}
