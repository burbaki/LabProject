package boxClasses;

public class Weight {

	private double weight;

	/**
	 * 
	 * @param Double
	 */
	public Weight(double value) {
           this.weight = value;
           
	}

	public double getWeight() {
		return this.weight;
	}

	/**
	 * 
	 * @param Double
	 */
	public void setWeight(int Double) {
		// TODO - implement Weight.setWeight
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Weight
	 */
	public Weight addWeight(Weight weight) {
                  this.weight += weight.weight; 
                  return this;
	}

	/**
	 * 
	 * @param Weight
	 */
	public Weight subWeight(Weight weight) {
		this.weight -= weight.weight; 
                return this;
	}

	public String toString() {
		// TODO - implement Weight.toString
		throw new UnsupportedOperationException();
	}

}