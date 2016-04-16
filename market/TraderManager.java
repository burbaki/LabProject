package market;

import country.DayChanger;


import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TraderManager implements Observer{

    private List<ITrader> listOfTrader;
    private List<Offer> listOfOffers;
    private Market ownMarket;
    private static int count;
    private final DayChanger dayChanger;

    TraderManager(List<Offer> listOfOffers) {
        this.dayChanger = country.CountryController.dayChanger;
        dayChanger.addObserver(this);
        ownMarket = Market.getInstance();
        listOfTrader = new LinkedList<>();
        this.listOfOffers = listOfOffers;        
        count = 0;
    }

    public MarketTrader createMarketTrader() {
        return new MarketTrader(ownMarket);
    }
//add anyone trader

    public void addTrader(ITrader trader) {
        if (trader != null) {
            trader.receiveList(listOfOffers);
            trader.setID(count++);
            
        }
    }
// creating and ading traders

    public void sendListToAllTraders()
    {
        for(ITrader t : listOfTrader)
            t.receiveList(listOfOffers);
    }
    public void makeTraders(int quantity) {
        for (int i = 0; i < quantity; i++) {
            addTrader(createMarketTrader());
        }

    }
// its is need for random deleting unnecessary trader

    public void removeBankrutTraders() {
        for (ITrader t : listOfTrader) {
            if (t.isTraderBankrut()) {
                listOfTrader.remove(t);
            }
        }
    }   

    public List<ITrader> getListOfTraders() {
        return listOfTrader;
    }

    @Override
    public void update(Observable o, Object arg) {
        removeBankrutTraders();
        sendListToAllTraders();
    }
}
