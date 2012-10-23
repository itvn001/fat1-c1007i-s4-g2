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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
    
    public PAIManagerBean() {
    }
    
    public void loadInforFromData(){
        UsersManager manager = new UsersManager();
        List<Users> listUser = manager.loadInforFromData(getUserId());
        for (Users users : listUser) {
            this.name = users.getUserName();
            this.phone = users.getUserFone();
            this.address = users.getAddress();
        }
    }

    public void saveInforForData(){
        try {
            UsersManager manager = new UsersManager();
            manager.saveInforForData(getUserId(), name, phone, address);
            FacesContext.getCurrentInstance().getExternalContext().redirect("ClientPrevieBills.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PAIManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
}
