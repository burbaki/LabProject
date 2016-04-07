package building;

import enumerationClasses.TypeProduction;
import java.util.EnumMap;

public class Stock {

    private EnumMap<TypeProduction, Double> amountOfProductions;

    public Stock() {
        amountOfProductions = new EnumMap<>(TypeProduction.class);
    }

    //do not know whether it should be
    public double GetAmountOfProduct(TypeProduction type) {
        return amountOfProductions.get(type);
    }

    public void setAmountOfProduct(TypeProduction type, double weight) {

    }

    public String toString() {
        return amountOfProductions.toString();
    }

    public void takeProduct(TypeProduction type, double weight) {
        double wgh = amountOfProductions.get(type);
        wgh += weight;
        amountOfProductions.put(type, wgh);
    }

    public void giveProduct(TypeProduction type, double weight) {
        double wgh = amountOfProductions.get(type);
        wgh -= weight;
        amountOfProductions.put(type, wgh);
    }

}
