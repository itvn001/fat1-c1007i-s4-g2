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
    public String checkStatus(int _Status){
        if(_Status == 0){
            return "<td class=\"HistoryOrdersTableTdStatus\"><a onclick=\"return confirm('Cancel order?');\" style=\"background: none repeat scroll 0 0 green;color: yellow;padding: 1px 10px;text-align: center;\" href=\"\">Waiting</a></td>";
        }else if(_Status == 1){
            return "<td class=\"HistoryOrdersTableTdStatus\"><span style=\"background: none repeat scroll 0 0 inactivecaption;color: green;padding: 1px 9px;text-align: center;\">Transportding</span></td>";
        }else if(_Status == 2){
            return "<td class=\"HistoryOrdersTableTdStatus\"><span style=\"background: none repeat scroll 0 0 inactivecaption;color: green;padding: 1px 9px;text-align: center;\">Done</span></td>";
        }else{
            return "<td class=\"HistoryOrdersTableTdStatus\"><span style=\"background: none repeat scroll 0 0 inactivecaption;color: green;padding: 1px 9px;text-align: center;\">Done</span></td>";
        }
    }
}
