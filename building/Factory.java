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

    Factory(TypeBuilding type, DayChanger dayChanger) {
        super(type, dayChanger);
        this.typeProduction = EnumConverter.BuildingsToProduction(type);
        requiredProduction = ResourceProperties.getRequiredProduction(typeProduction);
        trader = new FactoryTrader(stock, typeProduction, requiredProduction);        
    }

    @Override
    public void makeProduction() {
        for (TypeProduction t : requiredProduction) {
            if (stock.GetAmountOfProduct(t) > 1) {
                stock.giveProduct(t, 1);
            } else {
                return;
            }
        }
        stock.takeProduct(typeProduction, currentProductionPerDay);
        log.log(Level.INFO, "Produced {0} kilograms of {1}",
                new Object[]{currentProductionPerDay, typeProduction});
    }
}
