package building;



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