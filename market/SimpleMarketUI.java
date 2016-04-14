/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import building.Stock;
import java.util.List;

/**
 *
 * @author Burbaki
 */
public class SimpleMarketUI implements IMarketTraderUI {

    @Override
    public int findApropriateOffer(List<Offer> listOfOffers, double money) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductPack findProductionForSell(List<Offer> listOfOffers, Stock stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
