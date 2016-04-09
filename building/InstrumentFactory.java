package building;



import country.DayChanger;
import enumerationClasses.TypeBuilding;
import java.util.List;

import market.ProductPack;

public class InstrumentFactory extends Factory {

	private Instrument CreatingInstrument;
	private Instrument[] readyInstruments;
	private List<ProductPack> requiredProduction;

    public InstrumentFactory(TypeBuilding type) {
        super(type);
    }

    InstrumentFactory(TypeBuilding type, DayChanger dayChanger) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	/**
	 * 
	 * @param TypeInstrument
	 * @param LevelOfOnstrument
	 */
	public void makeInstrument(int TypeInstrument, int LevelOfOnstrument) {
		// TODO - implement InstrumentFactory.makeInstrument
		throw new UnsupportedOperationException();
	}

}