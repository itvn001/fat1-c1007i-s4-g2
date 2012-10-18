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

    public Boolean UpdateOrdersStatus(String id, String status) {
        try {
            this.paramvalue = new String[]{
                id,
                status
            };
            this.handHand = new HandlingBusiness();
            return this.handHand.UpdateToDB("aupdateStatusOrders", "?,?", paramvalue);
        } catch (Exception e) {
            return false;
        }
    }

    public List<dvd.entity.OrderDetails> showListOrderDetails(String id) {
        try {
            this.paramvalue = new String[]{
                id
            };
            dvd.libraries.MapperData<dvd.entity.OrderDetails> ordetailsHand = new MapperData<OrderDetails>();
            return ordetailsHand.getDataWithProc("aShowOrdersDetails", "?", paramvalue, dvd.entity.OrderDetails.class);
        } catch (Exception e) {
            return null;
        }
    }

    public int showNumberOrderPending() {
        try {
            List<dvd.entity.Order> lp = this.orderMap.getDataWithView("aShowOrderPending", dvd.entity.Order.class);
            int count = 0;
            for (Order order : lp) {
                count = order.getShipStatus();
                break;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<dvd.entity.Order> RetriveListOrderPending() {
        try {
            this.paramvalue = new String[]{"1"};
            dvd.libraries.MapperData<dvd.entity.Order> ordetailsHand = new MapperData<Order>();
            return ordetailsHand.getDataWithProc("aShowOrderPendingPr", "?", paramvalue, dvd.entity.Order.class);
        } catch (Exception e) {
            return null;
        }
    }
}
