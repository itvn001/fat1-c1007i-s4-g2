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

    /**
     * Creates a new instance of MasterPage
     */
    public MasterPage() {
    }

    public void setSeesionChildMenu() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("childmenu", "1");
    }
}
