/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.entity.Users;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class User_Index {

    private dvd.business.dashboard.Users userh = new dvd.business.dashboard.Users();
    private List<dvd.entity.Users> listUser;

    public List<Users> getListUser() {
        this.listUser = userh.listUser();
        return listUser;
    }

    public void setListUser(List<Users> listUser) {
        this.listUser = listUser;
    }
    public User_Index() {
        
    }
    private String idUser = "";

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    private List<dvd.entity.Users> listDetailsUser;

    public List<Users> getListDetailsUser() {
        this.listDetailsUser = this.userh.listDetailsUser(this.idUser);
        return listDetailsUser;
    }
    public String toHome(){
        return "Index.xhtml";
    }
    public void setListDetailsUser(List<Users> listDetailsUser) {
        this.listDetailsUser = listDetailsUser;
    }
    public String toDetailsUsers(String id){
        this.idUser = id;
        return "View.xhtml";
    }
}
