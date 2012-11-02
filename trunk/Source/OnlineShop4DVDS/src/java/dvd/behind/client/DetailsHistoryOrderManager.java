/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.OrderManager;
import dvd.entity.OrderDetails;
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
public class DetailsHistoryOrderManager {

    /**
     * Creates a new instance of DetailsHistoryOrderManager
     */
    private int _OrderId;
    private int UserId;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public void orderIdSetValue(int _Id) {
        this._OrderId = _Id;
    }

    public DetailsHistoryOrderManager() {
        try {
            if (session.getAttribute("UserId") == null || session.getAttribute("UserId") == "") {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                UserId = Integer.parseInt("" + session.getAttribute("UserId"));
            }
        } catch (Exception e) {
        }
    }
    private double totalMoney = 0;
    List<OrderDetails> listOD;

    public List<OrderDetails> showOrderDetailById() {
        totalMoney = 0;
        try {
            OrderManager manager = new OrderManager();
            listOD = manager.showOrderDetailById(_OrderId);
            for (OrderDetails orderDetails : listOD) {
                orderDetails.setMoney(orderDetails.getQuantity() * Double.parseDouble(orderDetails.getUnitPrice()));
                setTotalMoney(getTotalMoney() + orderDetails.getMoney());
            }
        } catch (Exception e) {
        }
        return listOD;
    }

    public int sizeList() {
        return listOD.size();
    }

    /**
     * @return the totalMoney
     */
    public double getTotalMoney() {
        return totalMoney;
    }

    /**
     * @param totalMoney the totalMoney to set
     */
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * @return the _OrderId
     */
    public int getOrderId() {
        return _OrderId;
    }

    /**
     * @param OrderId the _OrderId to set
     */
    public void setOrderId(int OrderId) {
        this._OrderId = OrderId;
    }
}
