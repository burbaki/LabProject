package country;

import enumerationClasses.TypeBuilding;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import market.Market;

public class CountryController implements Observer {

    private static Logger log = Logger.getLogger(CountryController.class.getName());
    private double cashOfCountry;
    public static DayChanger dayChanger;
    private BuildingContainer buildingContainer;

    public CountryController() {
       
        this.dayChanger = new DayChanger();
        dayChanger.addObserver(this);
        cashOfCountry = 1000;
        buildingContainer = new BuildingContainer(this);
        createBasicBuilding();
    }

    private void makeDailyOperation() {
        System.out.println("country.CountryController.makeDailyTasks()");
        buildingContainer.makeDailyOperation();
    }

    public double getCashOfCountry() {
        return cashOfCountry;
    }

    public void run() {
        dayChanger.runOneDay();
    }

    public void runDays(int day) {
        dayChanger.runDays(day);
    }

    @Override
    public void update(Observable o, Object arg) {
        makeDailyOperation();
    }

    private void createBasicBuilding() {
        buildingContainer.buildBuilding(TypeBuilding.WATER);
        buildingContainer.buildBuilding(TypeBuilding.COIL);
        buildingContainer.buildBuilding(TypeBuilding.STEEL);
        log.info("Create basic buildings \n");
    }

    void giveMoney(double costOfBuilding) {
       cashOfCountry -= costOfBuilding;
    }
}
