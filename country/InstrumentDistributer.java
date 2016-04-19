/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

import building.Instrument;
import enumerationClasses.TypeBuilding;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import market.ITrader;
import market.IWallet;


public class InstrumentDistributer {

    List<Instrument> list;
    IWallet wallet;
    private static InstrumentDistributer instance;

    private InstrumentDistributer() {
        list = new ArrayList<>();
    }

    public static InstrumentDistributer getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public InstrumentDistributer(IWallet wallet) {
        if (instance == null) {
            instance = new InstrumentDistributer();
            instance.wallet = wallet;
        }
    }

    public void applay(Instrument instrument, ITrader trader) {
        trader.takeMoney(instrument.getPrice());
        list.add(instrument);
    }

    public boolean isInstrumentAvailable()
    {
        return !list.isEmpty();
    }
            
    public Instrument giveSuitableInstrument(TypeBuilding typeBuilding, int IDTrader) {
            list.get(0);
            return list.remove(0);
    }

}
