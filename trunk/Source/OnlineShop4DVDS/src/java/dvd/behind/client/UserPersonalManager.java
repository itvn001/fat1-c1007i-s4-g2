/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.UsersManager;
import dvd.entity.Users;
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
public class UserPersonalManager {

    /**
     * Creates a new instance of UserPersonalManager
     */
    private int UserId;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public UserPersonalManager() {
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

    public Users showInforUser() {
        Users users = null;
        try {
            UsersManager um = new UsersManager();
            users = um.showInforFromData(UserId);
        } catch (Exception e) {
        }
        return users;
    }
}
