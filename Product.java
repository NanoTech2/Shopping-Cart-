/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.io.Serializable;

/**
 * Default product type that, implements prod
 * @author Joshua Sohan
 */
public class Product implements Prod, Serializable{
    
    
    private String name;
    private double price;
    private String description;
    private int inventory;

    public Product(){
        name =null;
        price = 0;
        description = null;
        inventory  = 0;
    }
    
    /**
     * Sets the name
     * @param val String that becomes the name
     */
    @Override
    public void SetName(String val) {
        name = val;
    }
    
    
    /**
     * Sets the price
     * @param val double that becomes the price
     */
    @Override
    public void SetPrice(double val) {
        price = val;
    
    }

    
    /**
     * Sets the Description
     * @param val String the becomes the description
     */
    @Override
    public void SetDescription(String val) {
        description = val;
    }

    
    /**
     * Sets the inventory
     * @param val Int that becomes the inventory
     */
    @Override
    public void SetInventory(int val) {
        inventory = val;
    }

    
    /**
     * Gets the price
     * @return double of the items price price
     */
    @Override
    public double GetPrice() {
        return price;
     
    }

    
    /**
     * Gets the description
     * @return String of the items description
     */
    @Override
    public String GetDescription() {
        return description;
    }

    
    /**
     * Gets the name
     * @return String of items name
     */
    @Override
    public String Getname(){
        return name;
    }
    
    
    /**
     * Gets inventory
     * @return int of the items inventory
     */
    @Override
    public int GetInventory() {
        return inventory;
    }

 
    /**
     * returns copy of the product
     * @return Prodcut type that is a copy of itself
     */
    public Product Getcopy() {
        Product n = new Product();
        n.SetPrice(price);
        n.SetDescription(description);
        n.SetName(name);
        n.SetInventory(inventory);
        
        return n;
    }
    


}
