package country;

import building.BuildingLifecycleManager;
import enumerationClasses.TypeProduction;
import java.util.List;

public class BuildingContainer {

	private List<BuildingLifecycleManager> listOfBuildings; 

	

	/**
	 * 
	 * @param TypeProduction
	 */
	public void buildBuilding(TypeProduction type) {
            if(availableToBuild(type))
            {
                BuildingLifecycleManager newBuilding = new BuildingLifecycleManager(type);
                listOfBuildings.add(newBuilding);
            }
            
	}

	/**
	 * 
	 * @param TypeProduction
	 */
	public boolean availableToBuild(TypeProduction type) {
		;
	}

}