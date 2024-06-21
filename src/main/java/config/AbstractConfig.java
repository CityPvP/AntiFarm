package config;

import core.AntiFarmPlugin;
import fr.bramsou.yaml.api.YamlApiService;
import fr.bramsou.yaml.api.configuration.ConfigurationType;
import fr.bramsou.yaml.api.configuration.YamlConfiguration;

import java.io.File;

public abstract class AbstractConfig {

    private final YamlConfiguration configuration;

    public AbstractConfig() {
        this.configuration = YamlApiService.getApi().getConfigurationManager().loadConfiguration(this, new File(AntiFarmPlugin.getInstance().getDataFolder(), name() + ".yml"), ConfigurationType.DYNAMIC);
    }

    public final void reload() {
        this.configuration.load();
        this.configuration.save();
        this.loaded();
    }

    public abstract void loaded();

    public abstract String name();
}
