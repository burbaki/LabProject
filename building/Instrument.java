package building;

import static country.CountryController.dayChanger;
import enumerationClasses.TypeInstrument;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class Instrument {

    private static Logger log = Logger.getLogger(Instrument.class.getName());
    private StatusOfInstrument status;
    private InstrumentCharacteristics instrumentCharacteristics;

    public Instrument(TypeInstrument type, int lvl) {
        instrumentCharacteristics = new InstrumentCharacteristics(type, lvl);
        status = new StatusOfInstrument(type, dayChanger);
        log.log(Level.INFO, "Created isntrument {0} lvl, {1} type", new Object[]{getLvl(), type});
    }

    public double getProductionInfluence() {
        return instrumentCharacteristics.getProductionInfluence();
    }

    public String getStatus() {
        return status.getStatus();
    }

    public int getLvl() {
        return instrumentCharacteristics.getLevel();
    }
    public String toString()
    {
        return ""+ instrumentCharacteristics.getTypeInstrument()
                + " type, "+ instrumentCharacteristics.getLevel() + "lvl";
    }

}
