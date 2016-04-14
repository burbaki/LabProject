/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import enumerationClasses.TypeInstrument;

import java.util.List;
import java.util.Map;
import market.ITrader;
import market.IWallet;
import market.Market;
import market.Offer;
import market.ProductPack;

/**
 *
 * @author Burbaki
 */
class InstrumentTrader implements ITrader {

    private Stock stock;
    private int IDTrader;
    private List<Offer> listOfOffers;
    private IWallet wallet;
    private boolean isBankrut;

    InstrumentTrader(Stock stock, TypeInstrument typeInstrument, Map<Integer, List<ProductPack>> requiredProduction, Market instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void takeMoney(double money) {
        wallet.takeMoney(money);
    }

    public void giveMoney(double money) {
        wallet.giveMoney(money);
    }

    public double getMoneyBalance() {
        return wallet.getMoneyBalance();
    }

    @Override
    public void receiveList(List<Offer> listOfOffers) {
        this.listOfOffers = listOfOffers;
    }

    @Override
    public void setID(int i) {
        IDTrader = i;
    }

    @Override
    public void takeProductPack(ProductPack pack) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void giveProductPack(ProductPack pack) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void makeDailyOperation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void setBankrut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTraderBankrut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
