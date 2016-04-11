package building;

import country.DayChanger;
import enumerationClasses.EnumConverter;
import enumerationClasses.Level;
import enumerationClasses.TypeBuilding;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import service.BuildingProperty;

public abstract class ResourceBuilding implements Observer {

    private static int count = 0;
    protected Stock stock;
    private List<Instrument> instruments;
    private double BasicProductionPowerPerDay;
    private int idBuilding;
    protected double currentProductionPerDay;
    private FinanceManager financeManager;
    private int lvl;
    TypeBuilding typeBuilding;
    private DayChanger dayChanger;

    public ResourceBuilding(TypeBuilding type, DayChanger dayChanger) {
        this.dayChanger = dayChanger;
        dayChanger.addObserver(this);
        BasicProductionPowerPerDay = BuildingProperty.getBasicProductionPowerPerDay(type);
        stock = new Stock();
        financeManager = new FinanceManager(stock, EnumConverter.BuildingsToProduction(typeBuilding));
        lvl = 1;
        instruments = new LinkedList<>();
        idBuilding = count++;
    }

    public int getIdBuilding() {
        return this.idBuilding;
    }

    public void makeProduction() {

    }

    public void deployInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    public double getMoneyBalance() {

        return financeManager.getMoneyBalance();
    }

    public int getLevel() {
        return lvl;
    }

    void upgrade() {
        financeManager.giveMoney(BuildingProperty.getLvlUpCost(lvl));
        lvl++;
    }

    public void update(Observable o, Object arg) {
        makeProduction();
        // i dont use observer in financeManager class. because i dont sure that production
        // produse early that finance manager began his work.
        financeManager.makeDailyOperation();
        readyForDestroy();
    }

    boolean readyForDestroy() {
        return financeManager.getMoneyBalance() <= 0;
    }
}
