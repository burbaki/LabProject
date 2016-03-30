package building;

import enumerationClasses.TypeProduction;

public class Factory extends ResourceBuilding {

	private TypeProduction[] requiredProduction;
//конструктор 

    public Factory(int TypeProduction) {
        super(TypeProduction);
    }
}