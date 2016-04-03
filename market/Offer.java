package market;

import boxClasses.Money;
import boxClasses.Weight;
import enumerationClasses.TypeProduction;

public class Offer extends ProductPack {

    static int countOfOffers = 0;
    int idOffer;
    private Money value;
    private final int seller;
    private int buyer;

    public Offer(Weight weight, TypeProduction type, int idSeller) {
        super(weight, type);
        this.seller = idSeller;
        idOffer = countOfOffers++;
    }

    public Offer(ProductPack pack, int idSeller, Money value) {
        super(pack.getWeight(), pack.getTypeProduction());
        this.seller = idSeller;
        this.value = value;
        idOffer = countOfOffers++;
    }

    public void setBuyer(int idBuyer) {
        buyer = idBuyer;
    }

    public void setPrice(Money value) {
        this.value = value;
    }

    public int getID() {
        return idOffer;
    }

    public int getIDSeller() {
        return seller;
    }

    public int getIDBuyer() {
        return buyer;
    }

}
