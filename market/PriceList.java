package market;

import enumerationClasses.TypeProduction;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.EnumMap;
import java.util.List;

import java.util.Set;
import service.ResourceProperties;

public class PriceList {

    private int dayFromBegin;
    private EnumMap<TypeProduction, Double> priesList;
    private final List<Offer> offers;

    public PriceList(List<Offer> offers) {

        dayFromBegin = 0;
        priesList = new EnumMap<>(TypeProduction.class);
        this.offers = offers;

        // priesList.forEach((k, v) -> v =  ResourceProperties.getBasicValue(k) );
        for (TypeProduction type : TypeProduction.values()) {
            double newprice = ResourceProperties.getBasicValue(type);
            priesList.put(type, newprice);
        }
    }

    public Double getPriceForOneTonn(TypeProduction type) {
        return priesList.get(type);
    }

    public void updatePrice() {
        
        dayFromBegin++;
        EnumMap<TypeProduction, Double> sumOfOffers = new EnumMap<>(TypeProduction.class);
        for (TypeProduction type : TypeProduction.values()) {
            double initialWeight = 0;
            sumOfOffers.put(type, initialWeight);
        }
        double allWeightOfMarket = 0;
        for (Offer o : offers) {
            TypeProduction type = o.getTypeProduction();
            double currentWeight = o.getWeight();
            allWeightOfMarket += currentWeight;
            currentWeight += sumOfOffers.get(type);
            sumOfOffers.put(type, currentWeight);
        }
        EnumMap<TypeProduction, Double> allRatio = new EnumMap<>(TypeProduction.class);
        //allRatio.forEach((k, v) -> v = sumOfOffers.get(k) / all);
        for (TypeProduction type : TypeProduction.values()) {
            double ratio = sumOfOffers.get(type) / allWeightOfMarket;
            allRatio.put(type, ratio);
        }
        //priesList.forEach((k, v) -> v = ( ResourceProperties.getBasicValue(k) / allRatio.get(k)));
        for (TypeProduction type : TypeProduction.values()) {
            double newprice = ResourceProperties.getBasicValue(type) / allRatio.get(type);
            priesList.put(type, newprice);
        }

        try (PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("list.txt", true)));) {
            savesPrices(out);
            out.println(sumOfOffers);
        } catch (Exception e) {}
    }

    private void savesPrices(PrintStream out) {
        out.println("day: " + dayFromBegin);
        out.println(priesList);
    }
}
