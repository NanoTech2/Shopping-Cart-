/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.io.Serializable;
import java.util.LinkedList;
import java.lang.String;

/**
 *Bundle Product that handles a group of products, implements prod interface
 * @author Joshua sohan
 */
public class BundleProduct implements Prod, Serializable{
    
    private String name;
    private double price;
    private String description;
    private int inventory;
    
    private LinkedList<Product> prod = new LinkedList<>();
    private LinkedList<DiscountProduct> discountProd = new LinkedList<>();

    /**
     *Class constructor initializes all variables to null or 0
     */
    public void BundleProduct(){
        name = null;
        price = 0;
        description = "";
        inventory = 0;
    }
    
    /**
     * Adds product type object to the Bundle
     * @param val Product type object you want to add to the bundle
     */
    
    public void AddProd(Product val){
        Product temp = new Product();
        temp.SetDescription(val.GetDescription());
        temp.SetInventory(val.GetInventory());
        temp.SetPrice(price + val.GetPrice());
        temp.SetName(val.Getname());
        prod.add(temp);
        SetDescription("");
    }
    
    
    /**
     * Adds DiscountProduct object type to the Bundle
     * @param val The DiscountProduct you want to add to the bundle
     */
    public void AddProd(DiscountProduct val){
        Product temp = new Product();
        temp.SetDescription(val.GetDescription());
        temp.SetInventory(val.GetInventory());
        temp.SetName(val.Getname()); 
        temp.SetPrice(price + val.GetPrice());
        discountProd.add(val);
        SetDescription("");
    }
    
    
    /**
     * Sets the Bundle's name
     * @param val String you want to set as the Bundles name
     */
    @Override
    public void SetName(String val) {
        name = val;
    }

    /**
     * Sets the Bundle's price
     * @param val Double type of price
     */
    @Override
    public void SetPrice(double val) {
        price = val;
    }

    /**
     * Sets the Bundle's description
     * @param val The string you want to set as the description 
     */
    @Override
    public void SetDescription(String val) {
        String t = "Bundle Of: " + this.GetString();
        description = t;
    }

    
    /**
     * Sets the Bundle's inventory (How many in stocks)
     * @param val  int to set as the inventory
     */
    @Override
    public void SetInventory(int val) {
        inventory = val;
    }

    /**
     * If Price is never specified then it adds up the total prices for each product added
     * @return Returns he Price of Bundle
     */
    
    @Override
    public double GetPrice() {
        double temp = 0;
        if (price == 0 ){
            for (Product i : prod){
                temp = temp + i.GetPrice();
            }
            for (DiscountProduct i: discountProd){
                temp = temp + i.GetPrice();
            }
        }
        else{
            temp = price;
        }
        return temp;
    }

    /**
     * Returns the Bundle's description
     * @return String of the Bundle's description
     */
    @Override
    public String GetDescription() {
       return description;
    }

    /**
     * If custom name is not given then it creates a string of all names in bundle and returns it
     * @return A string of the name of the Bundle
     */
    @Override
    public String Getname() {
        String temp = "";
        if (name == null){
            temp = this.GetString();
        }
        else{
            temp = name;
        }
        return temp;
    }
    
    
    /**
     * Returns the Bundle's inventory
     * @return int of the items inventory
     */
    @Override
    public int GetInventory() {
        int temp = prod.get(0).GetInventory();
        if (inventory == 0){
            for (Product i: prod){
                if(temp > i.GetInventory()){
                    temp = i.GetInventory();
                }
            }
            for (DiscountProduct i: discountProd){
                if(temp > i.GetInventory()){
                    temp = i.GetInventory();
                }
            }
        }
        else
        {
            temp = inventory;
        }
        
        
        return temp;
    }
    
    
    /**
     * Returns a string of all the items inside the bundle
     * @return a string of all the items inside the bundle
     */
    public String GetString(){
        String temp ="";
        
        for (int i = 0; i < prod.size(); i ++){
                if (i == 0){
                    
                temp = temp + prod.get(i).Getname();
                }
                else{
                    temp = temp + ", " + prod.get(i).Getname();
                }
            }
            for (DiscountProduct i: discountProd){
                temp = temp + ", " + i.Getname();
            }
        
        
        return temp;
    }
    

  /**
   * returns a copy of the BundleProduct
   * @return Prodcut objct type that is an exact copy
   */
    public BundleProduct Getcopy() {
        
        BundleProduct t = new BundleProduct();
        for (Product j: prod){
            t.AddProd(j);
        }
        
        for (DiscountProduct j: discountProd){
            t.AddProd(j);
        }
        
        
        t.SetDescription(this.GetDescription());
        t.SetInventory(this.GetInventory());
        t.SetName(this.Getname());
        t.SetPrice(this.GetPrice());

        return t;
    }
    
}
