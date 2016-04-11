package building;

import enumerationClasses.TypeProduction;
import market.Offer;

public class FinanceManager {

    static void deleteTrader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double salaryPerDay;
    private BuildingTrader buildingTrader;

    public FinanceManager(Stock stock, TypeProduction type) {
        buildingTrader = new BuildingTrader(stock, type);
    }

    double getMoneyBalance() {
        return buildingTrader.getMoneyBalance();
    }

    public void makeDailyOperation() {
        buildingTrader.giveMoney(salaryPerDay);
        buildingTrader.makeDailyOperation();
    }

    void giveMoney(double money) {
        buildingTrader.giveMoney(money);
    }

}
