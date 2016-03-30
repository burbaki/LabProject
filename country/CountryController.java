package country;

import boxClasses.*;
import market.Market;
public class CountryController {

	private Market market;
	private Money cashOfCountrie;
	private DayChanger dayChanger = new DayChanger();
	private BuildingContainer buildingContainer;

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
             
         }
}