/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.entity.DataStore;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class CartManager {

    /**
     * Creates a new instance of CartManager
     */
    private List<DataStore> listCart;
    public CartManager() {
        if(listCart == null){
            listCart = new ArrayList<DataStore>();
        }
    }

    
    
    /**
     * @return the listCart
     */
    public List<DataStore> getListCart() {
        return listCart;
    }

    /**
     * @param listCart the listCart to set
     */
    public void setListCart(List<DataStore> listCart) {
        this.listCart = listCart;
    }
}
