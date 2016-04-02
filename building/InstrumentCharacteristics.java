package building;

import enumerationClasses.Level;
import enumerationClasses.TypeInstrument;

public class InstrumentCharacteristics {

    private TypeInstrument typeInstrument;
    private Level level;
    private double productionInfluence;

    public InstrumentCharacteristics(TypeInstrument type, Level lvl) {
        typeInstrument = type;
        level = lvl;
        productionInfluence = InstrumentProperty.getproductionInfluence(type);
    }

    public Level getLevel()
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

}
