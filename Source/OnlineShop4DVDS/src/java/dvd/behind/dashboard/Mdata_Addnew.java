/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Mdata_Addnew {

    private String idAlbum = "";

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Mdata_Addnew() {
    }

    public void cateAlbumValueChange(ValueChangeEvent e) {
        this.idAlbum = e.getNewValue().toString();
    }
}
