/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import building.Stock;
import enumerationClasses.TypeProduction;
import java.util.List;

/**
 *
 * @author Burbaki
 */
public class SimpleMarketUI implements IMarketTraderUI {

    @Override
    public int findApropriateOffer(List<Offer> listOfOffers, double money) {
        int i = (int) (Math.random() % listOfOffers.size());
        if (listOfOffers.get(i).getPriceOfPack() > money) {
            return listOfOffers.get(i).getID();
        } else {
            return -1;
        }
    }

    @Override
    public ProductPack findProductionForSell(List<Offer> listOfOffers, Stock stock) {
        TypeProduction maxValue = TypeProduction.BOARDS;
        for (TypeProduction type : TypeProduction.values()) {
            if (stock.GetAmountOfProduct(type) > stock.GetAmountOfProduct(maxValue)) {
                maxValue = type;
            }
        }
        double weightForSale = Math.round(stock.GetAmountOfProduct(maxValue) * 0.8);
        return new ProductPack(weightForSale, maxValue);
    }

}
