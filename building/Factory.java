package building;

import country.DayChanger;
import enumerationClasses.EnumConverter;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;
import java.util.List;
import service.ResourceProperties;

public class Factory extends ResourceBuilding {

    private List<TypeProduction> requiredProduction;
    private TypeProduction typeProduction;
    FactoryFinanceManger financeManger;

    Factory(TypeBuilding type, DayChanger dayChanger) {
        super(type, dayChanger);
        this.typeProduction = EnumConverter.BuildingsToProduction(type);
        requiredProduction = ResourceProperties.getRequiredProduction(typeProduction);
        financeManger = new FactoryFinanceManger(stock, typeProduction);
    }

    public void makeProduction() {
        for (TypeProduction t : requiredProduction) {
            if (stock.GetAmountOfProduct(t) > 1) {
                stock.giveProduct(t, 1);
            } else {
                return;
            }
        }
        stock.takeProduct(typeProduction, currentProductionPerDay);
    }
}
