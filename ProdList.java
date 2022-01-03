/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.util.Collection;
import javax.swing.JPanel;

/**
 * Interface to create a LinkedList adaptation
 * @author Joshua sohan
 */
public interface ProdList<E> {
    
    void  add(E e);
    void remove(E e);
    E getItem(int i);
    int size();
    boolean isEmpty();
    void removeAll();
    void addAll(Collection <? extends E> c);
}
