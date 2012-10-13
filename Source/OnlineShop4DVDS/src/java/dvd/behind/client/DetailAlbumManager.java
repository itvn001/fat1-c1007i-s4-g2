/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.AlbumManager;
import dvd.entity.Album;
import dvd.entity.DataStore;
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
    private int AlbumId;
    AlbumManager albumManager;
    public DetailAlbumManager() {
    }
    public void addAlbumID(int _albumID){
        AlbumId = _albumID;
    }
    public Album showInforAlbum(){
        Album album = new Album();
        try {
            albumManager = new AlbumManager();
            ResultSet rs = albumManager.showInforAlbum(AlbumId);
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
                album.setAlbumDetails(rs.getString(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return album;
    }
    public List<DataStore> showDataStore(){
        albumManager = new AlbumManager();
        return albumManager.showDataStore(AlbumId);
    }
    public String showNameSuppllier(){
        albumManager = new AlbumManager();
        
        return albumManager.getNameSupplier(AlbumId);
    }
    /**
     * @return the AlbumId
     */
    public int getAlbumId() {
        return AlbumId;
    }

    /**
     * @param AlbumId the AlbumId to set
     */
    public void setAlbumId(int AlbumId) {
        this.AlbumId = AlbumId;
    }
}
