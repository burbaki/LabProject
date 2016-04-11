/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import enumerationClasses.TypeProduction;
import java.util.List;
import market.ITrader;
import market.ITraderUI;
import market.IWallet;
import market.Market;
import market.Offer;
import market.ProductPack;
import market.TraderWallet;

/**
 *
 * @author Burbaki
 */
class BuildingTrader implements ITrader {

    protected Market market;
    protected Stock stock;
    protected int IDTrader;
    protected List<Offer> listOfOffers;
    private IWallet wallet;
    private TypeProduction priorityProductForSale;
    private final int PRIMARY_CASH = 1000;

    BuildingTrader(Stock stock, TypeProduction type) {
        this.stock = stock;
        wallet = new TraderWallet(PRIMARY_CASH);
        priorityProductForSale = type;

    }

    public void takeProductPack(ProductPack pack) {
        TypeProduction type = pack.getTypeProduction();
        double weight = pack.getWeight();
        stock.takeProduct(type, weight);

    }

    public void giveProductPack(ProductPack pack) {
        TypeProduction type = pack.getTypeProduction();
        double weight = pack.getWeight();
        stock.giveProduct(type, weight);
    }

    public void takeMoney(double money) {
        wallet.takeMoney(money);
    }

    public void giveMoney(double money) {
        wallet.giveMoney(money);
    }

    public double getMoneyBalance() {
        return wallet.getBalance();
    }

    @Override
    public void receiveList(List<Offer> listOfOffers) {
        this.listOfOffers = listOfOffers;
    }

    @Override
    public void setID(int i) {
        IDTrader = i;
    }

    @Override
    public void makeDailyOperation() {
        findApropriateOffer();
        market.applay(findProductionForSell(), IDTrader);

    }

    @Override
    public void setMarket(Market market) {
        this.market = market;
    }

    public void findApropriateOffer() {

    }

    public ProductPack findProductionForSell() {
        double weightForSale = stock.GetAmountOfProduct(priorityProductForSale) / 3;
        return  new ProductPack( weightForSale , priorityProductForSale);
    }

}
