package building;

import country.DayChanger;
import java.util.Observable;
import java.util.Observer;

public class LifeBar implements Observer {

    private DayChanger dayChanger;
    private int health;

    public LifeBar() {
        this.dayChanger = DayChanger.getInstance();
        dayChanger.addObserver(this);
    }

    private void unsubscribe() {
        dayChanger.deleteObserver(this);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int hp) {
        health = hp;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (health <= 0) {
            unsubscribe();
        }
        health--;
    }

}
