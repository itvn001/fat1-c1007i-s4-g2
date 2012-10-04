/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class MapperDataRun {
    public static void main(String... args) {
        try {
           // ResultSetMapper<SamplePojo> resultSetMapper = new ResultSetMapper<SamplePojo>();
            ResultSet resultSet = null;
            // simple JDBC code to run SQL query and populate resultSet - START
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=AssigmentEJB;user = sa; password = !vh04782$");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Categories");
            resultSet = statement.executeQuery();
            // simple JDBC code to run SQL query and populate resultSet - END
           // List<SamplePojo> pojoList = resultSetMapper.mapRersultSetToObject(resultSet, SamplePojo.class);
            // print out the list retrieved from database
//            if (pojoList != null) {
//                for (SamplePojo pojo : pojoList) {
//                    System.out.println(pojo.getCateName());
//                }
//            } else {
//                System.out.println("ResultSet is empty. Please check if database table is empty");
//            }
            connection.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }

    }
}
