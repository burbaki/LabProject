package building;

import enumerationClasses.TypeBuilding;
import service.BuildingsProperty;



public class BuildingLifecycleManager {

    
    private ResourceBuilding building;
  
    public BuildingLifecycleManager(TypeBuilding type) {
       this.building = new ResourceBuilding(type);       
    }

    public void deployInstrument(Instrument instrument) {
        building.deployInstrument(instrument);
    }

    public void upgrade() {
       
    }

    public boolean availableToUpgradable() {
        return building.getMoneyBalance() > BuildingsProperty.getLvlUpCost(building.getLevel());
    }

    public void destroyBuilding() {
       
    }

    public String getlvl()
    {
        return building.getLevel().toString();
    }
     
    public String getType()
    {
        return  building.toString();
    }

}
