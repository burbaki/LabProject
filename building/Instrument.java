package building;

import static country.CountryController.dayChanger;
import enumerationClasses.*;

public class Instrument {

    private StatusOfInstrument status;
    private InstrumentCharacteristics instrumentCharacteristics;

    public Instrument(TypeInstrument type, Level lvl) {
        instrumentCharacteristics = new InstrumentCharacteristics(type, lvl);
        status = new StatusOfInstrument(type, dayChanger);
    }

    public String GetStatus() {
        return status.getStatus();
    }

}
