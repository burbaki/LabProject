package building;

import enumerationClasses.TypeProduction;
import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stock {

    private EnumMap<TypeProduction, Double> amountOfProductions;
    private static Logger log = Logger.getLogger(Stock.class.getName());

    public Stock() {
        amountOfProductions = new EnumMap<>(TypeProduction.class);
        for (TypeProduction type : TypeProduction.values()) {
            double weight = 0;
            amountOfProductions.put(type, weight);
        }
    }

    //do not know whether it should be
    public double GetAmountOfProduct(TypeProduction type) {
        return amountOfProductions.get(type);
    }

    public String toString() {
        return amountOfProductions.toString();
    }

    public void takeProduct(TypeProduction type, double weight) {
        double wgh = amountOfProductions.get(type);
        wgh += weight;
        amountOfProductions.put(type, wgh);
        log.log(Level.INFO, "Take product {0} {1} kg", new Object[]{type, wgh});
    }

    public void giveProduct(TypeProduction type, double weight) {
        double wgh = amountOfProductions.get(type);
        wgh -= weight;
        amountOfProductions.put(type, wgh);
        log.log(Level.INFO, "Give product {0} {1} kg", new Object[]{type, wgh});
    }

}
