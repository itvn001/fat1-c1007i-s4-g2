/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.UsersManager;
import dvd.entity.Users;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class PAIManagerBean {

    /**
     * Creates a new instance of PAIManagerBean
     */
    private String userId = "vanthuc";
    private String name;
    private String phone;
    private String address;
    private String message;
    private boolean displayMessage;
    private boolean typeMessage;

    public void resetMessage(){
        this.setMessage("");
        setDisplayMessage(false);
    }

    public PAIManagerBean() {
    }

    public void loadInforFromData() {
        try {
            UsersManager manager = new UsersManager();
            List<Users> listUser = manager.loadInforFromData(getUserId());
            for (Users users : listUser) {
                this.name = users.getUserName();
                this.phone = users.getUserFone();
                this.address = users.getAddress();
            }
        } catch (Exception e) {
        }
    }

    public String saveInforForData() {
        try {
            UsersManager manager = new UsersManager();
            boolean result = manager.saveInforForData(getUserId(), name, phone, address);
            if (result) {
                return "ClientPrevieBills.xhtml?face=true";
            }
        } catch (Exception ex) {
            Logger.getLogger(PAIManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.displayMessage = true;
        this.typeMessage = false;
        this.message = "Can not save information please check again!";
        return "PersonalAuthenticationInformation.xhtml";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the displayMessage
     */
    public boolean isDisplayMessage() {
        return displayMessage;
    }

    /**
     * @param displayMessage the displayMessage to set
     */
    public void setDisplayMessage(boolean displayMessage) {
        this.displayMessage = displayMessage;
    }

    /**
     * @return the typeMessage
     */
    public boolean isTypeMessage() {
        return typeMessage;
    }

    /**
     * @param typeMessage the typeMessage to set
     */
    public void setTypeMessage(boolean typeMessage) {
        this.typeMessage = typeMessage;
    }
}
