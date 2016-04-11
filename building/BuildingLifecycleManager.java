package building;

import static country.CountryController.dayChanger;
import enumerationClasses.TypeBuilding;
import service.BuildingProperty;

public class BuildingLifecycleManager {

    private ResourceBuilding building;

    public BuildingLifecycleManager(TypeBuilding type) {
        if (createBuilding(type) != null) {
            this.building = createBuilding(type);
        }
    }

    public void deployInstrument(Instrument instrument) {
        building.deployInstrument(instrument);
    }

    public void upgrade() {
        if (availableToUpgradable()) {
            building.upgrade();
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
