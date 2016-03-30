package market;

import boxClasses.Money;

public class Offer extends ProductPack {

    // добавить методы для добавления продавца и покупателя. 
	private Money value;
	private Trader Seller;
	private Trader Buyer;

    public Offer(int Weight, int TypeProduction) {
        super(Weight, TypeProduction);
    }

}