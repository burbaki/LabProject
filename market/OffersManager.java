package market;

import static country.CountryController.dayChanger;
import country.DayChanger;
import enumerationClasses.TypeProduction;
// participle end
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OffersManager implements Observer {

    private List<Offer> listOfOffer;
    private PriceList price;
    FinancialOperationController financialOperationController;
    DayChanger dayChanger;

    OffersManager(FinancialOperationController financialOperationController, DayChanger o) {
        this.financialOperationController = financialOperationController;
        price = new PriceList(dayChanger, listOfOffer);
        this.dayChanger = dayChanger;
        dayChanger.addObserver(this);

    }
//незнаю надо ли он ??
    private void removeOffer(int id) {
        for (Offer o : listOfOffer) {
            if (o.getID() == id) {
                listOfOffer.remove(o);
            }
        }
    }

    public void addOffer(ProductPack pack, int IDTraderSeller) {
        financialOperationController.pickUpProductionFromTrader(pack);
        Offer newOffer = new Offer(pack, IDTraderSeller, price.getPriceForOneTonn(pack.getTypeProduction()) * pack.getWeight());
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

    public void updatePrices() {
        for (Offer o : listOfOffer) {
            TypeProduction type = o.getTypeProduction();
            o.setPrice(price.getPriceForOneTonn(type) * o.getWeight());
        }
    }

    @Override
    public void update(Observable o, Object arg) {  
        price.updatePrice();
        updatePrices();
      
    }

}
