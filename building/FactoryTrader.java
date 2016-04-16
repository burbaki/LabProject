/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import enumerationClasses.TypeProduction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import market.Market;
import market.Offer;

/**
 *
 * @author Burbaki
 */
public class FactoryTrader extends BuildingTrader {

    private static Logger log = Logger.getLogger(FactoryTrader.class.getName());
    List<TypeProduction> requiredResourse;

    public FactoryTrader(Stock stock, TypeProduction type, List<TypeProduction> requiredResourse) {
        super(stock, type);
        this.requiredResourse = requiredResourse;
    }

    @Override
    public void makeDailyOperation() {
        super.makeDailyOperation();
        int IDProductionForBuy = findApropriateOffer();
        if (IDProductionForBuy != -1) {
            market.pickUpOffer(IDProductionForBuy, IDTrader);
        } else {
            log.log(Level.INFO, "Dont need resourse , {0} trader", IDTrader);
        }

    }

    public int findApropriateOffer() {
        double min = stock.GetAmountOfProduct(requiredResourse.get(0));
        if (isNeedResourse()) {
            TypeProduction mintype = requiredResourse.get(0);
            for (TypeProduction type : requiredResourse) {

                if (min > stock.GetAmountOfProduct(type)) {
                    mintype = type;
                }
            }
            for (Offer o : listOfOffers) {
                if (o.getTypeProduction() == mintype
                        && (getMoneyBalance() - o.getPriceOfPack()) > 0) {
                    return o.getID();
                }
            }
        }
        return -1;
    }

    private boolean isNeedResourse() {
        for (TypeProduction type : requiredResourse) {
            if (stock.GetAmountOfProduct(type) < 5) {
                return true;
            }
        }
        return false;
    }

}
