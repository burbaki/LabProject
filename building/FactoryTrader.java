/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import enumerationClasses.TypeProduction;
import java.util.List;
import market.Market;
import market.Offer;

/**
 *
 * @author Burbaki
 */
public class FactoryTrader extends BuildingTrader {

    List<TypeProduction> requiredResourse;

    public FactoryTrader(Stock stock, TypeProduction type, List<TypeProduction> requiredResourse, Market market) {
        super(stock, type, market);
        this.requiredResourse = requiredResourse;
    }

    public void makeDailyOperation() {
        super.makeDailyOperation();
        int IDProductionForBuy = findApropriateOffer();
        if (IDProductionForBuy != -1) {
            market.pickUpOffer(IDProductionForBuy, IDTrader);
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
                if (o.getTypeProduction() == mintype) {
                    market.pickUpOffer(o.getID(), IDTrader);
                    return o.getID();
                }
            }
        }
        return -1;
    }

    private boolean isNeedResourse() {
        for (TypeProduction type : requiredResourse) {
            if( stock.GetAmountOfProduct(type) < 5)
            {
                return true;
            }
        }
        return false;
    }

}
