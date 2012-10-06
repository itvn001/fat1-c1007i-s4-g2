/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

import java.sql.DriverManager;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
public class Connection {

    private String strConnection = "1212";

    public Connection() {
    }

    public java.sql.Connection GetConnect() {
        try {
//            FacesContext ctx = FacesContext.getCurrentInstance();
//            String myConstantValue =
//                    ctx.getExternalContext().getInitParameter("connectionstr");
//            System.out.println(myConstantValue);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            java.sql.Connection connection = 
                    DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=SHOPDVDS;user=sa;password=!vh04782$");
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] a) {
        Connection g = new Connection();
        g.GetConnect();
    }
}
