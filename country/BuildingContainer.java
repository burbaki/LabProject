package country;

import boxClasses.Money;
import building.BuildingLifecycleManager;
import enumerationClasses.TypeBuilding;
import country.CountryController;
import java.util.List;
import service.BuildingsProperty;

public class BuildingContainer {
    CountryController OwnCountry;
    private Money CountryCash = new Money(0);
    private List<BuildingLifecycleManager> listOfBuildings;

    public BuildingContainer(CountryController country) {
        OwnCountry = country;
    }

    
    public void buildBuilding(TypeBuilding type) {
        if (availableToBuild(type)) {
            BuildingLifecycleManager newBuilding = new BuildingLifecycleManager(type);
            listOfBuildings.add(newBuilding);
        }

    }

    public void setCountryBallance(Money money) {
        CountryCash.setMoney(OwnCountry.getCashOfCountry());
        
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
