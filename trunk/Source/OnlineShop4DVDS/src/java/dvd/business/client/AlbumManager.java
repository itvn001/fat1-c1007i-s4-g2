/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.AlbumAllExtention;
import dvd.libraries.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AlbumManager {
    private CallableStatement cl;
    private ResultSet rs;
    private Connection conn;
    public AlbumManager(){
        conn = new Connection();
    }
    public ResultSet listAllAlbum(int _PIndex, int _PageSize){
        try {
            String squery = "{call pagingShowAllAlbum(?,?)}";
            cl = conn.GetConnect().prepareCall(squery);
            cl.setInt(1, _PIndex);
            cl.setInt(2, _PageSize);
            rs = cl.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
