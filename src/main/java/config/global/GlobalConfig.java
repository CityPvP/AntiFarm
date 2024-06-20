package config.global;

import config.AbstractConfig;
import config.global.creaturelimiter.CreatureProductLimiterConfig;
import config.global.dispenser.AntiDispenserConfig;
import config.global.farm.FarmsSettingsConfig;
import config.global.fishing.AntiFishingConfig;
import config.global.mobfarms.PreventMobFarmsConfig;
import config.global.mobspawner.MobSpawnerSettingsConfig;
import config.global.raidfarms.PreventRaidFarmsConfig;
import config.global.settings.SettingsConfig;
import config.global.villager.VillagerSettingsConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

import java.util.ArrayList;
import java.util.List;

public class GlobalConfig extends AbstractConfig {

    @ConfigurationPath(value = "settings", comments = "AntiFarm plugin configuration file. General settings.")
    private SettingsConfig settings = new SettingsConfig();

    @ConfigurationPath(value = "anti-fishing", comments = "Anti afk fish farm settings.")
    private AntiFishingConfig antiFishing = new AntiFishingConfig();

    @ConfigurationPath(value = "mob-spawner-settings", comments = "Mob spawner settings.")
    private MobSpawnerSettingsConfig mobSpawnerSettings = new MobSpawnerSettingsConfig();

    @ConfigurationPath(value = "villager-settings", comments = "Villager settings.")
    private VillagerSettingsConfig villagerSettings = new VillagerSettingsConfig();

    @ConfigurationPath(value = "farms-settings", comments = "Automatic farms settings.")
    private FarmsSettingsConfig farmsSettings = new FarmsSettingsConfig();

    @ConfigurationPath(value = "anti-dispenser", comments = "If dispenser is looking at a farm block, it will be prevented from throwing items in the \"blocked-item-list\".")
    private AntiDispenserConfig antiDispenser = new AntiDispenserConfig();

    @ConfigurationPath(value = "prevent-mob-farms", comments = "Automatic mob farms settings.")
    private PreventMobFarmsConfig preventMobFarms = new PreventMobFarmsConfig();

    @ConfigurationPath(value = "prevent-raid-farms", comments = "Raid farms settings.")
    private PreventRaidFarmsConfig preventRaidFarms = new PreventRaidFarmsConfig();

    @ConfigurationPath(value = "creature-product-limiter", comments = "Creature item output limit. Production range in which mobs must be fed.")
    private CreatureProductLimiterConfig creatureProductLimiter = new CreatureProductLimiterConfig();

    @ConfigurationPath(value = "farmland-blocks", comments = "List of blocks on which any farm block can be grown.")
    private List<String> farmlandBlocks = new ArrayList<>(List.of(
            "GRASS_BLOCK",
            "DIRT",
            "COARSE_DIRT",
            "ROOTED_DIRT",
            "SAND",
            "RED_SAND",
            "FARMLAND",
            "SOUL_SAND",
            "END_STONE",
            "STONE"
    ));

    @ConfigurationPath(value = "farm-blocks", comments = "List of all things that can be farmed.")
    private List<String> farmBlocks = new ArrayList<>(List.of(
            "CACTUS",
            "SUGAR_CANE",
            "SUGAR_CANE_BLOCK",
            "REEDS",
            "BAMBOO",
            "MELON",
            "MELON_STEM",
            "MELON_BLOCK",
            "ATTACHED_MELON_STEM",
            "PUMPKIN",
            "PUMPKIN_STEM",
            "ATTACHED_PUMPKIN_STEM",
            "WHEAT",
            "CROPS",
            "CARROT",
            "CARROTS",
            "POTATO",
            "POTATOES",
            "BEETROOT",
            "BEETROOTS",
            "BEETROOT_BLOCK",
            "SWEET_BERRY_BUSH",
            "COCOA",
            "CAVE_VINES",
            "CAVE_VINES_PLANT",
            "NETHER_WART",
            "CHORUS_PLANT",
            "CHORUS_FLOWER",
            "RED_MUSHROOM",
            "BROWN_MUSHROOM",
            "CRIMSON_FUNGUS",
            "WARPED_FUNGUS",
            "MOSS_BLOCK",
            "KELP",
            "BEE_NEST",
            "BEEHIVE",
            "SUNFLOWER",
            "LILAC",
            "ROSE_BUSH",
            "PEONY",
            "WEEPING_VINES",
            "WEEPING_VINES_PLANT",
            "TWISTING_VINES",
            "TWISTING_VINES_PLANT",
            "BIG_DRIPLEAF",
            "AMETHYST_CLUSTER",
            "DRIPSTONE_BLOCK",
            "POINTED_DRIPSTONE",
            "MANGROVE_LEAVES"
    ));

    public SettingsConfig getSettings() {
        return settings;
    }

    public AntiFishingConfig getAntiFishing() {
        return antiFishing;
    }

    public MobSpawnerSettingsConfig getMobSpawnerSettings() {
        return mobSpawnerSettings;
    }

    public VillagerSettingsConfig getVillagerSettings() {
        return villagerSettings;
    }

    public FarmsSettingsConfig getFarmsSettings() {
        return farmsSettings;
    }

    public AntiDispenserConfig getAntiDispenser() {
        return antiDispenser;
    }

    public PreventMobFarmsConfig getPreventMobFarms() {
        return preventMobFarms;
    }

    public PreventRaidFarmsConfig getPreventRaidFarms() {
        return preventRaidFarms;
    }

    public CreatureProductLimiterConfig getCreatureProductLimiter() {
        return creatureProductLimiter;
    }

    public List<String> getFarmlandBlocks() {
        return farmlandBlocks;
    }

    public List<String> getFarmBlocks() {
        return farmBlocks;
    }

    @Override
    public String name() {
        return "global";
    }
}