/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Album;
import dvd.business.dashboard.Categories;
import java.util.ArrayList;
import java.util.List;
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
public class Mdvd_Index {
    //Id keep categories

    private String idCategories = "0";

    public String getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(String idCategories) {
        this.idCategories = idCategories;
    }
    private dvd.business.dashboard.Categories categories = new Categories();
    private dvd.business.dashboard.Album albumhand = new Album();

    public Mdvd_Index() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (session.getAttribute("dvdc") == null) {
            listCategories = new ArrayList<SelectItem>();
            List<dvd.entity.Categories> lis = this.categories.listCategories();
            this.listCategories.add(new SelectItem(0, "all"));
            for (dvd.entity.Categories catelist : lis) {
                this.listCategories.add(new SelectItem(catelist.getCateID(),
                        catelist.getCateName()));
            }
        }
        else{
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
        return this.albumhand.getListAlbum(idCategories);
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
        this.ListAlbum = new ArrayList<dvd.entity.Album>();
        this.ListAlbum = this.albumhand.getListAlbum(this.idCategories);
        return ListAlbum;
    }

    public void setListAlbum(List<dvd.entity.Album> ListAlbum) {
        this.ListAlbum = ListAlbum;
    }
}
