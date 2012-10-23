/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.Users;
import dvd.libraries.Connection;
import dvd.libraries.MapperData;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UsersManager {
    
    public UsersManager(){
        conn = new Connection();
    }
    
    private CallableStatement cl;
    private Connection conn;
    
    public List<Users> loadInforFromData(String _UserId){
        MapperData data = new MapperData();
        String[] paramvalues = new String[]{_UserId};
        List<Users> listUser = data.getDataWithProc("loadUserInforFromData", "?", paramvalues, Users.class);
        return listUser;
    }
    public void saveInforForData(String _UserId, String _Name, String _Phone, String _Address){
        try {
            String squery = "{call saveUserInforIntoData('"+_UserId+"', N'"+_Name+"','"+_Phone+"', N'"+_Address+"')}";
            cl = conn.GetConnect().prepareCall(squery);
            cl.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
