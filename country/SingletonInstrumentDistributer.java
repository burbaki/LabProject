/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

import building.Instrument;
import enumerationClasses.TypeBuilding;
import java.util.LinkedList;
import java.util.List;
import market.ITrader;
import market.IWallet;
import market.TraderWallet;

public class SingletonInstrumentDistributer {

    List<Instrument> list;
    IWallet wallet;
    private static SingletonInstrumentDistributer instance;

    private SingletonInstrumentDistributer() {
    }

    public static SingletonInstrumentDistributer getInstance() {
        if (instance != null) {
            return instance;
        }        
        return null;
    }

    public SingletonInstrumentDistributer(IWallet wallet) {
        if (instance == null) {
            instance = new SingletonInstrumentDistributer();
            instance.wallet = wallet;
        }
    }

    public void applay(Instrument instrument, ITrader trader) {
        trader.takeMoney(instrument.getPrice());
        list.add(instrument);
    }

    public Instrument giveSuitableInstrument(TypeBuilding typeBuilding) {
        return list.remove(0);
    }

}
