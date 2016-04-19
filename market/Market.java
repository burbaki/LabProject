package market;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Market {

    private static Logger log = Logger.getLogger(Market.class.getName());
    private FinancialOperationController financialOperationController;
    private Double totalBalance;
    private TraderManager traderManager;
    private OffersManager offersManager;
    private static Market instance = new Market();

    public static Market getInstance() {
        return instance;
    }

    public Market() {
        financialOperationController = new FinancialOperationController();
        log.log(Level.INFO, "Financial operation controler was created");
        offersManager = new OffersManager(financialOperationController);
        log.log(Level.INFO, "Offers manager was created");
        traderManager = new TraderManager(getListOfOffers());       
        financialOperationController.receiveListOfTraders(getListOfTraders());
         log.log(Level.INFO, "Trader Manager was created");
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    private List<Offer> getListOfOffers() {
        return offersManager.getListOfOffer();
    }

    private List<ITrader> getListOfTraders() {
        return traderManager.getListOfTraders();
    }

    public void registerTrader(ITrader trader) {
        traderManager.addTrader(trader);
        financialOperationController.receiveListOfTraders(getListOfTraders());

    }

    public void applay(ProductPack pack, int IDTraderSeller) {
        offersManager.addOffer(pack, IDTraderSeller);     
        traderManager.sendListToAllTraders();
    }

    public void pickUpOffer(int IDOffer, int IDTraderBuyer) {
        offersManager.makeOffer(IDOffer, IDTraderBuyer);       
        traderManager.sendListToAllTraders();
    }

}
