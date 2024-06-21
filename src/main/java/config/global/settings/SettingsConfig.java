package config.global.settings;

import config.AntiFarmConfigurations;
import core.AntiFarmPlugin;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationReplacement;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SettingsConfig extends ConfigurationPart {

    @ConfigurationPath(value = "prefix", comments = "Prefix to be used in messages to players.")
    @ConfigurationReplacement
    private String prefix = "&a[AntiFarm]&r ";
    @ConfigurationPath(value = "disabled-worlds", comments = "List of worlds you want to disable anti farm. (Case sensitive list.)")
    private List<String> disabledWorldList = new ArrayList<>(List.of(
            "SomeWorld",
            "AnotherWorld"
    ));
    @ConfigurationPath(value = "bstats", comments = "Send plugin stats to bstats.")
    private boolean bstats = true;
    @ConfigurationPath(value = "update-check", comments = "Check for plugin updates.")
    private boolean updateCheck = true;
    @ConfigurationPath(value = "break-pistons", comments = "Break detected pistons for auto farm. ('true' option is recommended for performance.)")
    private boolean breakPistons = true;
    @ConfigurationPath(value = "break-blocks", comments = "Break detected blocks for auto farm. ('true' option is recommended for performance.)")
    private boolean breakBlocks = true;
    private Set<World> disabledWorlds = new HashSet<>();

    public static SettingsConfig getInstance() {
        return AntiFarmConfigurations.GLOBAL.getSettings();
    }

    @Override
    public void loaded() {
        for (String s : this.disabledWorldList) {
            World world = Bukkit.getWorld(s);
            if (world == null) {
                AntiFarmPlugin.getInstance().getLogger().warning(String.format("%s is not a valid existing world", s));
                continue;
            };
            this.disabledWorlds.add(world);
        }
    }

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

    public Set<World> getDisabledWorlds() {
        return disabledWorlds;
    }
}
