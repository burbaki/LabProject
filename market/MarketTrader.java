package market;

import building.Stock;
import country.DayChanger;
import enumerationClasses.TypeProduction;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MarketTrader implements ITrader, Observer {

    private IWallet wallet;
    private Stock stock;
    private int IDTrader;
    private DayChanger dayChanger;
    private IMarketTraderUI traderUI;
    private List<Offer> listOfOffers;
    private Market market;
    private boolean isBankrut;

    public MarketTrader(DayChanger dayChanger, Market market) {
        this.dayChanger = dayChanger;
        dayChanger.addObserver(this);
        traderUI = new SimpleMarketUI();
        wallet = new TraderWallet();
        this.market = market;
        market.registerTrader(this);

    }

    public void setID(int ID) {
        IDTrader = ID;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void receiveList(List<Offer> list) {
        listOfOffers = list;
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
    }

    public void update(Observable o, Object arg) {
        makeDailyOperation();
    }

    public double getMoneyBalance() {
        return wallet.getMoneyBalance();

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
        isBankrut = true;
    }

    @Override
    public boolean isTraderBankrut() {
        return isBankrut;
    }
}
