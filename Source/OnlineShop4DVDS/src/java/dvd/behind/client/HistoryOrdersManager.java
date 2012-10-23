/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.OrderManager;
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
public class HistoryOrdersManager {

    /**
     * Creates a new instance of HistoryOrdersManager
     */
    private int UserId = 1;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public HistoryOrdersManager() {
//        if (session.getAttribute("UserId") == null) {
//            UserId = 0;
//        }
    }

    public List<Order> listOrder() {
        List<Order> listO;
        OrderManager om = new OrderManager();
        listO = om.listOrder(UserId);
        for (Order order : listO) {
            order.setTotalMoney(order.getOrderID());
        }
        return listO;
    }
    public boolean checkStatus(int _Status){
        if(_Status ==1){
            return true;
        }else{
            return false;
        }
    }
}
