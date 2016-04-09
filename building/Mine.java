package building;

import country.DayChanger;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;
import java.util.Observable;
import service.BuildingProperty;

public class Mine extends ResourceBuilding {
public TypeProduction typeProduction;
    private double amountOfDeposits;

    public Mine( DayChanger dayChanger, TypeProduction type) {
        super(dayChanger);
        amountOfDeposits = BuildingProperty.getAmountOfDeposit();        
        typeProduction = type;
    }


    public double getAmountOfDeposit() {
        return amountOfDeposits;
    }

    @Override
    public void makeProduction() {
        amountOfDeposits -= currentProductionPerDay;
        
    }     

}
