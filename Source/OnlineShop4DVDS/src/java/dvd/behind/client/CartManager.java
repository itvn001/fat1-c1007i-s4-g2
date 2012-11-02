/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.OrderManager;
import dvd.business.client.UsersManager;
import dvd.entity.Album;
import dvd.entity.Users;
import dvd.libraries.MapperData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class CartManager {

    /**
     * Creates a new instance of cartManager
     */
    private String pageOld;
    private int DataStoreId;
    private List<Album> listDataStore;
    private String message;
    private boolean displayMessage;
    private boolean typeMessage;
  
    public CartManager() {
        if (this.listDataStore == null) {
            this.listDataStore = new ArrayList<Album>();
        }
    }

    public void addToCart(int _DataSotreId) {
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _DataSotreId};
            List<Album> stores = data.getDataWithProc("getAlbumById", "?", paramvalues, Album.class);
            boolean checkExist = false;
            if (stores != null) {
                for (Album als : stores) {
                    for (Album al : getListDataStore()) {
                        if (al.getAlbumID() == als.getAlbumID()) {
                            checkExist = true;
                            al.setQuantity(al.getQuantity() + 1);
                            break;
                        }
                    }
                    if (checkExist == false) {
                        als.setQuantity(1);
                        listDataStore.add(als);
                    }
                }
            }
            this.displayMessage = true;
            this.typeMessage = true;
            setMessage("Add product to cart success!");
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.pageOld);
        } catch (IOException ex) {
            this.displayMessage = true;
            this.typeMessage = false;
            setMessage("Add product to cart false!");
            Logger.getLogger(CartManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String editCart() {
        message = "Update success!";
        displayMessage = true;
        typeMessage = true;
        return "DefaultCart.xhtml?face-redirect=true";
    }

    public double totalMoney() {
        double totalMoney = 0;
        try {
            for (Album album : listDataStore) {
                totalMoney += (album.getQuantity() * Double.parseDouble(album.getAlbumPrice()));
            }
        } catch (Exception e) {
        }
        return totalMoney;
    }

    public void removeCart(int _AlbumId) {
        try {
            for (int i = 0; i < listDataStore.size(); i++) {
                if (listDataStore.get(i).getAlbumID() == _AlbumId) {
                    listDataStore.remove(i);
                    break;
                }
            }
            this.displayMessage = true;
            this.typeMessage = true;
            setMessage("Delete product from cart success!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("DefaultCart.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CartManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int cartSize() {
        return listDataStore.size();
    }

    public String ordering(int _UserId, String _FullName, String _Address) {
        try {
            OrderManager om = new OrderManager();
            UsersManager um = new UsersManager();
            int UserId = 0;
            for (Users us : um.loadInforFromData(_UserId)) {
                UserId = us.getUserID();
                break;
            }
            if (om.ordering(UserId, _FullName, _Address)) {
                for (Album album : listDataStore) {
                    om.orderingDetail(om.returnLastOrderId(), album.getAlbumID(), Double.parseDouble(album.getAlbumPrice()), album.getQuantity());
                }
            } else {
                MessageManager mm = new MessageManager();
                mm.setDisplayMessage(true);
                mm.setMessage("Ordering process error!");
                mm.setTypeMessage(false);
                return "ClientPrevieBills.xhtml?face-redirect=true";
            }
            MessageManager mm = new MessageManager();
            mm.setDisplayMessage(true);
            mm.setMessage("Ordering process success!");
            mm.setTypeMessage(true);
            this.listDataStore = new ArrayList<Album>();
            return "HistoryOrders.xhtml?face-redirect=true";
        } catch (Exception e) {
            MessageManager mm = new MessageManager();
            mm.setDisplayMessage(true);
            mm.setMessage("Ordering process error!");
            mm.setTypeMessage(false);
            return "ClientPrevieBills.xhtml?face-redirect=true";
        }
    }

    /**
     * @param pageOld the pageOld to set
     */
    public void valuePageOld(String pageOld, int _id) {
        this.pageOld = pageOld + _id;
    }

    /**
     * @return the DataStoreId
     */
    public int getDataStoreId() {
        return DataStoreId;
    }

    /**
     * @param DataStoreId the DataStoreId to set
     */
    public void setDataStoreId(int DataStoreId) {
        this.DataStoreId = DataStoreId;
    }

    public void messageEmpty() {
        this.setMessage("");
        this.displayMessage = false;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the displayMessage
     */
    public boolean isDisplayMessage() {
        return displayMessage;
    }

    /**
     * @param displayMessage the displayMessage to set
     */
    public void setDisplayMessage(boolean displayMessage) {
        this.displayMessage = displayMessage;
    }

    /**
     * @return the typeMessage
     */
    public boolean isTypeMessage() {
        return typeMessage;
    }

    /**
     * @param typeMessage the typeMessage to set
     */
    public void setTypeMessage(boolean typeMessage) {
        this.typeMessage = typeMessage;
    }

    /**
     * @return the listDataStore
     */
    public List<Album> getListDataStore() {
        return listDataStore;
    }

    /**
     * @param listDataStore the listDataStore to set
     */
    public void setListDataStore(List<Album> listDataStore) {
        this.listDataStore = listDataStore;
    }
}
