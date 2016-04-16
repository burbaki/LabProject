package country;

import building.BuildingLifecycleManager;
import enumerationClasses.TypeBuilding;
import country.CountryController;
import java.util.List;
import java.util.logging.Logger;
import service.BuildingProperty;

public class BuildingContainer {

    private static Logger log = Logger.getLogger(BuildingContainer.class.getName());
    private CountryController OwnCountry;

    private List<BuildingLifecycleManager> listOfBuildings;

    public BuildingContainer(CountryController country) {
        OwnCountry = country;

    }

    public void buildBuilding(TypeBuilding type) {
        if (availableToBuild(type)) {
            BuildingLifecycleManager newBuilding = new BuildingLifecycleManager(type);
            OwnCountry.giveMoney(BuildingProperty.getCostOfBuilding(type));
            listOfBuildings.add(newBuilding);
        } else {
            log.info("Building not build");
        }

    }

    public boolean availableToBuild(TypeBuilding type) {

        return OwnCountry.getCashOfCountry() > BuildingProperty.getCostOfBuilding(type);

    }

    public String getInformationAboutBuildings() {
        String info = new String();
        for (BuildingLifecycleManager b : listOfBuildings) {
            info = info + b.toString() + "/n";
        }
        return info;
    }

    void makeDailyOperation() {
        checkBuildingBankrut();
    }

    private void checkBuildingBankrut() {
        for (BuildingLifecycleManager b : listOfBuildings) {
            if (b.readyForDestroy()) {
                listOfBuildings.remove(b);
            }
        }
    }

}
