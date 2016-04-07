package boxClasses;

public class Money {

    private double money;

    public Money(double value) {
        this.money = value;
    }

    public Money add(Money money) {
        this.money += money.money;
        return this;
    }

    public Money subMoney(Money money) {
        this.money -= money.money;
        return this;
    }

    public Double toDouble() {
        return this.money;
    }

    public Money div(Double get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Money multi(Double toDouble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
