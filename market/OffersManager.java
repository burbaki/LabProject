package market;

import country.DayChanger;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OffersManager implements Observer {

    private List<Offer> listOfOffer;
    private PriceList price;
    FinancialOperationController financialOperationController;

    OffersManager(FinancialOperationController financialOperationController, DayChanger dayChanger) {
        this.financialOperationController = financialOperationController;
    }

    private void removeOffer(int id) {
        for (Offer o : listOfOffer) {
            if (o.getID() == id) {
                listOfOffer.remove(o);
            }
        }
    }

    public void addOffer(ProductPack pack, int IDTraderBuyer) {
        financialOperationController.pickUpProductionFromTrader(pack);
        Offer newOffer = new Offer(pack, IDTraderBuyer, price.getPriceForOneTonn(pack.getTypeProduction()));
        listOfOffer.add(newOffer);
    }

    /**
     *
     * @param Int
     */
    public void makeOffer(int IDOffer, int IDTraderBuyer) {
        for (Offer o : listOfOffer) {
            if (o.getID() == IDOffer) {
                financialOperationController.giveMoneyToTrader(o.getIDBuyer());
                financialOperationController.givePackToTrader(o, IDTraderBuyer);
                listOfOffer.remove(o);
            }
        }

    }

    public PriceList InstallPrice() {
        // TODO - implement OffersManager.InstallPrice
        throw new UnsupportedOperationException();
    }

    public List<Offer> getListOfOffer() {
        return listOfOffer;
    }

    @Override
    public void update(Observable o, Object arg) {
        

}
