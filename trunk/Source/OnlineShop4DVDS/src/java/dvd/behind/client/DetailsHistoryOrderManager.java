/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.OrderManager;
import dvd.entity.OrderDetails;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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

    public void orderIdSetValue(int _Id) {
        this._OrderId = _Id;
    }

    public DetailsHistoryOrderManager() {
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
