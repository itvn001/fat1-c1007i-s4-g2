/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.CategoryManager;
import dvd.entity.Categories;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class MasterPageManagerBean {

    /**
     * Creates a new instance of MasterPageManagerBean
     */
    public MasterPageManagerBean() {
    }
    private Categories cate;

    public List<Categories> showMenuCategory()
    {
        CategoryManager c = new CategoryManager();
        return c.showListCate();
    }
    /**
     * @return the cate
     */
    public Categories getCate() {
        return cate;
    }

    /**
     * @param cate the cate to set
     */
    public void setCate(Categories cate) {
        this.cate = cate;
    }
}
