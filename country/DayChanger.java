package country;

public class DayChanger {

    private long counterOfDays = 0;
    
    /**
     *
     * @param Int
     */
    public void runDays(int Int) {
        // TODO - implement DayChanger.runDays
        throw new UnsupportedOperationException();
    }

    /*public void interrupt() {
		// TODO - implement DayChanger.interrupt
		throw new UnsupportedOperationException();
	}*/
    public void runOneDay() {
        try {
            Thread.sleep(1000);
            counterOfDays++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public long getCounterOfDays()
    {
        return counterOfDays;
    }

}
