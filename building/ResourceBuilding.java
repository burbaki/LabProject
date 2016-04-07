package building;


import enumerationClasses.Level;
import enumerationClasses.TypeBuilding;
import java.util.LinkedList;
import java.util.List;

public class ResourceBuilding {

    private static int count = 0;
    private Stock stock;
    private List<Instrument> instruments = new LinkedList<>();
    private TypeBuilding outputProduction;
    private double BasicProductionPowerPerDay;
    private int idBuilding;
    private FinanceManager financeManager;
    private Level lvl;

    public int getIdBuilding() {
        return this.idBuilding;
    }

    public ResourceBuilding(TypeBuilding type) {
        
    }

    public void makeProduction() {
        // TODO - implement ResourceBuilding.makeProduction
        throw new UnsupportedOperationException();
    }

    public void deployInstrument(Instrument instrument) {
        // TODO - implement ResourceBuilding.deployInstrument
        throw new UnsupportedOperationException();
    }

    public double getMoneyBalance() {

       return financeManager.getMoneyBalance();
    }
    public Level getLevel()
    {
        return lvl;
    }
}
