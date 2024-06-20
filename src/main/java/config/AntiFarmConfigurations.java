package config;

import config.global.GlobalConfig;
import config.spawners.SpawnersConfig;

public interface AntiFarmConfigurations {

    static void reload() {
        GLOBAL.reload();
        SPAWNERS.reload();
    }

    GlobalConfig GLOBAL = new GlobalConfig();
    SpawnersConfig SPAWNERS = new SpawnersConfig();
}
