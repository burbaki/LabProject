package building;

import static country.CountryController.dayChanger;
import enumerationClasses.*;

public class Instrument {

    private StatusOfInstrument status;
    private InstrumentCharacteristics instrumentCharacteristics;

    public Instrument(TypeInstrument type, int lvl) {
        instrumentCharacteristics = new InstrumentCharacteristics(type, lvl);
        status = new StatusOfInstrument(type, dayChanger);
    }

    public double getProductionInfluence()
    {
        return instrumentCharacteristics.getProductionInfluence();
    }
    public String GetStatus() {
        return status.getStatus();
    }

     public int getLvl() {
       return instrumentCharacteristics.getLevel();
    }

}
