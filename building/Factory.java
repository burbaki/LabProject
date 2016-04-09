package building;

import country.DayChanger;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;


public class Factory extends ResourceBuilding {

	private TypeProduction[] requiredProduction;
//конструктор 

    public Factory(TypeBuilding type) {
        super(type);
    }

    Factory(TypeBuilding type, DayChanger dayChanger) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}