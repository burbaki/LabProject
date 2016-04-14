/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import java.util.List;

public interface ITrader {

    public void takeProductPack(ProductPack pack);

    public void giveProductPack(ProductPack pack);

    public void takeMoney(double money);

    public void giveMoney(double money);

    public void receiveList(List<Offer> listOfOffers);

    public void setID(int i);

    public void makeDailyOperation();

    public double getMoneyBalance();

    public void setBankrut();

    public boolean isTraderBankrut();
}
