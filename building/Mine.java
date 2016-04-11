package building;

import country.DayChanger;
import enumerationClasses.EnumConverter;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;
import java.util.Observable;
import service.BuildingProperty;

public class Mine extends ResourceBuilding {

    public TypeProduction typeProduction;
    private double amountOfDeposits;

    public Mine(TypeBuilding type, DayChanger dayChanger) {
        super(type, dayChanger);
        amountOfDeposits = BuildingProperty.getAmountOfDeposit();
        typeProduction = EnumConverter.BuildingsToProduction(type);
    }

    public double getAmountOfDeposit() {
        return amountOfDeposits;
    }

    @Override
    public boolean readyForDestroy() {
        
        return (super.readyForDestroy() || amountOfDeposits <= 0);
    }
    
    @Override
    public void makeProduction() {
        amountOfDeposits -= currentProductionPerDay;
        stock.takeProduct(EnumConverter.BuildingsToProduction(typeBuilding), currentProductionPerDay);
    }

}
