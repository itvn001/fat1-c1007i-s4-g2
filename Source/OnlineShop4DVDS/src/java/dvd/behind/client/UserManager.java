/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.libraries.Connection;
import dvd.libraries.HandlingBusiness;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author hungdn_a04769
 */
@ManagedBean
@RequestScoped
public class UserManager {

    /**
     * Creates a new instance of UserManager
     */
    private String userAccount;
    private String userPassword;
    private String userName;
    private String userAge;
    private String userPhone;
    private String userSex;
    private String userStatus;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public UserManager() {
    }
    Connection conn = new Connection();

    public void createUserAccount() {
        String[] parvalues = new String[]{
            this.userAccount,
            this.userPassword,
            this.userName,
            this.userAge,
            this.userPhone,
            this.userSex
        };
        dvd.libraries.HandlingBusiness handg = new HandlingBusiness();
        handg.InsertToDB("CreateUserAccount", "?,?,?,?,?,?", parvalues);
        
    }
}
