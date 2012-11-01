/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.entity.Album;
import dvd.entity.DataStore;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Mdata_Index {

    // idAlbum keep id of album
    private String idAlbum = "0";
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }
    private List<SelectItem> listCateAlbum;
    private dvd.business.dashboard.Album albumhand = new dvd.business.dashboard.Album();
    private dvd.business.dashboard.DataStore datahand = new dvd.business.dashboard.DataStore();

    public List<SelectItem> getListCateAlbum() {
        return listCateAlbum;
    }
    String[] lengthface = new String[]{
        "3:09",
        "3:39",
        "4:00",
        "2:30",
        "4:02"
    };
    static int i2 = 0;
    public String randomSongLength() {
        i2++;
        if(i2 == 5){
            i2 = 0;
        }
        return lengthface[i2];
    }

    public void setListCateAlbum(List<SelectItem> listCateAlbum) {
        this.listCateAlbum = listCateAlbum;
    }
    private List<dvd.entity.DataStore> listData;

    public List<DataStore> getListData() {
        this.listData = this.datahand.getListAllDataWithAlbumCate(idAlbum);
        return listData;
    }

    public void setListData(List<DataStore> listData) {
        this.listData = listData;
    }

    public Mdata_Index() {
        this.listCateAlbum = new ArrayList<SelectItem>();
        List<dvd.entity.Album> lista = this.albumhand.getCateAlbum();
        this.listCateAlbum.add(new SelectItem(0,
                "- All -"));
        for (Album album : lista) {
            this.listCateAlbum.add(new SelectItem(album.getAlbumID(),
                    album.getAlbumName()));
        }
    }

    public String redirectToEdit(String id) {
        this.idEditData = id;
        return "Edit.xhtml";
    }
    private static String idEditData = "0";
    private List<dvd.entity.DataStore> listEditData;

    public List<DataStore> getListEditData() {
        this.listEditData = this.datahand.getListEditData(idEditData);
        return listEditData;
    }

    public void setListEditData(List<DataStore> listEditData) {
        this.listEditData = listEditData;
    }

    /**
     * Event commobox cate album choice
     *
     * @param e
     */
    public void cateAlbumValueChange(ValueChangeEvent e) {
        this.idAlbum = e.getNewValue().toString();
    }

    public String returnPublicData(String s) {
        if (s.trim().equals("true")) {
            return "User";
        } else {
            return "Guest";
        }
    }

    /**
     * Method update User or Guest permission for data of dvd disc
     *
     * @param id
     */
    public void updateUserGuest(String id) {
        if (this.datahand.UpdateUseGuest(id) == true) {
            this.message = dvd.libraries.UImessage.generalMessage("blue", "You Action Success", "", "");
        } else {
            this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "",
                    "Please try again !");
        }
    }
}
