/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Album;
import dvd.business.dashboard.DataStore;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Mdvd_Edit {

    public Mdvd_Edit() {
    }
    HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private dvd.business.dashboard.Album albumhand = new Album();
    private dvd.business.dashboard.DataStore datastore = new DataStore();
    private List<dvd.entity.Album> listAlbumedit;
    String id = "";
    public List<dvd.entity.Album> getListAlbumedit() {
        try {
            listAlbumedit = new ArrayList<dvd.entity.Album>();
            id = (String) req.getParameter("sd");
            this.listAlbumedit = this.albumhand.getListAlbumEditForm(id);
            return listAlbumedit;
        } catch (Exception ex) {
            Logger.getLogger(Mdvd_Edit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setListAlbum(List<dvd.entity.Album> listAlbumedit) {
        this.listAlbumedit = listAlbumedit;
    }
    public List<dvd.entity.DataStore> listDataSrote;

    public List<dvd.entity.DataStore> getListDataSrote() {
        this.listDataSrote = this.datastore.getListDataStoreWAlbum(id);
        return listDataSrote;
    }

    public void setListDataSrote(List<dvd.entity.DataStore> listDataSrote) {
        this.listDataSrote = listDataSrote;
    }
}
