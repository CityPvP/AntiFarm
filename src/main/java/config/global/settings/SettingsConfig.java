package config.global.settings;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

import java.util.ArrayList;
import java.util.List;

public class SettingsConfig extends ConfigurationPart {

    @ConfigurationPath(value = "prefix", comments = "Prefix to be used in messages to players.")
    private String prefix = "&a[AntiFarm]&r ";
    @ConfigurationPath(value = "bstats", comments = "Send plugin stats to bstats.")
    private boolean bstats = true;
    @ConfigurationPath(value = "update-check", comments = "Check for plugin updates.")
    private boolean updateCheck = true;
    @ConfigurationPath(value = "break-pistons", comments = "Break detected pistons for auto farm. ('true' option is recommended for performance.)")
    private boolean breakPistons = true;
    @ConfigurationPath(value = "break-blocks", comments = "Break detected blocks for auto farm. ('true' option is recommended for performance.)")
    private boolean breakBlocks = true;
    @ConfigurationPath(value = "disabled-worlds", comments = "List of worlds you want to disable anti farm. (Case sensitive list.)")
    private List<String> disabledWorlds = new ArrayList<>(List.of(
            "SomeWorld",
            "AnotherWorld"
    ));

    public String getPrefix() {
        return prefix;
    }

    public boolean isBstats() {
        return bstats;
    }

    public boolean isUpdateCheck() {
        return updateCheck;
    }

    public boolean isBreakPistons() {
        return breakPistons;
    }

    public boolean isBreakBlocks() {
        return breakBlocks;
    }

    public List<String> getDisabledWorlds() {
        return disabledWorlds;
    }
}
