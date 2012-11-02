/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.CollectionManager;
import dvd.business.client.DataStoreManager;
import dvd.entity.CollectionCate;
import dvd.entity.DataStore;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class DetailsPlaylistManager {

    private static String namePlaylist;
    private static String datePlaylist;
    private static int playListId;
    private static int UserId;
    private static String message = "";
    private static boolean displayMessage = false;
    private static boolean typeMessage = true;
    private List<DataStore> listDS = null;
    private int SNNumber = 0;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    /**
     * Creates a new instance of DetailsPlaylistManager
     */
    public DetailsPlaylistManager() {
        try {
            if (session.getAttribute("UserId") == null || session.getAttribute("UserId") == "") {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                UserId = Integer.parseInt("" + session.getAttribute("UserId"));
            }
        } catch (Exception e) {
        }
    }

    public int getSNNumber() {
        SNNumber++;
        return SNNumber;
    }
    
    public void resetMessage(){
        message = "";
        displayMessage = false;
    }
    
    public void listDataStoreCCateId() {
        try {
            CollectionManager cm = new CollectionManager();
            listDS = cm.listDataStoreByPlaylistId(playListId);
        } catch (Exception e) {
        }
    }

    public void addIdPlaylist(int _PlaylistId) {
        if (_PlaylistId != 0) {
            playListId = _PlaylistId;
            CollectionManager cm = new CollectionManager();
            List<CollectionCate> listCC = cm.listCollectionCateById(_PlaylistId, UserId);
            for (CollectionCate collectionCate : listCC) {
                setNamePlaylist(collectionCate.getCollectCateName());
                setDatePlaylist("Create date: " + collectionCate.getDateCreate());
            }
        }
    }

    public void deleteDataStoreInPlaylist(int _PlaylistId) {
        try {
            DataStoreManager dsm = new DataStoreManager();
            if (dsm.deleteDataStoreInPlaylist(playListId, _PlaylistId)) {
                message = "Delete item in playlist success!";
                displayMessage = true;
                typeMessage = true;
            } else {
                message = "Delete item in playlist false!";
                displayMessage = true;
                typeMessage = false;
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("DetailsPlaylistPage.xhtml?PlayListId=" + playListId);
        } catch (Exception e) {
        }
    }

    /**
     * @return the listDS
     */
    public List<DataStore> getListDS() {
        this.listDataStoreCCateId();
        return listDS;
    }

    /**
     * @param listDS the listDS to set
     */
    public void setListDS(List<DataStore> listDS) {
        this.listDS = listDS;
    }

    /**
     * @return the namePlaylist
     */
    public static String returnNamePlaylist() {
        return namePlaylist;
    }

    /**
     * @param aNamePlaylist the namePlaylist to set
     */
    public static void setNamePlaylist(String aNamePlaylist) {
        namePlaylist = aNamePlaylist;
    }

    /**
     * @return the datePlaylist
     */
    public static String returnDatePlaylist() {
        return datePlaylist;
    }

    /**
     * @param aDatePlaylist the datePlaylist to set
     */
    public static void setDatePlaylist(String aDatePlaylist) {
        datePlaylist = aDatePlaylist;
    }

    /**
     * @return the message
     */
    public static String showMessage() {
        return message;
    }

    /**
     * @return the displayMessage
     */
    public static boolean showDisplayMessage() {
        return displayMessage;
    }

    /**
     * @return the typeMessage
     */
    public static boolean showTypeMessage() {
        return typeMessage;
    }
}
