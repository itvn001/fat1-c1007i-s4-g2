/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.AlbumManager;
import dvd.entity.Album;
import dvd.entity.DataStore;
import dvd.libraries.MapperData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class DetailAlbumManager {
    /**
     * Creates a new instance of DetailAlbumManager
     */
    
    public DetailAlbumManager() {
    }
    
    public Album showInforAlbum(int _AlbumID){
        Album album = new Album();
        try {
            AlbumManager Amanager = new AlbumManager();
            ResultSet rs = Amanager.showInforAlbum(_AlbumID);
            if(rs.next()){
                album.setAlbumID(rs.getInt(1));
                album.setCateID(""+rs.getInt(2));
                album.setAlbumName(rs.getString(3));
                album.setAlbumPrice(""+rs.getDouble(4));
                album.setAlbumDateCreate(rs.getString(5));
                album.setAlbumStatus(rs.getBoolean(6));
                album.setAlbumImage(rs.getString(7));
                album.setQuantity(rs.getInt(8));
                album.setCateName(rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return album;
    }
    public List<DataStore> ListDataStore(int _AlbumID){
        List<DataStore> listStore;
        MapperData data = new MapperData();
        int[] paramnumber = new int[]{1};
        String[] paramvalues = new String[]{""+_AlbumID};
        listStore = data.getDataWithProc("listDataSotre", "?", paramnumber, paramvalues, DataStore.class);
        return listStore;
    }
}
