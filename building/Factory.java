package building;

import country.DayChanger;
import country.SingletonMarket;
import enumerationClasses.EnumConverter;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeProduction;
import java.util.List;
import market.ITrader;
import service.ResourceProperties;

public class Factory extends ResourceBuilding {

    private List<TypeProduction> requiredProduction;
    private TypeProduction typeProduction;
    protected ITrader trader;

    Factory(TypeBuilding type, DayChanger dayChanger) {
        super(type, dayChanger);
        this.typeProduction = EnumConverter.BuildingsToProduction(type);
        requiredProduction = ResourceProperties.getRequiredProduction(typeProduction);
        trader = new FactoryTrader(stock, typeProduction, requiredProduction, SingletonMarket.getInstance());
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
