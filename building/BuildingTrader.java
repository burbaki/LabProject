/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import enumerationClasses.TypeProduction;
import java.util.List;
import market.ITrader;
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
    private TypeProduction ProductForSale;
    private final int PRIMARY_CASH = 1000;
    private boolean isBankrut;

    BuildingTrader(Stock stock, TypeProduction type, Market market) {
        this.stock = stock;
        wallet = new TraderWallet(PRIMARY_CASH);
        ProductForSale = type;
        this.market = market;
        market.registerTrader(this);

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
        return wallet.getMoneyBalance();
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
        market.applay(findProductionForSell(), IDTrader);
    }

    public ProductPack findProductionForSell() {
        double weightForSale = stock.GetAmountOfProduct(ProductForSale) / 3;
        return  new ProductPack( weightForSale , ProductForSale);
    }

    @Override
    public void setBankrut() {
        isBankrut = true;
    }

    @Override
    public boolean isTraderBankrut() {
        return isBankrut;
    }

}
