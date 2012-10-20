/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

import dvd.entity.UserAdmin;
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
public class Permission {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    public Permission() {
    }

    public void checkPermissionDisc() {
        CheckPermission("disc");
    }

    public void checkPermissionOrders() {
        CheckPermission("orders");
    }

    public void CheckPermission(String namePerssion) {
        //Convert Session object to UserAdmin object
        List<dvd.entity.UserAdmin> listfs = (List<dvd.entity.UserAdmin>) session.getAttribute("AccountInfo");
        if (listfs != null) {
            try {
                String _namePermission = "";
                Boolean havePermission = true;
                for (UserAdmin userAdmin : listfs) {
                    _namePermission = userAdmin.getPerName();
                }
                if (_namePermission.trim().equals("admin")) {
                    // If permission is admin then skip 
                } else {
                    if (_namePermission.trim().equals(namePerssion)) {
                    } else {
                        havePermission = false;
                    }
                }
                FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.google.com/");
                if (havePermission == false) {
                } else {
                }
            } catch (IOException ex) {
                Logger.getLogger(Permission.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }
    }
}
