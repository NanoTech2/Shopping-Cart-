/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

/**
 *Product interface that helps create new product types
 * @author Joshua Sohan
 */
public interface Prod {
    
    public void SetName(String val);
    public void SetPrice(double val);
    public void SetDescription(String val);
    public void SetInventory(int val);
    public double GetPrice();
    public String GetDescription();
    public String Getname();
    public int GetInventory();
}
