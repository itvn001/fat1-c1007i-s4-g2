/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.CategoryManager;
import dvd.entity.Categories;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class MasterPageManagerBean {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    /**
     * Creates a new instance of MasterPageManagerBean
     */
    public MasterPageManagerBean() {
    }
    private Categories cate;
    private int count = 0;

    public int getCount() {
        count++;
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean checkLoginLogout() {
        try {
            if (session.getAttribute("UserId") == null || session.getAttribute("UserId") == "") {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void logoutAccount() {
        try {
            if (session.getAttribute("UserId") != null) {
                session.setAttribute("UserId", null);
                session.setAttribute("UserName", null);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("Default.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MasterPageManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }
    }

    public List<Categories> showMenuCategory() {
        CategoryManager c = new CategoryManager();
        List<Categories> listCate = null;
        try {
            listCate = c.showListCate();
        } catch (Exception e) {
        }
        return listCate;
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
