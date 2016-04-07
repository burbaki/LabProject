package market;


import enumerationClasses.TypeProduction;

public class ProductPack {

    private final Double weight;
    private final TypeProduction type;

    public ProductPack(double wgh, TypeProduction type) {
        weight = wgh;
        this.type = type;
    }

    public Double getWeight() {
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
