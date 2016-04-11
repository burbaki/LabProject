package building;


import building.FinanceManager;
import enumerationClasses.TypeProduction;

public class FactoryFinanceManger extends FinanceManager {

    public FactoryFinanceManger(Stock stock, TypeProduction type) {
        super(stock, type);
    }


}