package market;

import static country.CountryController.dayChanger;

import java.util.LinkedList;
import java.util.List;

public class TraderManager {

    private List<MarketTrader> listOfTrader;
    private List<Offer> listOfOffers;
    private Market OwnMarket;
    private static int count;

    TraderManager(List<Offer> listOfOffers, Market market) {
        listOfTrader = new LinkedList<>();
        this.listOfOffers = listOfOffers;
        OwnMarket = market;
        count = 0;
    }

    public MarketTrader createMarketTrader() {
        return new MarketTrader(dayChanger);
    }
//add anyone trader

    public void addTrader(ITrader trader) {
        if (trader != null) {
            trader.receiveList(listOfOffers);
            trader.setID(count++);
            trader.setMarket(OwnMarket);
        }
    }
// creating and ading traders

    public void makeTraders(int quantity) {
        for (int i = 0; i < quantity; i++) {
            addTrader(createMarketTrader());
        }

    }
// its is need for random deleting unnecessary trader

    public void removeTrader(int IDTrader) {
        for (MarketTrader t : listOfTrader) {
            if (t.getIDTrader() == IDTrader) {
                listOfOffers.remove(t);
            }
        }
    }

    // this function checked money balance of trader and if he have few maoney return true
    public boolean IsTraderBankrut(MarketTrader trader) {
        return (trader.getBalance() < 10);
    }

    public void findBankrut() {
        for (MarketTrader t : listOfTrader) {
            if (IsTraderBankrut(t)) {
                removeTrader(t.getIDTrader());
            }
        }
    }

    public List<MarketTrader> getListOfTraders() {
        return listOfTrader;
    }

}
