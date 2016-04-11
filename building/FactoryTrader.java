/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import enumerationClasses.TypeProduction;
import java.util.List;
import market.Offer;

/**
 *
 * @author Burbaki
 */
public class FactoryTrader extends BuildingTrader {

    List<TypeProduction> requiredResourse;

    public FactoryTrader(Stock stock, TypeProduction type, List<TypeProduction> requiredResourse) {
        super(stock, type);
        this.requiredResourse = requiredResourse;
    }
public void makeDailyOperation() 
{
    super.makeDailyOperation();
    for (TypeProduction type : requiredResourse)
    {
        if(stock.GetAmountOfProduct(type)< 5)
            findApropriateOffer();
    }    
}
    public void findApropriateOffer() {
        double min = stock.GetAmountOfProduct(requiredResourse.get(0));
        TypeProduction mintype = requiredResourse.get(0);
        for (TypeProduction type : requiredResourse) {

            if (min > stock.GetAmountOfProduct(type)) {
                mintype = type;
            }
        }
        for (Offer o : listOfOffers) {
            
            if (o.getTypeProduction() == mintype) {
                market.pickUpOffer(o.getID(), IDTrader);
            }
        }
    }

}
