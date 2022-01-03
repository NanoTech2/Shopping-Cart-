/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.util.LinkedList;

/**
 * Interface that helps create new user types
 * @author Joshua Sohan
 */
public interface User {
    
    void Add(Product val);
    void Add(DiscountProduct val);
    void Add(BundleProduct val);
    void Remove(Product val);
    void Remove(DiscountProduct val);
    void Remove(BundleProduct val);
    PDList<Product> ProdList();
    PDList<DiscountProduct> DisList();
    PDList<BundleProduct> BunList();
    int num();
    void SetName(String val);
    void SetPassword(String val);
    void SetUsername(String val);
    String GetName();
    String GetPassword();
    String GetUsername();

}
