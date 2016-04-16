package building;

import country.DayChanger;
import country.SingletonInstrumentDistributer;
import enumerationClasses.TypeBuilding;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import market.ITrader;
import service.BuildingProperty;

public abstract class ResourceBuilding implements Observer {

    private final double salaryPerDay;
    private static int count = 0;
    protected Stock stock;
    private List<Instrument> instruments;
    private double BasicProductionPowerPerDay;
    private int idBuilding;
    protected double currentProductionPerDay;
    private int lvl;
    protected ITrader trader;
    TypeBuilding typeBuilding;
    private DayChanger dayChanger;

    public ResourceBuilding(TypeBuilding type, DayChanger dayChanger) {
        this.dayChanger = dayChanger;
        dayChanger.addObserver(this);
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
        Instrument instrument;
        instrument = SingletonInstrumentDistributer.getInstance().giveSuitableInstrument(typeBuilding);
        if(instrument != null && instruments.size() <= 3)
        {
            deployInstrument(instrument);
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
            }
        }
    }

    public void update(Observable o, Object arg) {
        checkInstrumentForDestroy();
        checkInstrumentForDeploy();
        currentProductionPerDay = calculateCurrentProductionPerDay();
        makeProduction();
        trader.giveMoney(salaryPerDay);
        // i dont use observer in financeManager class. because i dont sure that production
        // produse early that finance manager began his work.
        trader.makeDailyOperation();
        readyForDestroy();
    }

    boolean readyForDestroy() {
        return trader.getMoneyBalance() <= 0;
    }
    public String toString()
    {
        return "id: " + idBuilding + " type: " + typeBuilding;
    }

}
