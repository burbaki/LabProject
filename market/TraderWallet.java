/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

/**
 *
 * @author Burbaki
 */
public class TraderWallet implements IWallet {

    private double moneyBalance;

    public TraderWallet() {
        moneyBalance = 100;
    }

    @Override
    public void takeMoney(double money) {
        moneyBalance += money;
    }

    @Override
    public void giveMoney(double money) {
        moneyBalance -= money;
    }

    @Override
    public double getBalance() {
        return moneyBalance;

    }

}
