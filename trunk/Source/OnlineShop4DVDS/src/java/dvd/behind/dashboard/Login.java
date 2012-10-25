/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.UserAdmin;
import dvd.libraries.Encryption;
import java.io.IOException;
import java.util.List;
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
public class Login {

    private dvd.business.dashboard.UserAdmin userHand;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String password;

    public Login() {
        this.userHand = null;
        this.username = "admin";
        this.password = "admin";
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    public String btnlogin_Click() {
        this.userHand = new UserAdmin();
        dvd.libraries.Encryption en = new Encryption();
        String passEncrypt = en.Encript_Pass(this.password);
        List<dvd.entity.UserAdmin> listLogin = this.userHand.login(this.username, passEncrypt);
        if (listLogin == null) {
            return "#";
        } else {
            for (dvd.entity.UserAdmin userAdmin : listLogin) {
                session.setAttribute("AccountInfo", listLogin);
                session.setAttribute("Permission", userAdmin.getPerName());
            }
            return "success";
        }
    }
}
