package config.global.farm;

import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class FarmsSettingsConfig extends ConfigurationPart {

    @ConfigurationPath(value = "prevent-cactus-farms", comments = "Prevents farming by placing a block next to the cactus.")
    private boolean preventCactusFarms = true;
    @ConfigurationPath(value = "prevent-piston-farms", comments = "Prevents 'farm-blocks' from breaking and moving with piston.")
    private boolean preventPistonFarms = true;
    @ConfigurationPath(value = "prevent-water-harvesting-farms", comments = "Prevents harvesting farm blocks using water.")
    private boolean preventWaterHarvestingFarms = true;
    @ConfigurationPath(value = "prevent-enderman-harvesting-farms", comments = "Prevents farm blocks from being taken by endermans.")
    private boolean preventEndermanHarvestingFarms = true;
    @ConfigurationPath(value = "prevent-waterless-farms", comments = "Prevents planting on a block of land that has not reached the full water level.")
    private boolean preventWaterlessFarms = true;
    @ConfigurationPath(value = "prevent-lightless-farms", comments = "Prevents planting on farmland with light level below 8.")
    private boolean preventLightlessFarms = true;
    @ConfigurationPath(value = "prevent-snowball-farms", comments = "Prevents the snowman from leaving snow trail on the ground.")
    private boolean preventSnowballFarms = true;
    @ConfigurationPath(value = "prevent-berry-farms", comments = "Prevents foxes from dropping sweet berries to the ground.")
    private boolean preventBerryFarms = true;
    @ConfigurationPath(value = "prevent-zerotick-farms", comments = "Prevents 'farmland-blocks' with 'farm-blocks' from being moved by pistons.")
    private boolean preventZerotickFarms = true;
    @ConfigurationPath(value = "prevent-string-dupe", comments = "Prevents string dupe.")
    private boolean preventStringDupe = true;
    @ConfigurationPath(value = "prevent-dripstone-farms", comments = "Prevents dripstone farm.")
    private boolean preventDripstoneFarms = true;
    @ConfigurationPath(value = "prevent-infinity-lava", comments = "Prevents infinity lava with dripstone.")
    private boolean preventInfinityLava = true;

    public boolean isPreventCactusFarms() {
        return preventCactusFarms;
    }

    public boolean isPreventPistonFarms() {
        return preventPistonFarms;
    }

    public boolean isPreventWaterHarvestingFarms() {
        return preventWaterHarvestingFarms;
    }

    public boolean isPreventEndermanHarvestingFarms() {
        return preventEndermanHarvestingFarms;
    }

    public boolean isPreventWaterlessFarms() {
        return preventWaterlessFarms;
    }

    public boolean isPreventLightlessFarms() {
        return preventLightlessFarms;
    }

    public boolean isPreventSnowballFarms() {
        return preventSnowballFarms;
    }

    public boolean isPreventBerryFarms() {
        return preventBerryFarms;
    }

    public boolean isPreventZerotickFarms() {
        return preventZerotickFarms;
    }

    public boolean isPreventStringDupe() {
        return preventStringDupe;
    }

    public boolean isPreventDripstoneFarms() {
        return preventDripstoneFarms;
    }

    public boolean isPreventInfinityLava() {
        return preventInfinityLava;
    }
}