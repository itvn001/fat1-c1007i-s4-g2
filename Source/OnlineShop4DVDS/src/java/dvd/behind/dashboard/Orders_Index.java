/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Orders;
import dvd.entity.Order;
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
public class Orders_Index {

    private dvd.business.dashboard.Orders orderhand = new Orders();

    public Orders_Index() {
    }
    private List<dvd.entity.Order> listOrder;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getListOrder() {
        this.listOrder = this.orderhand.showListOrder();
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    public String orderStyle(int status) {
        switch (status) {
            case 0:
                return "Pedding";
            case 1:
                return "Transporting";
            case 2:
                return "Done";
            case 3:
                return "Cancle By Users";
            default:
                return "Unknow";
        }
    }
    private String idOrders = "";

    public String getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(String idOrders) {
        this.idOrders = idOrders;
    }

    public void handlingStatus(String id, String status) {
        try {
            if (this.orderhand.UpdateOrdersStatus(id, status) == true) {
                this.message = dvd.libraries.UImessage.generalMessage("blue", "Orders Status is update", "", "");
            } else {
                this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "Please try again !", "");
            }
        } catch (Exception e) {
        }
    }

    public List<dvd.entity.OrderDetails> viewOrderDetails(String id) {
        try {
            return this.orderhand.showListOrderDetails(id);
        } catch (Exception e) {
            return null;
        }
    }
    private List<dvd.entity.OrderDetails> listOrderDetails;

    public List<OrderDetails> getListOrderDetails() {
        this.listOrderDetails = this.orderhand.showListOrderDetails(this.idOrders);
        return listOrderDetails;
    }

    public void setListOrderDetails(List<OrderDetails> listOrderDetails) {
        this.listOrderDetails = listOrderDetails;
    }
    public String displayor = "none";

    public String getDisplayor() {
        return displayor;
    }

    public void setDisplayor(String a) {
        displayor = a;
    }
    private String shipaddress;

    public String getShipaddress() {
        return shipaddress;
    }

    public String getFone() {
        return fone;
    }
    private String fone;
    public void viewDetailsUI(String sId,String _shipaddress,String _fone) {
        this.shipaddress = _shipaddress;
        this.fone = _fone;
        this.idOrders = sId;
        this.scode = sId;
        displayor = "block";
    }
    private String scode;

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }
    private int totalMoney = 0;

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
    public int caculateMomey(int price,int quantity){
        totalMoney += price * quantity;
        return price * quantity;
    }
}
