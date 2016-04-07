package building;

import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;


public class Factory extends ResourceBuilding {

	private TypeProduction[] requiredProduction;
//конструктор 

    public Factory(TypeBuilding type) {
        super(type);
    }
}