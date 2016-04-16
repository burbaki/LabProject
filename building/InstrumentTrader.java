/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import country.InstrumentDistributer;
import country.SingletonInstrumentDistributer;
import enumerationClasses.TypeInstrument;
import enumerationClasses.TypeProduction;

import java.util.List;
import java.util.logging.Logger;

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

    private static Logger log = Logger.getLogger(InstrumentTrader.class.getName());
    private Stock stock;
    private int IDTrader;
    private List<Offer> listOfOffers;
    private IWallet wallet;
    private boolean isBankrut;
    List<TypeProduction> requiredResourse;
    TypeInstrument typeInstrument;
    List<Instrument> instrumentForSelles;
    Market market;

    InstrumentTrader(Stock stock, TypeInstrument typeInstrument,
            List<TypeProduction> requiredProduction, List<Instrument> instruments) {
        this.stock = stock;
        this.typeInstrument = typeInstrument;
        requiredProduction = requiredProduction;
        instrumentForSelles = instruments;
        market.registerTrader(this);
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

    public void receiveList(List<Offer> listOfOffers) {
        this.listOfOffers = listOfOffers;
    }

    public void setID(int i) {
        IDTrader = i;
    }

    public void makeDailyOperation() {
        int IDProductionForBuy = findApropriateOffer();
        if (IDProductionForBuy != -1) {
            market.pickUpOffer(IDProductionForBuy, IDTrader);
        }
        sellsInstrument();
    }

    public int findApropriateOffer() {
        double min = stock.GetAmountOfProduct(requiredResourse.get(0));
        if (isNeedResourse()) {
            TypeProduction mintype = requiredResourse.get(0);
            for (TypeProduction type : requiredResourse) {

                if (min > stock.GetAmountOfProduct(type)) {
                    mintype = type;
                }
            }
            for (Offer o : listOfOffers) {
                if (o.getTypeProduction() == mintype) {
                    market.pickUpOffer(o.getID(), IDTrader);
                    return o.getID();
                }
            }
        }
        return -1;
    }

    private boolean isNeedResourse() {
        for (TypeProduction type : requiredResourse) {
            // it is test variant
            if (stock.GetAmountOfProduct(type) < 5) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setBankrut() {
        isBankrut = true;
    }

    @Override
    public boolean isTraderBankrut() {
        return isBankrut;
    }

    @Override
    public void takeProductPack(ProductPack pack) {
        TypeProduction type = pack.getTypeProduction();
        double weight = pack.getWeight();
        stock.takeProduct(type, weight);
    }

    @Override
    public void giveProductPack(ProductPack pack) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Instrument findInstrumentForTrade() {
        int index = (int) (Math.random() % instrumentForSelles.size());
        return instrumentForSelles.get(index);
    }

    private void sellsInstrument() {
        InstrumentDistributer instrumentDistributer = SingletonInstrumentDistributer.getInstance();
        instrumentDistributer.applay(findInstrumentForTrade());
    }

}
