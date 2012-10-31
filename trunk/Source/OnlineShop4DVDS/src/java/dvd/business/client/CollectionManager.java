/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.CollectionCate;
import dvd.entity.CollectionUser;
import dvd.libraries.Connection;
import dvd.libraries.MapperData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CollectionManager {

    private Connection conn;
    private PreparedStatement ps;

    public CollectionManager() {
        conn = new Connection();
    }

    public List<CollectionCate> listCollectionCate(int UserId) {
        List<CollectionCate> listCC;
        MapperData data = new MapperData();
        String[] paramvalues = new String[]{"" + UserId};
        listCC = data.getDataWithProc("showCollectionCateById", "?", paramvalues, CollectionCate.class);
        return listCC;
    }

    public boolean listContentSave(int _CollectionCate, int _DataId) {
        try {
            String squery = "{call addDataStoteIntoMyList(" + _CollectionCate + "," + _DataId + ")}";
            ps = conn.GetConnect().prepareStatement(squery);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CollectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistDataStore(int _CollectionCate, int _DataId) {
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _CollectionCate, "" + _DataId};
            List<CollectionUser> listCU = data.getDataWithProc("checkExistDataStore", "?,?", paramvalues, CollectionUser.class);
            if (listCU.size() > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean addNewDataStore(String _Name, int _UserId){
        try {
            String squery = "{call insertNewPlaylist(?,?)}";
            ps = conn.GetConnect().prepareStatement(squery);
            ps.setString(1, _Name);
            ps.setInt(2, _UserId);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CollectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int listCollectionCateNew(int _UserId){
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _UserId};
            List<CollectionCate> listCU = data.getDataWithProc("listCollectionCateNew", "?", paramvalues, CollectionCate.class);
            for (CollectionCate collectionCate : listCU) {
                int n = collectionCate.getCollecCateID();
                return n;
            }
        } catch (Exception ex) {
        }
        return 0;
    }
}
