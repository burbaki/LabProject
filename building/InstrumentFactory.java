package building;


import boxClasses.Weight;
import java.util.List;

import market.ProductPack;

public class InstrumentFactory extends Factory {

	private Instrument CreatingInstrument;
	private Instrument[] readyInstruments;
	private List<ProductPack> requiredProduction;

    public InstrumentFactory(int TypeProduction) {
        super(TypeProduction);
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