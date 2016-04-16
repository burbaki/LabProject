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

    private static Logger log = Logger.getLogger(BuildingTrader.class.getName());
    protected Market market;
    protected Stock stock;
    protected int IDTrader;
    protected List<Offer> listOfOffers;
    private IWallet wallet;
    private TypeProduction productForSale;
    private final int PRIMARY_CASH = 1000;
    private boolean isBankrut;

    BuildingTrader(Stock stock, TypeProduction type) {
        this.stock = stock;
        wallet = new TraderWallet(PRIMARY_CASH);
        productForSale = type;
        this.market = Market.getInstance();
        market.registerTrader(this);
        log.log(Level.INFO, "Created buildingTrader with {0}", toString());
    }

    public void takeProductPack(ProductPack pack) {
        TypeProduction type = pack.getTypeProduction();
        double weight = pack.getWeight();
        stock.takeProduct(type, weight);
        log.log(Level.INFO, "Taked pack : {0} ", pack.toString());
    }

    public void giveProductPack(ProductPack pack) {
        TypeProduction type = pack.getTypeProduction();
        double weight = pack.getWeight();
        stock.giveProduct(type, weight);
        log.log(Level.INFO, "Given pack : {0} ", pack.toString());
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
        double weightForSale = stock.GetAmountOfProduct(productForSale) / 3;
        return new ProductPack(weightForSale, productForSale);
    }

    @Override
    public void setBankrut() {
        log.log(Level.INFO, "Trader {0} is bankrut", toString());
        isBankrut = true;
    }

    @Override
    public boolean isTraderBankrut() {
        return isBankrut;
    }

    public String toString() {
        String answer = new String();
        return answer + "Created buildingTrader with id: " + IDTrader + ", selles: " + productForSale;        
    }

}
