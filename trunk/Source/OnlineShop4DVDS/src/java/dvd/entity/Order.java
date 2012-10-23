/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Administrator
 */
@Entity
public class Order {

    @Column(name = "OrderID")
    @Id
    private int orderID;
    @Column(name = "UserID")
    private int userID;
    @Column(name = "OrderDate")
    private String orderDate;
    @Column(name = "ShipName")
    private String shipName;
    @Column(name = "ShipAddress")
    private String shipAddress;
    @Column(name = "ShipPostalCode")
    private String shipPostalCode;
    @Column(name = "ShipStatus")
    private int shipStatus;
    @Column(name = "TotalMoney")
    private double totalMoney;
    @Column(name = "UserAccount")
    private String userAccount;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public int getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(int shipStatus) {
        this.shipStatus = shipStatus;
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
}
