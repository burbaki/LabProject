/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import country.DayChanger;
import country.InstrumentDistributer;
import enumerationClasses.TypeInstrument;
import enumerationClasses.TypeProduction;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
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
class InstrumentFactoryTrader implements ITrader, Observer {

    private static Logger log = Logger.getLogger(InstrumentFactoryTrader.class.getName());
    private Stock stock;
    private int IDTrader;
    private List<Offer> listOfOffers;
    private IWallet wallet;
    private boolean isBankrut;
    private List<TypeProduction> requiredResourse;
    private TypeInstrument typeInstrument;
    private List<Instrument> instrumentForSelles;
    private Market market;
    private final DayChanger dayChanger;

    public InstrumentFactoryTrader(Stock stock, TypeInstrument typeInstrument,
            List<TypeProduction> requiredResourse, List<Instrument> instruments) {
        this.stock = stock;
        this.typeInstrument = typeInstrument;
        this.requiredResourse = requiredResourse;
        instrumentForSelles = instruments;
        market.registerTrader(this);
        this.dayChanger =  DayChanger.getInstance();;
        dayChanger.addObserver(this);
    }

    @Override
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
        log.log(Level.INFO, " Trader {0} receive list of offers", IDTrader);
    }

    @Override
    public void setID(int i) {
        IDTrader = i;
    }

    @Override
    public void makeDailyOperation() {
        int IDProductionForBuy = findApropriateOffer();
        if (IDProductionForBuy != -1) {
            market.pickUpOffer(IDProductionForBuy, IDTrader);
        }
        sellsInstrument();
        log.log(Level.INFO, " Trader {0} maked daily operation", IDTrader);
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
        log.log(Level.INFO, "Trader {0} is bankrut", toString());
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
        InstrumentDistributer instrumentDistributer = InstrumentDistributer.getInstance();
        if (!instrumentForSelles.isEmpty()) {
            Instrument inst = findInstrumentForTrade();
            instrumentDistributer.applay(inst, this);
            log.log(Level.INFO, " trader {0} selles instrument: {1}", new Object[]{IDTrader, inst.toString()});
        }

    }

    private void unsubscribe() {
        dayChanger.deleteObserver(this);
    }

    @Override
    public String toString() {
        String answer = new String();
        return answer + "Created buildingTrader with id: " + IDTrader + ", selles: " + typeInstrument;
    }

    @Override
    public void update(Observable o, Object arg) {
        makeDailyOperation();
    }

    public int getIDTrader() {
        return IDTrader;
    }

    public double getWeightResourseOnSctock(TypeProduction type) {
        return stock.GetAmountOfProduct(type);
    }
}
