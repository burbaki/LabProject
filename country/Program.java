/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

/**
 *
 * @author Burbaki
 * 
 */
public class Program {
    public static void main(String args[])
    {
        CountryController mainCountry = new CountryController();
        System.out.println("go go go");
        mainCountry.runDays(10);
        System.out.print("1 days passed");
     
       
    }
}
