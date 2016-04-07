package country;


import building.BuildingLifecycleManager;
import enumerationClasses.TypeBuilding;
import country.CountryController;
import java.util.List;
import service.BuildingsProperty;

public class BuildingContainer {
    private CountryController OwnCountry;
    private double countryCash;
    private List<BuildingLifecycleManager> listOfBuildings;

    public BuildingContainer(CountryController country) {
        OwnCountry = country;
        countryCash = 0;
    }

    
    public void buildBuilding(TypeBuilding type) {
        if (availableToBuild(type)) {
            BuildingLifecycleManager newBuilding = new BuildingLifecycleManager(type);
            listOfBuildings.add(newBuilding);
        }

    }

    public void setCountryBallance() {
        countryCash = OwnCountry.getCashOfCountry();
        
    }

    public boolean availableToBuild(TypeBuilding type) {
        setCountryBallance();
        return countryCash > BuildingsProperty.getCostOfBuilding(type);

    }

    public String getInformationAboutBuildings() {
        String info = new String();
        for (BuildingLifecycleManager b : listOfBuildings) {
            info = info + b.toString() + "/n";
        }
        return info;
    }
}
