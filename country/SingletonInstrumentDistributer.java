/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

/**
 *
 * @author Burbaki
 */
public class SingletonInstrumentDistributer {

    private static InstrumentDistributer instance;

    private SingletonInstrumentDistributer() {
    }

    public static InstrumentDistributer getInstance() {
        if (instance == null) {
            instance = new InstrumentDistributer();
        }
        return instance;
    }
}
