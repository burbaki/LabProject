package building;

import country.DayChanger;
import enumerationClasses.Level;
import enumerationClasses.TypeBuilding;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import service.BuildingProperty;

public abstract class ResourceBuilding implements Observer{

    private static int count = 0;
    protected Stock stock;
    private List<Instrument> instruments;
    private double BasicProductionPowerPerDay;
    private int idBuilding;
    protected double currentProductionPerDay;
    private FinanceManager financeManager;
    private int lvl;
    private final DayChanger dayChanger;

    public ResourceBuilding( DayChanger dayChanger)  {
        this.dayChanger = dayChanger;
        dayChanger.addObserver(this);
        BasicProductionPowerPerDay = BuildingProperty.getBasicProductionPowerPerDay(type);
        
        financeManager = new FinanceManager();
        stock = new Stock();
        lvl = 1;
        instruments = new LinkedList<>();
        idBuilding = count++;

    }

    public int getIdBuilding() {
        return this.idBuilding;
    }

    public void makeProduction() {
        stock.takeProduct(currentProductionPerDay);
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
        lvl++;
    }
    
     public void update(Observable o, Object arg) {
        makeProduction();
    }
}
