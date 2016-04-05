package boxClasses;

public class Money {

    // существует интерфейс компарабле. реализуй клас используя его .
    private double money;

    /**
     *
     * @param Double
     */
    public Money(double value) {
         this.money = value;
        
    }

    public Double getMoney() {
        // TODO - implement Money.getMoney
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Double
     */
    /**
     *
     * @param Money
     */
    public Money addMoney(Money money) {
      this.money += money.money; 
      return this; 
    }

    /**
     *
     * @param Money
     */
    public Money subMoney(Money money) {
        this.money -= money.money; 
      return this; 
    }

    public String toString() {
        // TODO - implement Money.toString
        throw new UnsupportedOperationException();
    }

    // функция называется примерно так. общие требования при первом меньшем -1 при первом большем +1  при равности 0
    public int Compare(Money money)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    
public Double toDouble()
{
return this.money;
}
    public void setMoney(Money money) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
