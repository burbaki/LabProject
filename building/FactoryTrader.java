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
        int IDProductionForBuy = 0;
        try {
            IDProductionForBuy = findApropriateOffer();
        } catch (NoMoneyException ex) {
            Logger.getLogger(FactoryTrader.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (IDProductionForBuy != -1) {
            market.pickUpOffer(IDProductionForBuy, IDTrader);
            System.out.println(stock);
        } else {
            log.log(Level.INFO, "Dont need resourse , {0} trader", IDTrader);
        }

    }

    public int findApropriateOffer() throws NoMoneyException {
        double minRes = stock.GetAmountOfProduct(requiredResourse.get(0));
        TypeProduction minType = requiredResourse.get(0);
        for (TypeProduction type : requiredResourse) {
            if (minRes > stock.GetAmountOfProduct(type)) {
                minRes = stock.GetAmountOfProduct(type);
                minType = type;
                break;
            }
        }
        if (minRes <= 5) {
            for (Offer o : listOfOffers) {
                if (o.getTypeProduction() == minType
                        && o.getPriceOfPack() < this.getMoneyBalance()) {
                    return o.getID();
                }
            }
        }
        throw new NoMoneyException("no money", this.getMoneyBalance());
        //return -1;
    }

}
