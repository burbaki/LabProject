package market;

import static country.CountryController.dayChanger;

import java.util.List;

public class OffersManager {

    private List<Offer> listOfOffer;
    private PriceList price;
    FinancialOperationController financialOperationController;

    OffersManager(FinancialOperationController financialOperationController) {
        this.financialOperationController = financialOperationController;
        price = new PriceList(dayChanger, listOfOffer);

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
        Offer newOffer = new Offer(pack, IDTraderBuyer, price.getPriceForOneTonn(pack.getTypeProduction()) * pack.getWeight());
        listOfOffer.add(newOffer);
    }

    public void makeOffer(int IDOffer, int IDTraderBuyer) {
        for (Offer o : listOfOffer) {
            if (o.getID() == IDOffer) {
                financialOperationController.giveMoneyToTrader(o.getIDBuyer());
                financialOperationController.givePackToTrader(o, IDTraderBuyer);
                listOfOffer.remove(o);
            }
        }
    }

    public List<Offer> getListOfOffer() {
        return listOfOffer;
    }

}
