package country;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



public class DayChanger extends Observable {

    private List<Observer> observers;
    private long counterOfDays = 0;

    public DayChanger() {
        observers = new LinkedList<>();
    }

    public void runDays(int Int) {
        // TODO - implement DayChanger.runDays
        throw new UnsupportedOperationException();
    }

    public void runOneDay() {
        try {
            Thread.sleep(1000);
            counterOfDays++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getCounterOfDays() {
        return counterOfDays;
    }
   
     public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update(this, counterOfDays);
        }
    }
    public void addObserver(Observer o) {
         observers.add(o);
    }

    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

   
}
