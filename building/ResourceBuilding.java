package building;

import country.DayChanger;
import country.InstrumentDistributer;
import enumerationClasses.TypeBuilding;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import market.ITrader;
import service.BuildingProperty;

public abstract class ResourceBuilding implements Observer {

    private static Logger log = Logger.getLogger(ResourceBuilding.class.getName());
    private final double salaryPerDay;
    private static int count = 0;
    protected Stock stock;
    private List<Instrument> instruments;
    private double BasicProductionPowerPerDay;
    private int idBuilding;
    protected double currentProductionPerDay;
    private int lvl;
    protected ITrader trader;
    protected TypeBuilding typeBuilding;
    private DayChanger dayChanger;

    public ResourceBuilding(TypeBuilding type) {
        this.dayChanger = country.CountryController.dayChanger;
        dayChanger.addObserver(this);
        typeBuilding = type;
        BasicProductionPowerPerDay = BuildingProperty.getBasicProductionPowerPerDay(type);
        stock = new Stock();
        lvl = 1;
        instruments = new LinkedList<>();
        idBuilding = count++;
        salaryPerDay = BuildingProperty.getSalary(type);
    }

    public int getIdBuilding() {
        return this.idBuilding;
    }

    public void makeProduction() {

    }

    private void checkInstrumentForDeploy() {
        if (InstrumentDistributer.getInstance().isInstrumentAvailable()) {
            Instrument instrument
                    = InstrumentDistributer.getInstance().giveSuitableInstrument(typeBuilding,trader.getIDTrader());
            if (instrument != null && instruments.size() <= 3) {
                deployInstrument(instrument);
                log.log(Level.INFO, "Building {0}, deployed {1} instrument",
                        new Object[]{toString(), instrument.toString()});
            }
        }
    }

    public void deployInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    public int getLevel() {
        return lvl;
    }

    public double getMoneyBalance() {
        return trader.getMoneyBalance();
    }

    void upgrade() {
        trader.giveMoney(BuildingProperty.getLvlUpCost(lvl));
        lvl++;
    }

    private double calculateCurrentProductionPerDay() {
        double productionPower = BasicProductionPowerPerDay;

        for (Instrument i : instruments) {
            productionPower *= i.getProductionInfluence();
        }
        return productionPower;
    }

    private void checkInstrumentForDestroy() {
        for (Instrument i : instruments) {
            if ("BROKEN".equals(i.getStatus())) {
                instruments.remove(i);
                log.log(Level.INFO, "Building {0} destroy {1} instrument",
                        new Object[]{toString(), i.toString()});
            }
        }
    }

    public void update(Observable o, Object arg) {
        checkInstrumentForDestroy();
        checkInstrumentForDeploy();
        currentProductionPerDay = calculateCurrentProductionPerDay();
        makeProduction();
        trader.giveMoney(salaryPerDay);
        log.log(Level.INFO, "spend {0} for pay workers", salaryPerDay);
        // i dont use observer in financeManager class. because i dont sure that production
        // produse early that finance manager began his work.        
        readyForDestroy();
    }

    boolean readyForDestroy() {
        if (trader.getMoneyBalance() <= 10) {
            log.log(Level.INFO, "Building {0}, ready for destroy", toString());
            return true;
        }
        return false;
    }

    public void unsubscribe() {
        dayChanger.deleteObserver(this);
    }

    public String toString() {
        return "id: " + idBuilding + " type: " + typeBuilding;
    }

}
