/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.Order;
import dvd.entity.OrderDetails;
import dvd.libraries.Connection;
import dvd.libraries.MapperData;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class OrderManager {

    private CallableStatement cl;
    private Connection conn;
    private PreparedStatement ps;

    public OrderManager() {
        conn = new Connection();
    }

    public boolean ordering(int _UserId, String _Name, String _Address) {
        try {
            GregorianCalendar date = new GregorianCalendar();
            String sNow = date.getTime().toString();
            String squery = "{call Ordering(" + _UserId + ", '" + sNow + "', N'" + _Name + "', N'" + _Address + "')}";
            ps = conn.GetConnect().prepareStatement(squery);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void orderingDetail(int _OrderId, int _AlbumId, double _Price, int _Quantity) {
        try {
            String squery = "{call OrderingDetail(" + _OrderId + ", " + _AlbumId + ", " + _Price + ", " + _Quantity + ")}";
            ps = conn.GetConnect().prepareStatement(squery);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int returnLastOrderId() {
        try {
            String squery = "Select * from returnLastOrderId";
            cl = conn.GetConnect().prepareCall(squery);
            ResultSet rs = cl.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public List<Order> listOrder(int _UserId) {
        List<Order> listO;
        MapperData data = new MapperData();
        String[] paramvalues = new String[]{"" + _UserId};
        listO = data.getDataWithProc("listOrderByUserId", "?", paramvalues, Order.class);
        return listO;
    }

    public double totalMoneyByOrderId(int _OrderId) {
        double totalMoney = 0;
        try {
            String sQuery = "{call listOrderDetailByOrderId(" + _OrderId + ")}";
            cl = conn.GetConnect().prepareCall(sQuery);
            ResultSet rs = cl.executeQuery();
            while (rs.next()) {
                totalMoney += (rs.getInt(1) * rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalMoney;
    }

    public List<OrderDetails> showOrderDetailById(int _OrderId) {
        MapperData data = new MapperData();
        String[] paramvalues = new String[]{"" + _OrderId};
        List<OrderDetails> listOD = data.getDataWithProc("showOrderDetailsById", "?", paramvalues, OrderDetails.class);
        return listOD;
    }

    public boolean changeStatus(int _Id) {
        try {
            String squery = "{call changeStatusOrder(" + _Id + ")}";
            ps = conn.GetConnect().prepareStatement(squery);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
