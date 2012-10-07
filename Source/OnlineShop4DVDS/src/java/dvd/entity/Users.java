/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Administrator
 */
@Entity
public class Users {

    @Column(name = "UserID")
    @Id
    private int userID;
    @Column(name = "UserAccount")
    private String userAccount;
    @Column(name = "UserPassword")
    private String userPassword;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "UserAge")
    private String userAge;
    @Column(name = "UserFone")
    private String userFone;
    @Column(name = "UserSex")
    private String userSex;
    @Column(name = "UserStatus")
    private Boolean userStatus;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

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

    public String getUserFone() {
        return userFone;
    }

    public void setUserFone(String userFone) {
        this.userFone = userFone;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }
}
