/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.DataStore;
import dvd.libraries.Connection;
import dvd.libraries.MapperData;
import java.sql.PreparedStatement;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class DataStoreManager {

    /**
     * Creates a new instance of DataStoreManager
     */
    private Connection conn;
    private PreparedStatement ps;
    
    public DataStoreManager() {
        conn = new Connection();
    }
    public List<DataStore> returnDataStoreById(int _DataStoreId){
        List<DataStore> listDS = null;
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _DataStoreId};
            listDS = data.getDataWithProc("showDataStoreById", "?", paramvalues, DataStore.class);
        } catch (Exception e) {
        }
        return listDS;
    }
    
    public boolean deleteDataStoreInPlaylist(int _PlaylistId, int _DataStoreId){
        try {
            String squery = "{call deleteDataStoreInPlaylist(?,?)}";
            ps = conn.GetConnect().prepareStatement(squery);
            ps.setInt(1, _PlaylistId);
            ps.setInt(2, _DataStoreId);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
