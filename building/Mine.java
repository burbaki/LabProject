package building;

import country.DayChanger;

import enumerationClasses.EnumConverter;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.BuildingProperty;

public class Mine extends ResourceBuilding {
private static Logger log = Logger.getLogger(Mine.class.getName());
    public TypeProduction typeProduction;
    private double amountOfDeposits;

    public Mine(TypeBuilding type, DayChanger dayChanger) {
        super(type, dayChanger);
        amountOfDeposits = BuildingProperty.getAmountOfDeposit();
        typeProduction = EnumConverter.BuildingsToProduction(type);
        trader = new BuildingTrader(stock, typeProduction);
    }

    public double getAmountOfDeposit() {
        return amountOfDeposits;
    }

    @Override
    public boolean readyForDestroy() {

        if (super.readyForDestroy() || amountOfDeposits <= 0) {
            trader.setBankrut();
            log.log(Level.INFO, "Building {0}, ready for destroy", toString());
        }
        return false;
    }

    @Override
    public void makeProduction() {
        amountOfDeposits -= currentProductionPerDay;
        stock.takeProduct(typeProduction, currentProductionPerDay);
        log.log(Level.INFO, "Produced {0} kilograms of {1}",
                new Object[]{currentProductionPerDay, typeProduction});
    }

}
