package country;

import market.Market;

public class CountryController {

    private Market market;
    private double cashOfCountry;
    public static DayChanger dayChanger;
    private BuildingContainer buildingContainer;

    public CountryController() {
        this.dayChanger = new DayChanger();
        cashOfCountry = 1000;
        market = new Market();
        buildingContainer = new BuildingContainer(this);
    }

    private void makeDailyTasks() {
        System.out.println("country.CountryController.makeDailyTasks()");
        System.out.println(dayChanger.getCounterOfDays());
    }

    public double getCashOfCountry() {
        return cashOfCountry;
    }

    public void run() {
        dayChanger.runOneDay();
        makeDailyTasks();
    }

    public void runDays(int Int) {
        for (int i = 0; i <= Int; i++) {
            run();
        }
    }
}
