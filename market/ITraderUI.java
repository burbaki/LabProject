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
public interface ITraderUI {

    

   
//Return id of Offer
    public int findApropriateOffer(List<Offer> listOfOffers, double money);

    public ProductPack findProductionForSell(List<Offer> listOfOffers, Stock stock);

}
