/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.dashboard;

import dvd.entity.Order;
import dvd.entity.OrderDetails;
import dvd.libraries.HandlingBusiness;
import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Orders {

    private String[] paramvalue;
    private dvd.libraries.HandlingBusiness handHand;
    private dvd.libraries.MapperData<dvd.entity.Order> orderMap = new MapperData<Order>();

    public Orders() {
        this.paramvalue = null;
    }

    public List<dvd.entity.Order> showListOrder() {
        try {
            this.paramvalue = new String[]{
                "1"
            };
            return this.orderMap.getDataWithProc("aShowOrders", "?", 
                    this.paramvalue, dvd.entity.Order.class);
        } catch (Exception e) {
            return null;
        }
    }
    public Boolean UpdateOrdersStatus(String id,String status){
        try{
            this.paramvalue = new String[]{
                id,
                status
            };
            this.handHand = new HandlingBusiness();
            return this.handHand.UpdateToDB("aupdateStatusOrders", "?,?", paramvalue);
        }
        catch(Exception e){
            return false;
        }
    }
    public List<dvd.entity.OrderDetails> showListOrderDetails(String id){
        try{
            this.paramvalue = new String[]{
                id
            };
            dvd.libraries.MapperData<dvd.entity.OrderDetails> ordetailsHand = new MapperData<OrderDetails>();
            return ordetailsHand.getDataWithProc("aShowOrdersDetails", "?", paramvalue, dvd.entity.OrderDetails.class);   
        }
        catch(Exception e){
            return null;
        }
    }
}
