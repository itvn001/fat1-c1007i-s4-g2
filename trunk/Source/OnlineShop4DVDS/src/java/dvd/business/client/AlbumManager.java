/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.Album;
import dvd.entity.DataStore;
import dvd.libraries.Connection;
import dvd.libraries.MapperData;
import java.sql.CallableStatement;
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

    public AlbumManager() {
        conn = new Connection();
    }

    public ResultSet listAllAlbum(int _PIndex, int _PageSize) {
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

    public ResultSet showInforAlbum(int _AlbumId) {
        try {
            String squery = "{call showInforAlbum(?)}";
            cl = conn.GetConnect().prepareCall(squery);
            cl.setInt(1, _AlbumId);
            rs = cl.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public List<DataStore> showDataStore(int _AlbumID) {
        MapperData data = new MapperData();
        int[] paramnumber = new int[]{1};
        String[] paramvalues = new String[]{"" + _AlbumID};
        return data.getDataWithProc("listDataSotre", "?", paramnumber, paramvalues, DataStore.class);
    }

    public String getNameSupplier(int _AlbumID) {
        try {
            int supplierID = getSupplierID(_AlbumID);
            String squery = "{call showNameSupplier(" + supplierID + ")}";
            cl = conn.GetConnect().prepareCall(squery);
            rs = cl.executeQuery();
            if (rs.next()) {
                String supplierName = rs.getString(1);
                return supplierName;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "No Supplier";
    }

    private int getSupplierID(int _AlbumID) {
        try {
            String squery = "{call getSupplierID(?)}";
            cl = conn.GetConnect().prepareCall(squery);
            cl.setInt(1, _AlbumID);
            rs = cl.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt(1);
                return supplierID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
