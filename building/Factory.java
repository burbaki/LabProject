package building;

import country.DayChanger;

import enumerationClasses.EnumConverter;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;
import java.util.List;
import java.util.logging.Level;

import java.util.logging.Logger;
import service.ResourceProperties;

public class Factory extends ResourceBuilding {

    private static Logger log = Logger.getLogger(Factory.class.getName());
    private List<TypeProduction> requiredProduction;
    private TypeProduction typeProduction;

    Factory(TypeBuilding type) {
        super(type);
        this.typeProduction = EnumConverter.BuildingsToProduction(type);
        requiredProduction = ResourceProperties.getRequiredProduction(typeProduction);
        trader = new FactoryTrader(stock, typeProduction, requiredProduction);
         log.log(Level.INFO, "Created buildingTrader with {0}", trader.toString());
    }

    private boolean readyForProduction() {
        for (TypeProduction t : requiredProduction) {
            if (stock.GetAmountOfProduct(t) < 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void makeProduction() {
        if (readyForProduction()) {
            for (TypeProduction t : requiredProduction) {
                stock.giveProduct(t, 1);
            }
            stock.takeProduct(typeProduction, currentProductionPerDay);
             log.log(Level.INFO, "Produced {0} kilograms of {1}",
                new Object[]{currentProductionPerDay, typeProduction});
        }
       
    }

}
