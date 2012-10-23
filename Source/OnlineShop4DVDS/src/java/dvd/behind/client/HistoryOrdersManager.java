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

    public String checkStatus(int _ID, int _Status) {
        if (_Status == 0) {
            return "<td class=\"HistoryOrdersTableTdStatus\"><h:commandLink action=\"#{historyOrdersManager.changeStatus(listOrder.orderID)}\" onclick=\"return confirm('Cancel order ID: " + _ID + " ?');\" style=\"background: none repeat scroll 0 0 yellow;color: green;cursor: pointer;padding: 1px 10px;text-align: center;\" href=\"\">Waiting</commandLink></td>";
        } else if (_Status == 1) {
            return "<td class=\"HistoryOrdersTableTdStatus\"><span style=\"background: none repeat scroll 0 0 inactivecaption;color: green;padding: 1px 9px;text-align: center;\">Transportding</span></td>";
        } else if (_Status == 2) {
            return "<td class=\"HistoryOrdersTableTdStatus\"><span style=\"background: none repeat scroll 0 0 green;color: yellow;padding: 1px 9px;text-align: center;\">Done</span></td>";
        } else {
            return "<td class=\"HistoryOrdersTableTdStatus\"><span style=\"background: none repeat scroll 0 0 buttonface;color: red;padding: 1px 9px;text-align: center;\">Cancel</span></td>";
        }
    }
    
    public String changeStatus(int _ID){
        OrderManager om = new OrderManager();
        if(om.changeStatus(_ID)){
            MessageManager mm = new MessageManager();
            mm.setDisplayMessage(true);
            mm.setTypeMessage(true);
            mm.setMessage("Cancel order: " + _ID + " success!");
        }else{
            MessageManager mm = new MessageManager();
            mm.setDisplayMessage(true);
            mm.setTypeMessage(false);
            mm.setMessage("Cancel order: " + _ID + " false!");
        }
        return "HistoryOrders.xhtml";
    }
}
