/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Mdvd_Addnew {

    private List<SelectItem> listCategories;
    private dvd.business.dashboard.Categories categories = new dvd.business.dashboard.Categories();

    public Mdvd_Addnew() {
        List<dvd.entity.Categories> lis = this.categories.listCategories();
        this.listCategories = new ArrayList<SelectItem>();
        this.listCategories.add(new SelectItem(0, "--select--"));
        for (dvd.entity.Categories catelist : lis) {
            this.listCategories.add(new SelectItem(catelist.getCateID(),
                    catelist.getCateName()));
        }
    }
    private String idCategories = "0";

    public String getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(String idCategories) {
        this.idCategories = idCategories;
    }

    public List<SelectItem> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List<SelectItem> listCategories) {
        this.listCategories = listCategories;
    }

    public void categoriesValuesChange(ValueChangeEvent e) {
        this.idCategories = e.getNewValue().toString();
    }
}
