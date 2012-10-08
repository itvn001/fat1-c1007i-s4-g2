/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.dashboard;

import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Album {

    private dvd.libraries.MapperData<dvd.entity.Album> mapperCommo;
    private int[] paramnumber;
    private String[] paramvalues;

    public Album() {
        this.mapperCommo = new MapperData<dvd.entity.Album>();
    }
    
    public List<dvd.entity.Album> getListAlbum(String id) {
        this.paramnumber = new int[]{
            1
        };
        this.paramvalues = new String[]{
            id
        };
        return this.mapperCommo.getDataWithProc("aShowAlbum", "?", paramnumber,
                paramvalues, dvd.entity.Album.class);
    }

    public List<dvd.entity.Album> getListAlbumEditForm(String id) {
        this.paramnumber = new int[]{
            1
        };
        this.paramvalues = new String[]{
            id
        };
        return this.mapperCommo.getDataWithProc("aShowEditAlbum", "?", paramnumber,
                paramvalues, dvd.entity.Album.class);
    }
}
