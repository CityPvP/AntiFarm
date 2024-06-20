package config.global.villager;

import config.global.villager.golem.PreventGolemSpawningConfig;
import config.global.villager.infection.PreventVillagersInfectionConfig;
import config.global.villager.zombie.PreventZombieVillagersCureConfig;
import fr.bramsou.yaml.api.configuration.dynamic.ConfigurationPart;
import fr.bramsou.yaml.api.configuration.dynamic.annotation.ConfigurationPath;

public class VillagerSettingsConfig extends ConfigurationPart {

    @ConfigurationPath(value = "prevent-villagers-profession-change", comments = "Once the villager gets a profession it prevents it from being changed.")
    private boolean preventVillagersProfessionChange = true;
    @ConfigurationPath(value = "prevent-villagers-harvesting-farms", comments = "Prevents villagers from harvesting and planting fields.")
    private boolean preventVillagersHarvestingFarms = true;
    @ConfigurationPath(value = "prevent-villagers-breed", comments = "Prevent villagers from breeding.")
    private boolean preventVillagersBreed = true;
    @ConfigurationPath(value = "prevent-villagers-infection", comments = "Prevents villagers from getting infected. (This blocks the way to get discounts from villagers on your server.)")
    private PreventVillagersInfectionConfig preventVillagersInfection = new PreventVillagersInfectionConfig();
    @ConfigurationPath(value = "prevent-zombie-villagers-cure", comments = "Prevent zombie villagers from being cured. (This makes it difficult to reach villagers on your server, making them valuable.)")
    private PreventZombieVillagersCureConfig preventZombieVillagersCure = new PreventZombieVillagersCureConfig();
    @ConfigurationPath(value = "prevent-targeting-villager", comments = "Prevents villagers from being targeted by creatures. (Helps protect the villager population.)")
    private boolean preventTargetingVillager = false;
    @ConfigurationPath(value = "prevent-villager-trade", comments = "Prevents trade with villagers.")
    private boolean preventVillagerTrade = false;
    @ConfigurationPath(value = "prevent-wandering-trader-trade", comments = "Prevents trade with wandering traders.")
    private boolean preventWanderingTraderTrade = false;
    @ConfigurationPath(value = "prevent-golem-spawning", comments = "Disables iron golem spawning with villager interaction.")
    private PreventGolemSpawningConfig preventGolemSpawning = new PreventGolemSpawningConfig();

    public boolean isPreventVillagersProfessionChange() {
        return preventVillagersProfessionChange;
    }

    public boolean isPreventVillagersHarvestingFarms() {
        return preventVillagersHarvestingFarms;
    }

    public boolean isPreventVillagersBreed() {
        return preventVillagersBreed;
    }

    public PreventVillagersInfectionConfig getPreventVillagersInfection() {
        return preventVillagersInfection;
    }

    public PreventZombieVillagersCureConfig getPreventZombieVillagersCure() {
        return preventZombieVillagersCure;
    }

    public boolean isPreventTargetingVillager() {
        return preventTargetingVillager;
    }

    public boolean isPreventVillagerTrade() {
        return preventVillagerTrade;
    }

    public boolean isPreventWanderingTraderTrade() {
        return preventWanderingTraderTrade;
    }

    public PreventGolemSpawningConfig getPreventGolemSpawning() {
        return preventGolemSpawning;
    }
}
