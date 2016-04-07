package building;

import static country.CountryController.dayChanger;
import country.DayChanger;
import enumerationClasses.TypeInstrument;
import java.util.Observable;
import java.util.Observer;
import service.InstrumentProperty;

public class StatusOfInstrument implements Observer {

    enum Status {
        BROKEN, READY, CREATING
    }
    private DayChanger dayChanger;
    private LifeBar lifeBar;
    int CreatingDayLeft;
    TypeInstrument type;
    Status status;

    String getStatus() {
        String answer = new String();
        if (status == Status.BROKEN) {
            answer = status.toString();
        } else if (status == Status.READY) {
            answer = status.toString() + "," + lifeBar.getHealth() + "health";
        } else if (status == Status.CREATING) {
            answer = status.toString() + "," + CreatingDayLeft + "remain";
        }
        return answer;
    }

    public StatusOfInstrument(TypeInstrument type, DayChanger dayChanger) {
        this.dayChanger = dayChanger;
        dayChanger.addObserver(this);
        status = Status.CREATING;
        CreatingDayLeft = InstrumentProperty.getQuantityDaysOfBuilding();
        this.type = type;
    }

    private void createLifeBar() {
        lifeBar = new LifeBar(dayChanger);
        lifeBar.setHealth(InstrumentProperty.getHealth(this.type));
    }

    public void finishCreate() {
        createLifeBar();
        status = Status.READY;
        dayChanger.deleteObserver(this);
    }

    public void update(Observable o, Object arg) {
        if (CreatingDayLeft <= 0) {
            finishCreate();
        }
        CreatingDayLeft--;
    }
}
