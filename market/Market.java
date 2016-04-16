package market;

import java.util.List;

public class Market {

    private FinancialOperationController financialOperationController;
    private Double totalBalance;
    private TraderManager traderManager;
    private OffersManager offersManager;
    private static Market instance = new Market();

    public static Market getInstance() {
        return instance;
    }

    public Market() {
        //financialOperationController = new FinancialOperationController
        // !! conflict !!
        offersManager = new OffersManager(financialOperationController);
        traderManager = new TraderManager(getListOfOffers());
        financialOperationController = new FinancialOperationController(traderManager);
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    private List<Offer> getListOfOffers() {
        return offersManager.getListOfOffer();
    }

    public void registerTrader(ITrader trader) {
        traderManager.addTrader(trader);
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
