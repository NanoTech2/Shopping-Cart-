/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.io.Serializable;

/**
 * A User object type that implements User interface and is responsible for Seller account
 * @author Joshua Sohan
 */
public class Seller implements User, Serializable{
    private final PDList<Product> prod;
    private final PDList<DiscountProduct> discount;
    private final PDList<BundleProduct> bundle;
    private int arrayNum;
    private String username;
    private String name;
    private String password;
    
    public Seller(){
        arrayNum = 0;
        prod = new PDList();
        discount = new PDList();
        bundle = new PDList();
    }

    /**
     * Add prodcut to product
     * @param val Prodcut you want to add
     */
    @Override
    public void Add(Product val) {
        prod.add(val);
        arrayNum++;
    }

    /**
     * Add Discount product 
     * @param val DiscountProdcut you wish to add
     */
    @Override
    public void Add(DiscountProduct val) {
        discount.add(val);
        arrayNum++;
    }

    /**
     * Add Bundle Product
     * @param val BundleProduct you wish to add
     */
    @Override
    public void Add(BundleProduct val) {
        bundle.add(val);
        arrayNum++;
    }

    /**
     * Remove Product
     * @param val Product you wish to remove
     */
    @Override
    public void Remove(Product val) {
        prod.remove(val);
        arrayNum--;
    }

    /**
     * removes Discount product
     * @param val DiscountProduct you wish to remove
     */
    @Override
    public void Remove(DiscountProduct val) {
        discount.remove(val);
        arrayNum--;
    }

    /**
     * Removes Bundle Product 
     * @param val BundleProduct you wish to remove
     */
    @Override
    public void Remove(BundleProduct val) {
        bundle.remove(val);
        arrayNum--;
    }

    
    /**
     * returns the number of total products
     * @return int of total products
     */
    @Override
    public int num() {
        return arrayNum;
    }    

    
    /**
     * returns the list of of products
     * @return PDList of products
     */
    @Override
    public PDList<Product> ProdList() {
        return (PDList<Product>) prod;
    }

    /**
     * returns the list of DiscountProducts
     * @return PDList of discount products
     */
    @Override
    public PDList<DiscountProduct> DisList() {
        return (PDList<DiscountProduct>) discount;
    }

    /**
     * returns the list of BundleProducts
     * @return PDList type object
     */
    @Override
    public PDList<BundleProduct> BunList() {
        return (PDList<BundleProduct>) bundle;
    }

    /**
     * Sets name
     * @param val String to set name too
     */
    @Override
    public void SetName(String val) {
        name = val;
    }

    /**
     * Sets password
     * @param val String to set password to
     */
    @Override
    public void SetPassword(String val) {
        password = val;

    }

    /**
     * Sets Username
     * @param val String to set username too
     */
    @Override
    public void SetUsername(String val) {
        username = val;
    }

    /**
     * Gets name
     * @return String of name
     */
    @Override
    public String GetName() {
        
        return name;
    }

    /**
     * Gets password
     * @return String of password
     */
    @Override
    public String GetPassword() {
        return password;
    }

    /**
     * Gets Usename
     * @return String of username
     */
    @Override
    public String GetUsername() {
        return username;
    }
}
