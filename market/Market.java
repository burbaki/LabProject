package market;


import static country.CountryController.dayChanger;
import enumerationClasses.TypeProduction;
import java.util.EnumMap;
import java.util.List;

public class Market {

    private FinancialOperationController financialOperationController;
    private Double totalBalance;
    private TraderManager traderManager;
    private OffersManager offersManager;

    public Market() {
        financialOperationController = new FinancialOperationController(offersManager);
        traderManager = new TraderManager( getListOfOffers(), this);
        offersManager = new OffersManager(financialOperationController, dayChanger);
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public List<Offer> getListOfOffers() {
        return offersManager.getListOfOffer();
    }
   
    public void makeDailyOperation() 
    {
        
    }


    public void applay(ProductPack pack, int IDTraderSeller) {
        offersManager.addOffer(pack, IDTraderSeller);
    }

    public void pickUpOffer(int IDOffer, int IDTraderBuyer ) {
        offersManager.makeOffer(IDOffer, IDTraderBuyer);
    }

}
