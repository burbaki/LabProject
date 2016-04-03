package market;

import boxClasses.Money;
import enumerationClasses.TypeProduction;
import java.util.EnumMap;

public class PriceList {

    private int dayFromBegin;
    private EnumMap<TypeProduction, Money> priesList;

    public Money getPriceForOneTonn(TypeProduction type) {
        return priesList.get(type);
    }

}
