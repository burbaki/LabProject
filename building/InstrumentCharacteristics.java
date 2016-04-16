package building;

import enumerationClasses.Level;
import enumerationClasses.TypeInstrument;
import service.InstrumentProperty;

public class InstrumentCharacteristics {

    private TypeInstrument typeInstrument;
    private int level;
    private double productionInfluence;

    public InstrumentCharacteristics(TypeInstrument type, int lvl) {
        typeInstrument = type;
        assert(lvl <=5);
        level = lvl;
        productionInfluence = InstrumentProperty.getproductionInfluence(type);
    }

    public int getLevel()
    {
        return level;
    }
    public TypeInstrument getTypeInstrument()
    {
        return typeInstrument;
    }
    public double getProductionInfluence() {
        return productionInfluence;
    }

    double getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
