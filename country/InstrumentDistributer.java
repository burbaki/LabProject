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


public class InstrumentDistributer {
List<Instrument> list;

public InstrumentDistributer()
{
    list = new LinkedList<>();
}
    public void applay(Instrument instrument) {
        list.add(instrument);
    }

    public Instrument giveSuitableInstrument(TypeBuilding typeBuilding) {
        return list.remove(0);
    }
    
}
