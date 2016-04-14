package country;

import market.Market;

public class SingletonMarket {

    private static Market instance;

    private SingletonMarket() {
    }

    public static Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
    }
}
