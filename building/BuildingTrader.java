/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import country.DayChanger;
import enumerationClasses.TypeProduction;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
class BuildingTrader implements ITrader, Observer {

    private static Logger log = Logger.getLogger(BuildingTrader.class.getName());
    protected Market market;
    protected Stock stock;
    protected int IDTrader;
    protected List<Offer> listOfOffers;
    private IWallet wallet;
    protected TypeProduction productForSale;
    private final int PRIMARY_CASH = 1000;
    private boolean isBankrut;
    private DayChanger dayChanger;

    BuildingTrader(Stock stock, TypeProduction type) {
        this.dayChanger =  DayChanger.getInstance();
        dayChanger.addObserver(this);
        this.stock = stock;
        wallet = new TraderWallet(PRIMARY_CASH);
        productForSale = type;
        this.market = Market.getInstance();
        market.registerTrader(this);

    }

    @Override
    public void takeProductPack(ProductPack pack) {
        TypeProduction type = pack.getTypeProduction();
        double weight = pack.getWeight();
        stock.takeProduct(type, weight);
        log.log(Level.INFO, "Taked pack : {0}, trader: {1} ", new Object[]{pack.toString(), IDTrader});
    }

    public void giveProductPack(ProductPack pack) {
        TypeProduction type = pack.getTypeProduction();
        double weight = pack.getWeight();
        stock.giveProduct(type, weight);
        log.log(Level.INFO, "Given pack : {0}, trader: {1}  ", new Object[]{pack.toString(), IDTrader});
    }

    public void takeMoney(double money) {
        wallet.takeMoney(money);
        log.log(Level.INFO, "Taken cash : {0} $, trader: {1}  ", new Object[]{money, IDTrader});
    }

    public void giveMoney(double money) {
        wallet.giveMoney(money);
        log.log(Level.INFO, "Given cash : {0} $, trader: {1}  ", new Object[]{money, IDTrader});
    }

    public double getMoneyBalance() {
        return wallet.getMoneyBalance();
    }

    public void receiveList(List<Offer> listOfOffers) {
        this.listOfOffers = listOfOffers;
        log.log(Level.INFO, " Trader {0} receive list of offers", IDTrader);
    }

    @Override
    public void setID(int i) {
        IDTrader = i;
    }

    @Override
    public void makeDailyOperation() {
        if (stock.GetAmountOfProduct(productForSale) != 0) {
            market.applay(findProductionForSell(), IDTrader);
        }
       
    }

    public ProductPack findProductionForSell() {
        double weightForSale = Math.round(stock.GetAmountOfProduct(productForSale) * 0.8);
        return new ProductPack(weightForSale, productForSale);
    }

    @Override
    public void setBankrut() {
        log.log(Level.INFO, "Trader {0} is bankrut", toString());
        isBankrut = true;
        unsubscribe();
    }

    @Override
    public boolean isTraderBankrut() {
        return isBankrut;
    }

    public String toString() {
        String answer = new String();
        return answer + "id: " + IDTrader + ", selles: " + productForSale;
    }

    private void unsubscribe() {
        dayChanger.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        makeDailyOperation();
         log.log(Level.INFO, " Trader {0} maked DailyOperation", IDTrader);
    }

    @Override
    public int getIDTrader() {
        return IDTrader;
    }

    public double getWeightResourseOnSctock(TypeProduction type) {
        return stock.GetAmountOfProduct(type);
    }

}
