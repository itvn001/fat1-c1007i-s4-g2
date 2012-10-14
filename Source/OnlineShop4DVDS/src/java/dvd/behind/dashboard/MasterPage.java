/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

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
public class MasterPage {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    /**
     * Creates a new instance of MasterPage
     */
    public MasterPage() {
        if (session.getAttribute("") == null) {
        }
    }
    private static String show_hide = "show";

    public String getShow_hide() {
        return show_hide;
    }

    public void setShow_hide(String show_hide) {
        MasterPage.show_hide = show_hide;
    }

    public void setSeesionChildMenu() {
        if (session.getAttribute("childmenu") == null) {
            session.setAttribute("childmenu", "1");
        }
    }

    public String actionclick_Home() {
        session.setAttribute("childmenu", "1");
        return "../View/Index.xhtml";
    }

    public String actionclick_mngdvd() {
        session.setAttribute("childmenu", "3");
        return "../Malbum/Index.xhtml";
    }

    public String actionclick_mngdata() {
        session.setAttribute("childmenu", "4");
        return "../Mdata/Index.xhtml";
    }

    public void commitShowHideNavigator() {
        if (this.show_hide.trim().equals("show")) {
            this.show_hide = "hide";
        } else if (this.show_hide.trim().equals("hide")) {
            this.show_hide = "show";
        }
    }
}
