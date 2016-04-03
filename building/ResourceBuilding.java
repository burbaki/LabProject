package building;

import boxClasses.Weight;
import enumerationClasses.TypeBuilding;
import java.util.LinkedList;
import java.util.List;

public class ResourceBuilding {

        private static int count = 0;
	private Stock stock;
	private List<Instrument> instruments = new LinkedList<>();
	private TypeBuilding outputProduction;
	private Weight BasicProductionPowerPerDay;
	private int idBuilding;
	private FinanceManager financeManager;

	public int getIdBuilding() {
		return this.idBuilding;
	}

	public ResourceBuilding(TypeBuilding type) {
		// TODO - implement ResourceBuilding.ResourceBuilding
		throw new UnsupportedOperationException();
	}

	public void makeProduction() {
		// TODO - implement ResourceBuilding.makeProduction
		throw new UnsupportedOperationException();
	}

	public void deployInstrument(Instrument instrument) {
		// TODO - implement ResourceBuilding.deployInstrument
		throw new UnsupportedOperationException();
	}  
}