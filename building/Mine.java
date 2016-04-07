package building;

import enumerationClasses.TypeBuilding;

public class Mine extends ResourceBuilding {

    private double amountOfDeposits;

    public Mine(TypeBuilding type) {
        super(type);
    }

    public double getAmountOfDeposit() {
        return amountOfDeposits;
    }

    public void Harvest() {
        // TODO - implement Mine.Harvest
        throw new UnsupportedOperationException();
    }

}
