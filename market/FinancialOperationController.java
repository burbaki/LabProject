/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import java.util.List;

/**
 *
 * @author Burbaki
 */
public class FinancialOperationController {

    OffersManager offersManager;
    TraderManager traderManager;
    List<MarketTrader> list;

    FinancialOperationController(TraderManager traderManager) {

        this.traderManager = traderManager;
        list = traderManager.getListOfTraders();
    }

    FinancialOperationController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void giveMoneyToTrader(double money, int IDTrader) {
        for (MarketTrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                t.takeMoney(money);
            }
        }
    }

    public void givePackToTrader(ProductPack pack, int IDTrader) {
        for (MarketTrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                t.takeProductPack(pack);
            }
        }
    }

    public void pickUpMoneyFromTrader(double money, int IDTrader) {
        for (MarketTrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                t.giveMoney(money);
            }
        }
    }

    public void pickUpProductionFromTrader(ProductPack pack, int IDTrader) {

        for (MarketTrader t : list) {
            if (t.getIDTrader() == IDTrader) {
                t.giveProductPack(pack);
            }
        }
    }

}
