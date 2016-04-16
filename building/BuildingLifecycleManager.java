package building;

import static country.CountryController.dayChanger;
import enumerationClasses.TypeBuilding;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.BuildingProperty;

public class BuildingLifecycleManager {

    private static final Logger log = Logger.getLogger(BuildingLifecycleManager.class.getName());
    private ResourceBuilding building;

    public BuildingLifecycleManager(TypeBuilding type) {
        if (createBuilding(type) != null) {
            this.building = createBuilding(type);
            log.log(Level.INFO, "Created {0} building", building.toString());
        }
    }

    public void upgrade() {
        if (availableToUpgradable()) {
            building.upgrade();
            log.log(Level.INFO, "Upgrated{0} building", building.toString());
        }
    }

    public boolean availableToUpgradable() {
        return building.getMoneyBalance() > BuildingProperty.getLvlUpCost(building.getLevel())
                && building.getLevel() <= 5;

    }

    public String getlvl() {
        return building.getLevel() + " level";
    }

    public boolean readyForDestroy() {
        return building.readyForDestroy();
    }

    public String getType() {
        return building.toString();
    }
//issues space, need rework

    private ResourceBuilding createBuilding(TypeBuilding type) {
        if ((((((type == TypeBuilding.DRILL
                || type == TypeBuilding.COMBAIN)
                || type == TypeBuilding.BENCH)
                || type == TypeBuilding.SAW)
                || type == TypeBuilding.PUMP)
                || type == TypeBuilding.EQUIPMENT)
                || type == TypeBuilding.MICROSCEMES) {
            return new InstrumentFactory(type, dayChanger);
        } else if (type == TypeBuilding.COIL
                || type == TypeBuilding.WOOD
                || type == TypeBuilding.IRONORE
                || type == TypeBuilding.WATER
                || type == TypeBuilding.COOPERORE
                || type == TypeBuilding.SILICON) {
            return new Mine(type, dayChanger);
        } else if (type == TypeBuilding.COOPER
                || type == TypeBuilding.MICROSCEMES
                || type == TypeBuilding.STEEL
                || type == TypeBuilding.BOARDS) {
            return new Factory(type, dayChanger);
        }
        return null;
    }

}
