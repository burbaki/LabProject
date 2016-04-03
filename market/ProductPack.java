package market;

import boxClasses.Weight;
import enumerationClasses.TypeProduction;

public class ProductPack {

    private final Weight weight;
    private final TypeProduction type;

    public ProductPack(Weight wgh, TypeProduction type) {
        weight = wgh;
        this.type = type;
    }

    public Weight getWeight() {
        return weight;
    }

    public TypeProduction getTypeProduction() {
        return type;
    }

    public String getString() {
        String answer = new String();
        return answer + weight.toString() + " " + type.toString();

    }

}
