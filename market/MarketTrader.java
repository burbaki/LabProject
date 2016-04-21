package market;

import building.Stock;
import country.DayChanger;
import enumerationClasses.TypeProduction;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarketTrader implements ITrader, Observer {

    private static Logger log = Logger.getLogger(MarketTrader.class.getName());
    private IWallet wallet;
    private Stock stock;
    private int IDTrader;
    private DayChanger dayChanger;
    private IMarketTraderUI traderUI;
    private List<Offer> listOfOffers;
    private Market market;
    private boolean isBankrut;

    public MarketTrader() {
        this.dayChanger =  DayChanger.getInstance();
        dayChanger.addObserver(this);
        traderUI = new SimpleMarketUI();
        wallet = new TraderWallet();
        this.market = Market.getInstance();
        market.registerTrader(this);
    }

    public void setID(int ID) {
        IDTrader = ID;
        log.log( Level.INFO, "Set trader's id : {0}", ID);
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void receiveList(List<Offer> list) {
        listOfOffers = list;
        log.log(Level.INFO, " Trader {0} receive list of offers", IDTrader);
    }

    public int getIDTrader() {
        return this.IDTrader;
    }

    public int findApropriateOffer() {
        return traderUI.findApropriateOffer(listOfOffers, wallet.getMoneyBalance());
    }

    public ProductPack findProductionForSell() {
        return traderUI.findProductionForSell(listOfOffers, stock);

    }

    public void makeDailyOperation() {
        market.applay(findProductionForSell(), IDTrader);
        market.pickUpOffer(findApropriateOffer(), IDTrader);
        log.log(Level.INFO, " Trader {0} maked DailyOperation", IDTrader);
    }

    public void update(Observable o, Object arg) {
        makeDailyOperation();
    }

    public double getMoneyBalance() {
        return wallet.getMoneyBalance();

    }

    private void unsubscribe() {
        dayChanger.deleteObserver(this);
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
        if (wallet.getMoneyBalance() < 10) {
            setBankrut();
        }
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

    @Override
    public double getWeightResourseOnSctock(TypeProduction type) {
        return stock.GetAmountOfProduct(type);
    }
}
