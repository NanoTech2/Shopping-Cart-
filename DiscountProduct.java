/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.io.Serializable;

/**
 * Discount Product that takes a Product and gives back a DiscountProduct type, Implements prod interface
 * @author Joshua Sohan
 */
public class DiscountProduct implements Prod, Serializable {
    
    private Product prod;

    
    /**
     * Class constructor, gets product and the price your wish to change
     * @param val Product
     * @param price Price
     */
    public DiscountProduct(Product val, double price){
        prod = new Product();
        prod.SetDescription(val.GetDescription());
        prod.SetInventory(val.GetInventory());
        prod.SetPrice(price);
        prod.SetName(val.Getname() + "[Discounted]");
    }
    
    public DiscountProduct(Product val){
        prod = new Product();
        prod.SetDescription(val.GetDescription());
        prod.SetInventory(val.GetInventory());
        prod.SetPrice(val.GetPrice());
        prod.SetName(val.Getname());
    }
    
    /**
     * Sets the name
     * @param val String that becomes the name
     */
    @Override
    public void SetName(String val) {
        prod.SetName(val);
    }
    
    /**
     * Sets the price
     * @param val double that becomes the price
     */
    @Override
    public void SetPrice(double val) {
        prod.SetPrice(val);
    }

    /**
     * Sets the Description
     * @param val String the becomes the description
     */
    @Override
    public void SetDescription(String val) {
        prod.SetDescription(val);
    }

    /**
     * Sets the inventory
     * @param val Int that becomes the inventory
     */
    @Override
    public void SetInventory(int val) {
        prod.SetInventory(val);
    }

    /**
     * Gets the price
     * @return double of the items price price
     */
    @Override
    public double GetPrice() {
        return prod.GetPrice();
     
    }

    /**
     * Gets the description
     * @return String of the items description
     */
    @Override
    public String GetDescription() {
        return prod.GetDescription();
    }

    /**
     * Gets the name
     * @return String of items name
     */
    @Override
    public String Getname(){
        return prod.Getname();
    }
    
    /**
     * Gets inventory
     * @return int of the items inventory
     */
    @Override
    public int GetInventory() {
        return prod.GetInventory();
    }  
    
    
    /**
     * Makes a copy of itself
     * @return DiscountProduct that is a copy of itself
     */
    public DiscountProduct Getcopy() {
        DiscountProduct n = new DiscountProduct(prod);        
        return n;
    }
     
}
