package boxClasses;

public class Weight {

    
	private double weight;

        
        
	public Weight(double value) {
            this.weight = value;
        }

	public double getWeight() {
             return this.weight;
	}


	public Weight addWeight(Weight weight) {
            this.weight += weight.weight; 
             return this;
	}


	public Weight subWeight(Weight weight) {
	    this.weight -= weight.weight; 
             return this;
	}


}