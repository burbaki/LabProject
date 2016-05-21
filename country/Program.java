/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 *
 * @author Burbaki
 * 
 */
public class Program {
    public static void main(String args[])
    {
        try{
        Handler handler = new FileHandler("log.log", 0,10);
        
        Logger.getLogger("").addHandler(handler);}
        catch(IOException ex)
        {
            ex.getStackTrace();
        }
        CountryController mainCountry = new CountryController();
        System.out.println("go go go");
        mainCountry.runDays(20);
      
     
       
    }
}
