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
public class UserAdmin {

    @Column(name = "UserAdminID")
    @Id
    private int userAdminID;
    @Column(name = "Account")
    private String account;
    @Column(name = "Passwords")
    private String passwords;
    @Column(name = "PerID")
    private int perID;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;

    public int getUserAdminID() {
        return userAdminID;
    }

    public void setUserAdminID(int userAdminID) {
        this.userAdminID = userAdminID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public int getPerID() {
        return perID;
    }

    public void setPerID(int perID) {
        this.perID = perID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
