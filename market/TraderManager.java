package market;

import building.BuildingLifecycleManager;
import country.DayChanger;
import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TraderManager implements Observer {

    private static Logger log = Logger.getLogger(TraderManager.class.getName());
    private List<ITrader> listOfTrader;
    private List<Offer> listOfOffers;
    private Market ownMarket;
    private static int count;
    private final DayChanger dayChanger;

    TraderManager(List<Offer> listOfOffers) {
        this.dayChanger = DayChanger.getInstance();
        dayChanger.addObserver(this);
        ownMarket = Market.getInstance();
        listOfTrader = new LinkedList<>();
        this.listOfOffers = listOfOffers;
        count = 0;
    }

    public void addTrader(ITrader trader) {
        if (trader != null) {
            trader.receiveList(listOfOffers);
            trader.setID(count++);
            listOfTrader.add(trader);
            log.log(Level.INFO, "addedTrader {0} with id: {1}",
                    new Object[]{trader.getClass(), trader.getIDTrader()});
        }
    }
// creating and ading traders

    public void sendListToAllTraders() {
        for (ITrader t : listOfTrader) {
            t.receiveList(listOfOffers);
        }
        log.log(Level.INFO, "Sended to all traders list of offers");
    }

    public void removeBankrutTraders() {
        Iterator<ITrader> iter = listOfTrader.iterator();
        while (iter.hasNext()) {
            ITrader t = iter.next();
            if (t.isTraderBankrut()) {
                iter.remove();
            }
        }
        log.log(Level.INFO, "removed all bankruts");
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
