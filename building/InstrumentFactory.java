package building;

import country.DayChanger;
import country.SingletonMarket;
import enumerationClasses.EnumConverter;
import enumerationClasses.TypeBuilding;
import enumerationClasses.TypeInstrument;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import market.IWallet;

import market.ProductPack;
import service.InstrumentProperty;

public class InstrumentFactory extends Factory implements IWallet {

    private TypeInstrument typeInstrument;
    private List<Instrument> readyInstruments;
    private List<Instrument> inProductionInstruments;
    private Map<Integer, List<ProductPack>> requiredProduction;

    public InstrumentFactory(TypeBuilding type, DayChanger dayChanger) {
        super(type, dayChanger);
        this.typeInstrument = EnumConverter.BuildingsToInstrument(typeBuilding);
        requiredProduction = InstrumentProperty.getRequiredProduction(typeInstrument);
        readyInstruments = new LinkedList<>();
        trader = new InstrumentTrader(stock, typeInstrument, requiredProduction, SingletonMarket.getInstance());

    }

    public List<Instrument> getListOfReadyInstrument() {
        return readyInstruments;
    }

    public void makeInstrument(int LevelOfOnstrument) {
        Instrument newInst = new Instrument(typeInstrument, LevelOfOnstrument);
        inProductionInstruments.add(newInst);
    }

    private void transferReadyInstrument() {
        Pattern p = Pattern.compile("CREATE.+");
        for (Instrument i : inProductionInstruments) {
            Matcher m = p.matcher(i.getStatus());
            if (!m.matches()) {
                readyInstruments.add(i);
                inProductionInstruments.remove(i);
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

    public Instrument giveInstrument(int minLvl) {
        if (!readyInstruments.isEmpty()) {
            for (int i = minLvl; minLvl >= 1; i--) {
                for (Instrument ins : readyInstruments) {
                    if (ins.getLvl() >= i) {
                        readyInstruments.remove(ins);
                        return ins;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void takeMoney(double money) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void giveMoney(double money) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
