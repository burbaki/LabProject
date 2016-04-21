package country;

import enumerationClasses.TypeBuilding;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import market.IWallet;
import market.Market;
import market.TraderWallet;

public class CountryController implements Observer {

    InstrumentDistributer instrumentDistributer;
    private static Logger log = Logger.getLogger(CountryController.class.getName());
    private IWallet wallet;
    private BuildingContainer buildingContainer;
    private final DayChanger dayChanger;

    public CountryController() {

        this.dayChanger = DayChanger.getInstance();
        dayChanger.addObserver(this);
        wallet = new TraderWallet(100000);
        instrumentDistributer = new InstrumentDistributer(wallet);
        buildingContainer = new BuildingContainer(this);
        createBasicBuilding();
    }

    private void makeDailyOperation() {
        System.out.println("country.CountryController.makeDailyTasks()");
        buildingContainer.makeDailyOperation();
    }

    public double getCashOfCountry() {
        return wallet.getMoneyBalance();
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
        buildingContainer.buildBuilding(TypeBuilding.IRONORE);
        buildingContainer.buildBuilding(TypeBuilding.COOPER);
        buildingContainer.buildBuilding(TypeBuilding.COOPERORE);
        buildingContainer.buildBuilding(TypeBuilding.WOOD);
        buildingContainer.buildBuilding(TypeBuilding.BOARDS);
        buildingContainer.buildBuilding(TypeBuilding.SILICON);
        buildingContainer.buildBuilding(TypeBuilding.STEEL);
        log.info("Create basic buildings \n");
    }

    void giveMoney(double cost) {
        wallet.giveMoney(cost);
    }
}
