package market;

import boxClasses.*;
import static country.CountryController.dayChanger;
import enumerationClasses.TypeProduction;
import java.util.EnumMap;
import java.util.List;

public class Market {

    private FinancialOperationController financialOperationController;
    private Money totalBalance;
    private TraderManager traderManager;
    private OffersManager offersManager;

    public Market() {
        financialOperationController = new FinancialOperationController(offersManager);
        traderManager = new TraderManager();
        offersManager = new OffersManager(financialOperationController, dayChanger);
    }

    public Money getTotalBalance() {
        return totalBalance;
    }

    public List<Offer> getListOfOffers() {
        return offersManager.getListOfOffer();
    }

    public void makeDailyOperation() {
        // TODO - implement Market.makeDailyOperation
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ProductPack
     */
    public void applay(ProductPack pack, int IDTraderSeller) {
        offersManager.addOffer(pack, IDTraderSeller);
    }

    public void pickUpOffer(int IDOffer) {
        // TODO - implement Market.pickUpOffer
        throw new UnsupportedOperationException();
    }

}
