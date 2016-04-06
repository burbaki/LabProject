package boxClasses;

public class Money {

    
    private double money;

   
    
    public Money(double value) {
        this.money = value;    
    }

    public Money addMoney(Money money) {
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


}
