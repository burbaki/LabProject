package country;

import boxClasses.*;
import market.Market;
public class CountryController {

	private Market market = new Market();
	private Money cashOfCountrie = new Money(1000);
	private final DayChanger dayChanger;
	private BuildingContainer buildingContainer = new BuildingContainer();

    public CountryController() {
        this.dayChanger = new DayChanger();
        
    }

	private void makeDailyTasks() {
		          System.out.println("country.CountryController.makeDailyTasks()");
                          System.out.println(dayChanger.getCounterOfDays());
	}
        
         
         
         public void run()
         {            
             dayChanger.runOneDay();
             makeDailyTasks();
         }
         
         public void runDays(int Int)
         {
             for(int i = 0; i <= Int; i++)
                 run();
         }
}