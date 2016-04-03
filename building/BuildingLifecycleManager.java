package building;

import enumerationClasses.Level;
import enumerationClasses.TypeBuilding;



public class BuildingLifecycleManager {

    
    private ResourceBuilding building;
  
    public BuildingLifecycleManager(TypeBuilding type) {
       this.building = new ResourceBuilding(type);       
    }

    public void deployInstrument(Instrument instrument) {
        building.deployInstrument(instrument);
    }

    public void upgrade() {
        if (building.getCash().compare(BuildingProperty.getLvlUpCost(building.getLevel())) > 1);
    }

    public boolean availableToUpgradable() {
        // TODO - implement BuildingLIfecycleManager.availableToUpgradable
        throw new UnsupportedOperationException();
    }

    public void destroyBuilding() {
       
    }

    public String getlvl()
    {
        return building.getLevel();
    }
     
    public String getType()
    {
        return  building.toString();
    }

}
