package market;

import static country.CountryController.dayChanger;

import java.util.LinkedList;
import java.util.List;

public class TraderManager {

    private List<MarketTrader> listOfTrader;
    private List<Offer> listOfOffers;
    private Market ownMarket;
    private static int count;

    TraderManager(List<Offer> listOfOffers, Market market) {
        listOfTrader = new LinkedList<>();
        this.listOfOffers = listOfOffers;
        ownMarket = market;
        count = 0;
    }

    public MarketTrader createMarketTrader() {
        return new MarketTrader(dayChanger, ownMarket);
    }
//add anyone trader

    public void addTrader(ITrader trader) {
        if (trader != null) {
            trader.receiveList(listOfOffers);
            trader.setID(count++);
            
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

    public void findBankrut() {
        for (MarketTrader t : listOfTrader) {
            if (t.isTraderBankrut()) {
                removeTrader(t.getIDTrader());
            }
        }
    }

    public List<MarketTrader> getListOfTraders() {
        return listOfTrader;
    }

}
