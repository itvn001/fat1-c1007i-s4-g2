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
public class DataStore {

    private dvd.libraries.MapperData<dvd.entity.DataStore> mapperCommo;
    private int[] paramnumber;
    private String[] paramvalues;

    public DataStore() {
        this.mapperCommo = new MapperData<dvd.entity.DataStore>();
    }

    public List<dvd.entity.DataStore> getListDataStoreWAlbum(String id) {
        this.paramnumber = new int[]{
            1
        };
        this.paramvalues = new String[]{
            id
        };
        return this.mapperCommo.getDataWithProc("aShowDataStore", "?", paramnumber,
                paramvalues, dvd.entity.DataStore.class);
    }
}
