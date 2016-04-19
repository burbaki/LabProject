package market;

import enumerationClasses.TypeProduction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Offer extends ProductPack {

    private static Logger log = Logger.getLogger(Offer.class.getName());
    static int countOfOffers = 0;
    int idOffer;
    private double value;
    private final int seller;
    private int buyer;


    public Offer(ProductPack pack, int idSeller, double value) {
        super(pack.getWeight(), pack.getTypeProduction());
        this.seller = idSeller;
        this.value = value;
        idOffer = countOfOffers++;
    }

    public void setBuyer(int idBuyer) {
        buyer = idBuyer;
    }

    public void setPrice(double value) {
        this.value = value;
        //log.log(Level.INFO, " new price was setting {0}", toString());
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

    public double getPriceOfPack() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString() + "price: " + value;
    }

}
