/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.UserAdmin;
import dvd.libraries.Encryption;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class Login {

    private dvd.business.dashboard.UserAdmin userHand;
    private String username;
    private Boolean loginStatus;

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean _loginStatus) {
        loginStatus = _loginStatus;
    }

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
        loginStatus = false;
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
            FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "User password not found !", "");
            FacesContext.getCurrentInstance().addMessage(null, msg); 
            return "#";
        } else {
            for (dvd.entity.UserAdmin userAdmin : listLogin) {
                session.setAttribute("AccountInfo", listLogin);
                session.setAttribute("Permission", userAdmin.getPerName());
            }
            loginStatus = true;
            return "success";
        }
    }

    public String btnlogout_Click() {
        loginStatus = false;
        return "../Login.xhtml";
    }
}
