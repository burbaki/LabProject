package country;

import boxClasses.Money;
import building.BuildingLifecycleManager;
import enumerationClasses.TypeBuilding;

import java.util.List;
import service.BuildingsProperty;

public class BuildingContainer {

    private Money CountryCash = new Money(0);
    private List<BuildingLifecycleManager> listOfBuildings;

    public void buildBuilding(TypeBuilding type) {
        if (availableToBuild(type)) {
            BuildingLifecycleManager newBuilding = new BuildingLifecycleManager(type);
            listOfBuildings.add(newBuilding);
        }

    }

    public void setCountryBallance(Money money) {
        CountryCash.setMoney(money);
    }

    public boolean availableToBuild(TypeBuilding type) {

        return CountryCash.Compare(BuildingsProperty.getCostOfBuilding(type)) > 1;

    }

    public String getInformationAboutBuildings() {
        String info = new String();
        for (BuildingLifecycleManager b : listOfBuildings) {
            info = info + b.toString() + "/n";
        }
        return info;
    }
}
