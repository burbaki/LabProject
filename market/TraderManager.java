package market;

import static country.CountryController.dayChanger;

import java.util.LinkedList;
import java.util.List;

public class TraderManager {

    private List<MarketTrader> listOfTrader;
    private List<Offer> listOfOffers;
    private Market OwnMarket;

    TraderManager(List<Offer> listOfOffers, Market market) {
        listOfTrader = new LinkedList<>();
        this.listOfOffers = listOfOffers;
        OwnMarket = market;
    }

    public MarketTrader createMarketTrader() {
        return new MarketTrader(dayChanger, listOfOffers, OwnMarket);
    }

    public void addTrader(MarketTrader trader) {
        if (trader != null) {
            trader.receiveList(listOfOffers);
        }
    }

    public void makeTraders(int quantity) {
        for(int i = 0; i < quantity; i++)
        {
            addTrader(createMarketTrader());            
        }
            
    }

    public void removeTrader(int IDTrader) {
        for(MarketTrader t : listOfTrader)
        {
            if(t.getIDTrader() == IDTrader)
                listOfOffers.remove(t);
        }
    }

    // this function checked money balance of trader and if he have few maoney return true
    public void IsTraderBankrut(int IDTrader) {
        
    }

    public int[] findBankrut() {
        
    }

}
