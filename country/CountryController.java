package country;

import java.util.Observable;
import java.util.Observer;
import market.Market;

public class CountryController implements Observer {

    private InstrumentDistributer instrumentDistributer;
    private Market market;
    private double cashOfCountry;
    public static DayChanger dayChanger;
    private BuildingContainer buildingContainer;

    public CountryController() {
        this.dayChanger = new DayChanger();
        dayChanger.addObserver(this);
        cashOfCountry = 1000;
        market = SingletonMarket.getInstance();
        buildingContainer = new BuildingContainer(this);
    }

    private void makeDailyOperation() {
        System.out.println("country.CountryController.makeDailyTasks()");
        System.out.println(dayChanger.getCounterOfDays());
        buildingContainer.makeDailyOperation();
    }

    public double getCashOfCountry() {
        return cashOfCountry;
    }

    public void run() {
        dayChanger.runOneDay();
    }

    public void runDays(int Int) {
        for (int i = 0; i <= Int; i++) {
            run();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        makeDailyOperation();
    }
}
