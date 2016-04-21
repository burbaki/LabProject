package market;


import country.DayChanger;
import enumerationClasses.TypeProduction;
import java.util.LinkedList;
// participle end
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OffersManager implements Observer {

    private static Logger log = Logger.getLogger(OffersManager.class.getName());
    private List<Offer> listOfOffer;
    private PriceList price;
    FinancialOperationController financialOperationController;
    DayChanger dayChanger;

    OffersManager(FinancialOperationController financialOperationController) {
        listOfOffer = new LinkedList<>();
        price = new PriceList(listOfOffer);
        this.financialOperationController = financialOperationController;
        this.dayChanger =  DayChanger.getInstance();
        dayChanger.addObserver(this);
    }

    public void addOffer(ProductPack pack, int IDTraderSeller) {
        financialOperationController.pickUpProductionFromTrader(pack, IDTraderSeller);
        Offer newOffer = new Offer(pack, IDTraderSeller,
                price.getPriceForOneTonn(pack.getTypeProduction()) * pack.getWeight());
        listOfOffer.add(newOffer);
        log.log(Level.INFO, "Add new offer {0} from {1} trader", new Object[]{newOffer, IDTraderSeller});
    }

    public void makeOffer(int IDOffer, int IDTraderBuyer) {
        for (Offer o : listOfOffer) {
            if (o.getID() == IDOffer) {
                financialOperationController.pickUpMoneyFromTrader(o.getPriceOfPack(), IDTraderBuyer);
                financialOperationController.giveMoneyToTrader(o.getPriceOfPack(), o.getIDSeller());
                financialOperationController.givePackToTrader(o, IDTraderBuyer);
                listOfOffer.remove(o);
                log.log(Level.INFO, "Maked offer {0}. Buyer : {1} trader", new Object[]{o, IDTraderBuyer});
                break;
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
        log.log(Level.INFO, "updated prices");
    }

    @Override
    public void update(Observable o, Object arg) {
        price.updatePrice();
        log.log(Level.INFO, "pricelist was updated");
        updatePrices();
    }

}
