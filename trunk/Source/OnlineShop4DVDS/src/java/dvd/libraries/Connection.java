/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class Connection {
    public Connection() {
    }

    public java.sql.Connection GetConnect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=SHOPDVDS;user=admin;password=123456");
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}
