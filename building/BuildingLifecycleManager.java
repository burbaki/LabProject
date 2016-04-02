package building;

import enumerationClasses.Level;
import enumerationClasses.TypeBuilding;



public class BuildingLifecycleManager {

    private Level level;
    private ResourceBuilding building;
  
    public BuildingLifecycleManager(TypeBuilding type) {
       this.building = new ResourceBuilding(type);       
    }

    public void deployInstrument(Instrument instrument) {
        building.deployInstrument(instrument);
    }

    public void upgrade() {
        // TODO - implement BuildingLIfecycleManager.upgrade
        throw new UnsupportedOperationException();
    }

    public boolean availableToUpgradable() {
        // TODO - implement BuildingLIfecycleManager.availableToUpgradable
        throw new UnsupportedOperationException();
    }

    public void destroyBuilding() {
        // TODO - implement BuildingLIfecycleManager.destroyBuilding
        throw new UnsupportedOperationException();
    }

    public void buildBuilding(int TypeProduction) {
        // TODO - implement BuildingLIfecycleManager.build
        throw new UnsupportedOperationException();
    }
    public String getlvl()
    {
        return level.toString();
    }
     
    public String getType()
    {
        return level.toString() + building.toString();
    }

}
