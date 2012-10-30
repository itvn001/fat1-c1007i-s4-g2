/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Orders;
import dvd.entity.Order;
import java.util.List;
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
    public String actionClick_mngOrder(){
        session.setAttribute("childmenu", "5");
        return "../Orders/Index.xhtml";
    }
    public String actionClick_mngUser(){
        session.setAttribute("childmenu", "2");
        return "../Users/Index.xhtml";
    }
    public String actionClick_MngCate(){
        session.setAttribute("childmenu", "7");
        return "../Categories/Index.xhtml";
    }
    public String actionClick_MngComment(){
        session.setAttribute("childmenu", "8");
        return "../Comment/Index.xhtml";
    }
    public String viewAllPendingOrder(){
        return "../Orders/Index.xhtml";
    }
    public void commitShowHideNavigator() {
        if (this.show_hide.trim().equals("show")) {
            this.show_hide = "hide";
        } else if (this.show_hide.trim().equals("hide")) {
            this.show_hide = "show";
        }
    }
    private int noticeItemOrderPending = 0;
    private dvd.business.dashboard.Orders orHand = new Orders();
    public int getNoticeItemOrderPending() {
        this.noticeItemOrderPending = this.orHand.showNumberOrderPending();
        return noticeItemOrderPending;
    }
    public void setNoticeItemOrderPending(int noticeItemOrderPending) {
        this.noticeItemOrderPending = noticeItemOrderPending;
    }
    private List<dvd.entity.Order> listOrderPending;

    public List<Order> getListOrderPending() {
        this.listOrderPending = this.orHand.RetriveListOrderPending();
        return listOrderPending;
    }

    public void setListOrderPending(List<Order> listOrderPending) {
        this.listOrderPending = listOrderPending;
    }
}
