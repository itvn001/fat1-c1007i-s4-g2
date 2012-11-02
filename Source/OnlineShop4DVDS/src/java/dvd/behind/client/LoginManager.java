/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.UsersManager;
import dvd.entity.Users;
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
public class LoginManager {

    private String userName;
    private String passWord;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    public LoginManager() {
    }

    public String checkLogin() {
        try {
            UsersManager um = new UsersManager();
            Users users = um.checkLogin(userName, passWord);
            if(users != null){
                session.setAttribute("UserId", users.getUserID());
                session.setAttribute("UserName", users.getUserAccount());
                FacesContext.getCurrentInstance().getExternalContext().redirect("Default.xhtml");
            }
        } catch (Exception e) {
        }
        return "Login.xhtml";
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
