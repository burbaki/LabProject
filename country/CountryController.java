package country;

import boxClasses.*;
import market.Market;
public class CountryController {

	private Market market = new Market();
	private  Money cashOfCountry = new Money(1000);
	public static DayChanger dayChanger;
	private BuildingContainer buildingContainer = new BuildingContainer(this);

    public CountryController() {
        this.dayChanger = new DayChanger();
        
    }

    
	private void makeDailyTasks() {
		          System.out.println("country.CountryController.makeDailyTasks()");
                          System.out.println(dayChanger.getCounterOfDays());
	}
        
         
         public Money getCashOfCountry()
         {
             return cashOfCountry;                     
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