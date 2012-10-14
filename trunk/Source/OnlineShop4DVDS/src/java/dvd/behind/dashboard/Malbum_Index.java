/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Album;
import dvd.business.dashboard.Categories;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Malbum_Index {
    //Id keep categories

    private String idCategories = "0";

    public String getIdCategories() {
        return idCategories;
    }
    private int idalb;

    public int getIdalb() {
        return idalb;
    }

    public void setIdalb(int idalb) {
        this.idalb = idalb;
    }

    public String setIDAlbum(int id) {
        HttpSession session =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("re_albumid", id);
        return "Edit.xhtml";
    }

    public void setIdCategories(String idCategories) {
        this.idCategories = idCategories;
    }
    private dvd.business.dashboard.Categories categories = new Categories();
    private dvd.business.dashboard.Album albumhand = new Album();

    public Malbum_Index() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (session.getAttribute("dvdc") == null) {
            listCategories = new ArrayList<SelectItem>();
            List<dvd.entity.Categories> lis = this.categories.listCategories();
            this.listCategories.add(new SelectItem(0, "All Categories"));
            for (dvd.entity.Categories catelist : lis) {
                this.listCategories.add(new SelectItem(catelist.getCateID(),
                        catelist.getCateName()));
            }
        } else {
            session.setAttribute("dvdc", "123");
        }
    }
    private List<SelectItem> listCategories;

    public List<SelectItem> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List<SelectItem> listCategories) {
        this.listCategories = listCategories;
    }

    public List<dvd.entity.Album> ListAlbum() {
        try {
            return this.albumhand.getListAlbum(idCategories);
        } catch (Exception ex) {
            Logger.getLogger(Malbum_Index.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Event Click of Commobox categories
     *
     * @param e
     */
    public void categoriesValuesChange(ValueChangeEvent e) {
        this.idCategories = e.getNewValue().toString();
    }
    private List<dvd.entity.Album> ListAlbum;

    public List<dvd.entity.Album> getListAlbum() {
        try {
            this.ListAlbum = new ArrayList<dvd.entity.Album>();
            this.ListAlbum = this.albumhand.getListAlbum(this.idCategories);
            return ListAlbum;
        } catch (Exception ex) {
            Logger.getLogger(Malbum_Index.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setListAlbum(List<dvd.entity.Album> ListAlbum) {
        this.ListAlbum = ListAlbum;
    }

    public String returnStatus(String st) {
        if (st.trim().equals("true")) {
            return "ON";
        } else {
            return "OFF";
        }
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void btnOnOffAlbum_Click(String idalbum) throws Exception {
        String id = idalbum;
        if (this.albumhand.Setpublish(id) == true) {
            this.message = dvd.libraries.UImessage.generalMessage("blue", "You Action Success", "#", "");
        } else {
            this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "#", "Please try again!");
        }
    }
}
