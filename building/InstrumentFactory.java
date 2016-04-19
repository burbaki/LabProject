package building;

import country.DayChanger;

import enumerationClasses.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import market.ProductPack;
import service.InstrumentProperty;

public class InstrumentFactory extends Factory {

    private static Logger log = Logger.getLogger(FactoryTrader.class.getName());
    private TypeInstrument typeInstrument;
    private List<Instrument> readyInstruments;
    private List<Instrument> inProductionInstruments;
    InstrumentFactoryTrader trader;
    //Integer in Map it is lvl of instrument
    private Map<Integer, List<ProductPack>> requiredProduction;

    public InstrumentFactory(TypeBuilding type) {
        super(type);
        this.typeInstrument = EnumConverter.BuildingsToInstrument(typeBuilding);
        requiredProduction = InstrumentProperty.getRequiredProduction(typeInstrument);
        readyInstruments = new LinkedList<>();
        trader = new InstrumentFactoryTrader(stock, typeInstrument, getRequiredTypeProductions(), readyInstruments);
        log.log(Level.INFO, "Created InstrumetFactoryTrader with {0}", trader.toString());
    }

    public List<Instrument> getListOfReadyInstrument() {
        return readyInstruments;
    }

    public void makeInstrument(int LevelOfOnstrument) {
        Instrument newInst = new Instrument(typeInstrument, LevelOfOnstrument);
        inProductionInstruments.add(newInst);
        log.log(Level.INFO, "begin work with instrument: {0}, {1}", new Object[]{newInst.toString(), newInst.getStatus()});

    }

    private void transferReadyInstrument() {
        Pattern p = Pattern.compile("CREATE.+");
        for (Instrument i : inProductionInstruments) {
            Matcher m = p.matcher(i.getStatus());
            if (!m.matches()) {
                readyInstruments.add(i);
                inProductionInstruments.remove(i);
                log.log(Level.INFO, "End work with: {0} instrument", i.toString());
            }
        }
    }

    @Override
    public void makeProduction() {
        if (checkOportunityToMakeInstrument()) {
            makeInstrument(GetOprtunityLvlOfInstrument());
            transferReadyInstrument();
        }
    }
// check for first lvl

    private boolean checkOportunityToMakeInstrument() {
        List<ProductPack> prod = requiredProduction.get(1);
        for (ProductPack p : prod) {
            if (p.getWeight() > stock.GetAmountOfProduct(p.getTypeProduction())) {
                log.log(Level.INFO, "not enough resources to make {0}", typeInstrument);
                return false;
            }
        }
        return true;

    }

    private int GetOprtunityLvlOfInstrument() {
        for (int i = 1; i <= 5; i++) {
            List<ProductPack> prod = requiredProduction.get(1);
            for (ProductPack p : prod) {
                if (p.getWeight() > stock.GetAmountOfProduct(p.getTypeProduction())) {
                    return i - 1;
                }
            }
        }
        return 5;
    }

    private List<TypeProduction> getRequiredTypeProductions() {
        List<TypeProduction> list = new ArrayList<>();
        for (ProductPack pack : requiredProduction.get(0)) {
            list.add(pack.getTypeProduction());
        }
        return list;
    }
}
