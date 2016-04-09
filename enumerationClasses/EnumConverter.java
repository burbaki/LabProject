/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumerationClasses;

/**
 *
 * @author Burbaki
 */
public class EnumConverter {
    public static  TypeProduction BuildingsToProduction(TypeBuilding typeBuilding)
    {
        switch(typeBuilding)
                {
                    case COIL : return TypeProduction.COIL;
    case   WOOD: return TypeProduction.WOOD;
    case   IRONORE: return TypeProduction.IRONORE;
    case   WATER: return TypeProduction.WATER;
    case   COOPERORE: return TypeProduction.COOPERORE;
    case   SILICON: return TypeProduction.SILICON;
    case   COOPER: return TypeProduction.COOPER;
    case   STEEL: return TypeProduction.STEEL;
    case   BOARDS: return TypeProduction.BOARDS;
                }
        return TypeP;
    }
}
