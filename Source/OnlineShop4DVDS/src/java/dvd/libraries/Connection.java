/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
public class Connection {

    public Connection() {
    }

    public java.sql.Connection GetConnect() {
        try {
            Properties prop = new Properties();
            //load a properties file
            ExternalContext extContext =
                    FacesContext.getCurrentInstance().getExternalContext();
            String sfile = extContext.getRealPath("//WEB-INF//config.properties");
            File f = new File(sfile);
            prop.load(new FileInputStream(f));
            //get the property value and print it out
            String server = prop.getProperty("server");
            String port = prop.getProperty("port");
            String databaseName = prop.getProperty("databasename");
            String username = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlserver://" + server + ":" + port + ";databasename=" + databaseName + ";user=" + username + ";password=" + password);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}
