/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import enumerationClasses.Level;
import enumerationClasses.TypeBuilding;

/**
 *
 * @author Burbaki
 */
public class BuildingProperty {

    //Разаработать методы и классы для управления XML документами
    public static double getCostOfBuilding(TypeBuilding type) {
        return 200;
    }

    public static double getLvlUpCost(int level) {
        return 200 + 20 * level;
    }

    public static double getBasicProductionPowerPerDay(TypeBuilding type) {
        if (type == TypeBuilding.STEEL || type == TypeBuilding.COOPERORE) {
            return 2;
        }else if (type == TypeBuilding.BOARDS)
            return 1;
        return 5;
    }

    public static double getAmountOfDeposit() {
        return 10000;
    }

    public static double getSalary(TypeBuilding type) {
        return 20;
    }

}
