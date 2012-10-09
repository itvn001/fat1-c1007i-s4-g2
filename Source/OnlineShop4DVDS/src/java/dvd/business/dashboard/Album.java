/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.dashboard;

import dvd.libraries.HandlingBusiness;
import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Album {

    private dvd.libraries.MapperData<dvd.entity.Album> mapperCommo;
    private dvd.libraries.HandlingBusiness handbus;
    private int[] paramnumber;
    private String[] paramvalues;

    public Album() {
        this.mapperCommo = new MapperData<dvd.entity.Album>();
    }

    public List<dvd.entity.Album> getListAlbum(String id) throws Exception {
        this.paramnumber = new int[]{
            1
        };
        this.paramvalues = new String[]{
            id
        };
        return this.mapperCommo.getDataWithProc("aShowAlbum", "?", paramnumber,
                paramvalues, dvd.entity.Album.class);
    }

    public List<dvd.entity.Album> getListAlbumEditForm(String id) throws Exception {
        this.paramnumber = new int[]{
            1
        };
        this.paramvalues = new String[]{
            id
        };
        return this.mapperCommo.getDataWithProc("aShowEditAlbum", "?", paramnumber,
                paramvalues, dvd.entity.Album.class);
    }

    public Boolean CreateAlbum(dvd.entity.Album album) {
        try {
            this.paramnumber = new int[]{1, 2, 3, 4, 5
            };
            this.paramvalues = new String[]{
                album.getCateID(),
                album.getAlbumName(),
                album.getAlbumPrice(),
                album.getQuantity() + "",
                album.getAlbumImage()
            };
            this.handbus = new HandlingBusiness();
            return this.handbus.InsertToDB("aInsertAlbum", "?,?,?,?,?", paramnumber, paramvalues);
        } catch (Exception e) {
            return false;
        }
    }
}
