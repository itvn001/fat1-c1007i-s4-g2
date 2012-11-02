/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.Users;
import dvd.libraries.Connection;
import dvd.libraries.Encryption;
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

    public UsersManager() {
        conn = new Connection();
    }
    private CallableStatement cl;
    private Connection conn;

    public List<Users> loadInforFromData(int _UserId) {
        MapperData data = new MapperData();
        String[] paramvalues = new String[]{"" + _UserId};
        List<Users> listUser = data.getDataWithProc("loadUserInforFromData", "?", paramvalues, Users.class);
        return listUser;
    }

    public boolean saveInforForData(int _UserId, String _Name, String _Phone, String _Address) {
        try {
            String squery = "{call saveUserInforIntoData('" + _UserId + "', N'" + _Name + "','" + _Phone + "', N'" + _Address + "')}";
            cl = conn.GetConnect().prepareCall(squery);
            int n = cl.executeUpdate();
            if (n > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Users showInforFromData(int _UserId) {
        Users users = null;
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _UserId};
            List<Users> listUser = data.getDataWithProc("showInforFromData", "?", paramvalues, Users.class);
            for (Users us : listUser) {
                users = us;
                break;
            }
        } catch (Exception e) {
        }
        return users;
    }
    
    public Users checkLogin(String _UserName, String _Pass){
        Users users = null;
        try {
            Encryption e = new Encryption();
            String passWord = e.Encript_Pass(_Pass);
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _UserName, "" + passWord};
            List<Users> listUser = data.getDataWithProc("checkLogin", "?,?", paramvalues, Users.class);
            for (Users us : listUser) {
                users = us;
                break;
            }
        } catch (Exception e) {
        }
        return users;
    }
}
