/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import country.DayChanger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Burbaki
 */
public class FinancialOperationController {

    private static Logger log = Logger.getLogger(FinancialOperationController.class.getName());
    List<ITrader> list;

    public void giveMoneyToTrader(double money, int IDTrader) {
        for (ITrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                t.takeMoney(money);
            }
        }
        log.log(Level.INFO, "Given {0} $ to {1} trader", new Object[]{money, IDTrader});
    }

    public void givePackToTrader(ProductPack pack, int IDTrader) {
        for (ITrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                t.takeProductPack(pack);
            }
        }
        log.log(Level.INFO, "Given {0} $ to {1} trader", new Object[]{pack, IDTrader});
    }

    public void pickUpMoneyFromTrader(double money, int IDTrader) {
        for (ITrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                if (money < t.getMoneyBalance()) {
                    t.giveMoney(money);
                } else {
                    log.log(Level.INFO, "haven't enough money in {0} trader's balance", IDTrader);
                    return;
                }
            }
        }
        log.log(Level.INFO, "take {0} $ from {1} trader", new Object[]{money, IDTrader});
    }

    public void pickUpProductionFromTrader(ProductPack pack, int IDTrader) {

        for (ITrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                if (pack.getWeight() < t.getWeightResourseOnSctock(pack.getTypeProduction())) {
                    t.giveProductPack(pack);
                } else {
                    log.log(Level.INFO, "haven't enough resourse in {0} trader's stock", IDTrader);
                    return;
                }
            }
        }
        log.log(Level.INFO, "take {0} $ from {1} trader", new Object[]{pack, IDTrader});
    }

    void receiveListOfTraders(List<ITrader> listOfTraders) {
        this.list = listOfTraders;
        log.log(Level.INFO, "receive new list of traders");
    }
}
