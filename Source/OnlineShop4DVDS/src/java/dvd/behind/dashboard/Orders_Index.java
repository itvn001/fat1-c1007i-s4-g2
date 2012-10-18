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
            default:
                return "Unknow";
        }
    }
    private static String idOrders = "";

    public static String getIdOrders() {
        return idOrders;
    }

    public static void setIdOrders(String idOrders) {
        Orders_Index.idOrders = idOrders;
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
    public List<dvd.entity.OrderDetails> viewOrderDetails(String id){
        try{
            return this.orderhand.showListOrderDetails(id);
        }
        catch(Exception e){
            return null;
        }
    }
    public  String displayor = "none";

    public  String getDisplayor() {
        return displayor;
    }

    public  void setDisplayor(String displayor) {
        displayor = displayor;
    }
    public void viewDetailsUI(){
        displayor = "block";
    }
}
