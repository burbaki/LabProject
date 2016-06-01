package building;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Burbaki
 */
public class NoMoneyException extends Exception {

    private double money;

    public NoMoneyException(String mes, double m) {
        super(mes);
        money = m;
    }
}
