package country;

import building.ResourceBuilding;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import market.ITrader;

public class DayChanger extends Observable {

    private static DayChanger instance;
    private static Logger log = Logger.getLogger(DayChanger.class.getName());
    private List<Observer> buildingObservers;
    private List<Observer> traderObservers;
    private List<Observer> othersObservers;
    private long counterOfDays = 0;

    public static DayChanger getInstance() {
        if (instance == null) {
            instance = new DayChanger();
        }
        return instance;
    }

    public DayChanger() {
        buildingObservers = new LinkedList<>();
        traderObservers = new LinkedList<>();
        othersObservers = new LinkedList<>();

    }

    public void runDays(int day) {
        for (int i = 1; i <= day; i++) {
            runOneDay();
        }
    }

    public void runOneDay() {
        try {
            Thread.sleep(1);
            counterOfDays++;
            notifyObservers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getCounterOfDays() {
        return counterOfDays;
    }

    public void notifyObservers() {
        log.log(Level.INFO, "New {0} day begin", counterOfDays);
        for (Observer o : buildingObservers) {
            o.update(this, counterOfDays);
        }
        for (Observer o : traderObservers) {
            o.update(this, counterOfDays);
        }

        for (Observer o : othersObservers) {
            o.update(this, counterOfDays);
        }
        log.log(Level.INFO, " {0} day end", counterOfDays);
    }

    @Override
    public void addObserver(Observer o) {
        if (o instanceof ResourceBuilding) {
            buildingObservers.add(o);
        } else if (o instanceof ITrader) {
            traderObservers.add(o);
        } else {
            othersObservers.add(o);
        }
        log.log(Level.INFO, "New observer added: {0}", o.getClass());
    }

    @Override
    public void deleteObserver(Observer o) {
        if (o instanceof ResourceBuilding) {
            buildingObservers.remove(o);
        } else if (o instanceof ITrader) {
            traderObservers.remove(o);
        } else {
            othersObservers.remove(o);
        }
        log.log(Level.INFO, "observer deleted: {0}", o.getClass());
    }

}
