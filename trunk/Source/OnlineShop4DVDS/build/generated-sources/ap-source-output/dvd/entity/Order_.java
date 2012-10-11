package dvd.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-10-11T01:05:44")
@StaticMetamodel(Order.class)
public class Order_ { 

    public static volatile SingularAttribute<Order, Integer> userID;
    public static volatile SingularAttribute<Order, Integer> shipStatus;
    public static volatile SingularAttribute<Order, Integer> orderID;
    public static volatile SingularAttribute<Order, String> orderDate;
    public static volatile SingularAttribute<Order, String> shipName;
    public static volatile SingularAttribute<Order, String> shipAddress;
    public static volatile SingularAttribute<Order, String> shipPostalCode;

}