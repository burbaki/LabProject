package boxClasses;

public class Weight {

    private double weight;

    public Weight(double value) {
        this.weight = value;
    }

    public Weight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getWeight() {
        return this.weight;
    }

    public Weight add(Weight wh) {
        this.weight += wh.weight;
        return this;
    }

    public Weight subWeight(Weight weight) {
        this.weight -= weight.weight;
        return this;
    }

    public Double toDouble() {
        return this.weight;
    }  

    public double div(Weight wh) {
       return this.weight / wh.weight;
    }

}
