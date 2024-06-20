package config.global.mobfarms;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

import java.util.ArrayList;
import java.util.List;

public class PreventMobFarmsConfig extends ConfigurationPart {

    @ConfigurationPath(value = "enable", comments = "Enable/Disable option.")
    private boolean enable = true;
    @ConfigurationPath(value = "allow-custom-death-drops", comments = "Allows drops from the creatures that die from special causes. (For custom entity killer plugins.)")
    private boolean allowCustomDeathDrops = false;
    @ConfigurationPath(value = "block-drop-xp", comments = "Block experience drop from the creature the player didn't kill.")
    private boolean blockDropXp = true;
    @ConfigurationPath(value = "block-drop-item", comments = "Block item drop from the creature the player didn't kill.")
    private boolean blockDropItem = true;
    @ConfigurationPath(value = "required-damage-percent-for-loot", comments = "Percentage of damage the player must inflict for the mob to loot.")
    private int requiredDamagePercentForLoot = 70;
    @ConfigurationPath(value = "blacklist", comments = {
            "All afk mob farms are blocked by default.",
            "If you only want to block only one creature's farm. Set 'blacklist' to 'true' and add the name of the creature you want to block to the 'moblist'.",
            "If you only want to allow only one creature's farm. Set 'blacklist' to 'false' and add the name of the creature you want to block to the 'moblist'.",
            "If you want all afk creature farms to be blocked don't touch the default settings."
    })
    private boolean blacklist = false;
    @ConfigurationPath(value = "moblist", comments = {
            "blacklist: true -> 'moblist' is blacklist.",
            "blacklist: false -> 'moblist' is whitelist.",
            "When adding mobs to the list, all letters should be capitalized. (Case sensitive list.)"
    })
    private List<String> moblist = new ArrayList<>(List.of(
            "BAT"
    ));

    public boolean isEnable() {
        return enable;
    }

    public boolean isAllowCustomDeathDrops() {
        return allowCustomDeathDrops;
    }

    public boolean isBlockDropXp() {
        return blockDropXp;
    }

    public boolean isBlockDropItem() {
        return blockDropItem;
    }

    public int getRequiredDamagePercentForLoot() {
        return requiredDamagePercentForLoot;
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public List<String> getMoblist() {
        return moblist;
    }
}