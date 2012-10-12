/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.entity.Album;
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
    private String idAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }
    private List<SelectItem> listCateAlbum;
    private dvd.business.dashboard.Album albumhand = new dvd.business.dashboard.Album();

    public List<SelectItem> getListCateAlbum() {
        return listCateAlbum;
    }

    public void setListCateAlbum(List<SelectItem> listCateAlbum) {
        this.listCateAlbum = listCateAlbum;
    }

    public Mdata_Index() {
        this.listCateAlbum = new ArrayList<SelectItem>();
        List<dvd.entity.Album> lista = this.albumhand.getCateAlbum();
        for (Album album : lista) {
            this.listCateAlbum.add(new SelectItem(album.getAlbumID(), album.getAlbumName()));
        }
    }
    /**
     * Event commobox cate  album choice
     * @param e 
     */
    public void cateAlbumValueChange(ValueChangeEvent e) {
        this.idAlbum = e.getNewValue().toString();
    }
}
