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
    private int albumId;
    private String firstPath;
    private String firstImage;
    private String firstTitle;
    AlbumManager albumManager;

    public DetailAlbumManager() {
    }
    private  int countnumbers = 0;

    public  int getCountnumbers() {
        countnumbers ++;
        return countnumbers;
    }
    public void setCountnumbers(int _countnumbers) {
        countnumbers = _countnumbers++;
    }
    public void addAlbumID(int _albumID) {
        albumId = _albumID;
    }

    public Album showInforAlbum() {
        Album album = new Album();
        try {
            albumManager = new AlbumManager();
            ResultSet rs = albumManager.showInforAlbum(albumId);
            if (rs.next()) {
                album.setAlbumID(rs.getInt(1));
                album.setCateID("" + rs.getInt(2));
                album.setAlbumName(rs.getString(3));
                album.setAlbumPrice("" + rs.getDouble(4));
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

    public List<DataStore> showDataStore() {
        List<DataStore> listDS = null;
        try {
            albumManager = new AlbumManager();
            listDS = albumManager.showDataStore(albumId);
            firstImage = listDS.get(0).getDataImage();
            firstPath = listDS.get(0).getDataPath();
            firstTitle = listDS.get(0).getDataName();
        } catch (Exception e) {
        }
        return listDS;
    }

    public String showNameSuppllier() {
        albumManager = new AlbumManager();

        return albumManager.getNameSupplier(albumId);
    }

    /**
     * @return the AlbumId
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * @param AlbumId the AlbumId to set
     */
    public void setAlbumId(int AlbumId) {
        this.albumId = AlbumId;
    }

    /**
     * @return the firstPath
     */
    public String getFirstPath() {
        return firstPath;
    }

    /**
     * @param firstPath the firstPath to set
     */
    public void setFirstPath(String firstPath) {
        this.firstPath = firstPath;
    }

    /**
     * @return the firstImage
     */
    public String getFirstImage() {
        return firstImage;
    }

    /**
     * @param firstImage the firstImage to set
     */
    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    /**
     * @return the firstTitle
     */
    public String getFirstTitle() {
        return firstTitle;
    }

    /**
     * @param firstTitle the firstTitle to set
     */
    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }
}
