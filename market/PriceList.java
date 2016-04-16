package market;

import enumerationClasses.TypeProduction;
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
        updatePrice();
    }

    public Double getPriceForOneTonn(TypeProduction type) {
        return priesList.get(type);
    }


    public void updatePrice() {
        EnumMap<TypeProduction, Double> sumOfOffers = new EnumMap<>(TypeProduction.class);
        double allWeightOfMarket = 0;
        if (!offers.isEmpty()) {
            for (Offer o : offers) {
               TypeProduction type = o.getTypeProduction();
                Double currentWeight = o.getWeight();
                allWeightOfMarket = +currentWeight;
                currentWeight += sumOfOffers.get(type);
                sumOfOffers.put(type, currentWeight);
            }
            Set<TypeProduction> allType = sumOfOffers.keySet();
            EnumMap<TypeProduction, Double> allRatio = new EnumMap<>(TypeProduction.class);
            for (TypeProduction type : allType) {
                double ratio = sumOfOffers.get(type) / allWeightOfMarket;
                allRatio.put(type, ratio);
            }
            for (TypeProduction type : allType) {
                double newprice = ResourceProperties.getBasicValue(type) / allRatio.get(type);
                priesList.put(type, newprice);
            }
        }
    }

}
