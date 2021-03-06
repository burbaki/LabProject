/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import enumerationClasses.TypeProduction;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Burbaki
 */
public class ResourceProperties {

    public static double getBasicValue(TypeProduction type) {
        double price = 1;
        if (type == TypeProduction.WATER) {
            price = 2;
        } else if (type == TypeProduction.COIL) {
            price = 4;
        } else if (type == TypeProduction.IRONORE) {
            price = 6;
        } else if (type == TypeProduction.STEEL) {
            price = 8;
        } else if (type == TypeProduction.COOPER) {
            price = 9;
        } else if (type == TypeProduction.COOPERORE) {
            price = 7;
        } else if (type == TypeProduction.SILICON) {
            price = 10;
        } else if (type == TypeProduction.WOOD) {
            price = 3;
        } else if (type == TypeProduction.BOARDS) {
            price = 4;
        }
        return price;
    }

    public static List<TypeProduction> getRequiredProduction(TypeProduction typeProduction) {
        List<TypeProduction> list = new LinkedList<>();
        if (typeProduction == TypeProduction.STEEL) {
            list.add(TypeProduction.COIL);
            list.add(TypeProduction.IRONORE);
            list.add(TypeProduction.WATER);
        } else if (typeProduction == TypeProduction.BOARDS) {
            list.add(typeProduction.WOOD);
        } else if (typeProduction == TypeProduction.COOPER) {
            list.add(TypeProduction.COIL);
            list.add(TypeProduction.COOPERORE);
            list.add(TypeProduction.WATER);
        }
        return list;
    }
}
