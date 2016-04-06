package market;

import boxClasses.Money;
import boxClasses.Weight;
import country.DayChanger;
import enumerationClasses.TypeProduction;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import service.ResourseProperties;

public class PriceList implements Observer {

    private int dayFromBegin;
    private EnumMap<TypeProduction, Money> priesList;
    private DayChanger dayChanger;
    private List<Offer> Offers;

    public PriceList(DayChanger dayChanger, List<Offer> Offers) {
        this.dayChanger = dayChanger;
        dayChanger.addObserver(this);
        dayFromBegin = 0;
        priesList = new EnumMap<>(TypeProduction.class);
        updatePrice();
    }

    public Money getPriceForOneTonn(TypeProduction type) {
        return priesList.get(type);
    }
// разбить на несколько методов

    private void updatePrice() {
        EnumMap<TypeProduction, Weight> sumOfOffers = new EnumMap<>(TypeProduction.class);
        Weight allWeightOfMarket = new Weight();
        for (Offer o : Offers) {
            TypeProduction type = o.getTypeProduction();
            Weight currentWeight = o.getWeight();
            allWeightOfMarket.addWeight(currentWeight);
            currentWeight.add(sumOfOffers.get(type));
            sumOfOffers.put(type, currentWeight);
        }
        Set<TypeProduction> allType = sumOfOffers.keySet();
        EnumMap<TypeProduction, Double> allRatio = new EnumMap<>(TypeProduction.class);
        for (TypeProduction type : allType) {
            double ratio = allWeightOfMarket.div(sumOfOffers.get(type));
            allRatio.put(type, ratio);
        }
        for(TypeProduction type : allType)
        {
            Money newprice = ResourseProperties.getBasicValue(type).div(allRatio.get(type));            
            priesList.put(type,newprice);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
