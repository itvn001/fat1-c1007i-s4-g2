/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.CollectionCate;
import dvd.entity.CollectionUser;
import dvd.entity.DataStore;
import dvd.libraries.Connection;
import dvd.libraries.MapperData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<CollectionCate> listCC = null;
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + UserId};
            listCC = data.getDataWithProc("showCollectionCateById", "?", paramvalues, CollectionCate.class);
        } catch (Exception e) {
        }
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

    public boolean addNewDataStore(String _Name, int _UserId) {
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

    public int listCollectionCateNew(int _UserId) {
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
    
    public boolean deleteCollectionCate(int ClId){
        try {
            String squery = "{call deleteCollectionCateById(?)}";
            ps = conn.GetConnect().prepareStatement(squery);
            ps.setInt(1, ClId);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public List<DataStore> listDataStoreByPlaylistId(int _PlaylistId){
        List<DataStore> listDS = new ArrayList<DataStore>();
        try {
            MapperData data =  new MapperData();
            List<CollectionUser> listCU = listCollectionUserByPlaylistId(_PlaylistId);
            for (CollectionUser collectionUser : listCU) {
                int s = collectionUser.getDataID();
                String[] paramvalues = new String[]{"" + collectionUser.getDataID()};
                List<DataStore> listDSMini = data.getDataWithProc("showDataStoreById", "?", paramvalues, DataStore.class);
                for (DataStore dataStore : listDSMini) {
                    listDS.add(dataStore);
                }
            }
        } catch (Exception e) {
        }
        return listDS;
    }
    
    public List<CollectionUser> listCollectionUserByPlaylistId(int _Playlist){
        List<CollectionUser> listCU = null;
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _Playlist};
            listCU = data.getDataWithProc("listCollectionUserByPlaylistId", "?", paramvalues, CollectionUser.class);
        } catch (Exception e) {
        }
        return listCU;
    }
    
    public List<CollectionCate> listCollectionCateById(int _CollectionId, int _UserId){
        List<CollectionCate> listCC = null;
        try {
            MapperData data = new MapperData();
            String[] paramvalues = new String[]{"" + _CollectionId, "" + _UserId};
            listCC = data.getDataWithProc("listCollectionCateById", "?,?", paramvalues, CollectionCate.class);
        } catch (Exception e) {
        }
        return listCC;
    }
}
