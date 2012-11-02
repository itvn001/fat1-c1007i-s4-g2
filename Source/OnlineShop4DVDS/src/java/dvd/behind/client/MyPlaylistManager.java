/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.CollectionManager;
import dvd.entity.CollectionCate;
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
public class MyPlaylistManager {

    /**
     * Creates a new instance of MyPlaylistManager
     */
    private int UserId;
    private int SNNumber = 0;
    static private String message;
    static private boolean displayMessage;
    static private boolean typeMessage;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    
    public MyPlaylistManager() {
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

    public void resetMessage() {
        displayMessage = false;
    }

    public List<CollectionCate> showCollectionCateById() {
        CollectionManager cm = new CollectionManager();
        List<CollectionCate> listCC = null;
        try {
            listCC = cm.listCollectionCate(UserId);
        } catch (Exception e) {
        }
        return listCC;
    }

    public String deleteCollectionCate(int _CCId, String _Name) {
        try {
            CollectionManager clm = new CollectionManager();
            boolean result = clm.deleteCollectionCate(_CCId);
            if (result) {
                message = "Delete playlist: " + _Name + " success!";
                displayMessage = true;
                typeMessage = true;
            } else {
                message = "Delete playlist: " + _Name + " false!";
                displayMessage = true;
                typeMessage = false;
            }
        } catch (Exception e) {
        }
        return "MyPlaylistPage.xhtml";
    }

    /**
     * @return the SNNumber
     */
    public int getSNNumber() {
        SNNumber++;
        return SNNumber;
    }

    /**
     * @param SNNumber the SNNumber to set
     */
    public void setSNNumber(int SNNumber) {
        this.SNNumber = SNNumber;
    }

    /**
     * @return the message
     */
    public static String getMessage() {
        return message;
    }

    /**
     * @param aMessage the message to set
     */
    public static void setMessage(String aMessage) {
        message = aMessage;
    }

    /**
     * @return the displayMessage
     */
    public static boolean isDisplayMessage() {
        return displayMessage;
    }

    /**
     * @param aDisplayMessage the displayMessage to set
     */
    public static void setDisplayMessage(boolean aDisplayMessage) {
        displayMessage = aDisplayMessage;
    }

    /**
     * @return the typeMessage
     */
    public static boolean isTypeMessage() {
        return typeMessage;
    }

    /**
     * @param aTypeMessage the typeMessage to set
     */
    public static void setTypeMessage(boolean aTypeMessage) {
        typeMessage = aTypeMessage;
    }
}
