/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Album;
import dvd.business.dashboard.DataStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    String id = "0";
    // Keep id albumID
    private static int albumID = 0;

    public List<dvd.entity.Album> getListAlbumedit() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            listAlbumedit = new ArrayList<dvd.entity.Album>();
            id = session.getAttribute("re_albumid").toString();
            this.listAlbumedit = this.albumhand.getListAlbumEditForm(id);
            // For to get id album
            for (dvd.entity.Album album : listAlbumedit) {
                albumID = album.getAlbumID();
            }
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
    public List<dvd.entity.DataStore> listAllDataStore;

    public List<dvd.entity.DataStore> getListAllDataStore() {
        this.listAllDataStore = this.datastore.getListAllDataStore();
        return listAllDataStore;
    }

    public void setListAllDataStore(List<dvd.entity.DataStore> listAllDataStore) {
        this.listAllDataStore = listAllDataStore;
    }

    /**
     * Method move from list album to data store
     */
    public void btnaddtoalbum_Click() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            Map<String, String[]> requestParameterValuesMap =
                    ctx.getExternalContext().getRequestParameterValuesMap();
            if (requestParameterValuesMap.get("listdatastore") != null) {
                String[] get = requestParameterValuesMap.get("listdatastore");
                String svalues = "";
                for (int i = 0; i < get.length; i++) {
                    svalues = get[i];
                    this.datastore.MoveListAlbumToStore(svalues);
                    this.message = dvd.libraries.UImessage.generalMessage("blue",
                            "Move To Store Album Success !", "", "");
                }
            } else {
                FacesMessage msg =
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please choice checkbox on LIST ALBUM Table", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            this.message = dvd.libraries.UImessage.generalMessage("red",
                    "Error System.", "Please try again!", "");
        }
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method move form store album to list album
     */
    public void btnstorealbum_Click() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            Map<String, String[]> requestParameterValuesMap =
                    ctx.getExternalContext().getRequestParameterValuesMap();
            if (requestParameterValuesMap.get("albumidstore") != null) {
                String[] get = requestParameterValuesMap.get("albumidstore");
                String svalues = "";
                for (int i = 0; i < get.length; i++) {
                    svalues = get[i];
                    this.datastore.MoveStoreToListAlbum(svalues, this.id);
                    this.message = dvd.libraries.UImessage.generalMessage("blue",
                            "Move To List Album Success !", "", "");
                }
            } else {
                FacesMessage msg =
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please choice checkbox on SONG STORE Table", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            this.message = dvd.libraries.UImessage.generalMessage("red",
                    "Error System.", "Please try again!", "");
        }
    }

    /**
     * Method Save data Album
     */
    public void btnsaveInfor_Click() throws Exception {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String, String[]> requestParameterValuesMap =
                ctx.getExternalContext().getRequestParameterValuesMap();
        //Get request form
        String[] get1 = requestParameterValuesMap.get("intxtnamealbum");
        String[] get2 = requestParameterValuesMap.get("intxtidAlbum");
        String[] get3 = requestParameterValuesMap.get("intxtpricealbum");
        String[] get4 = requestParameterValuesMap.get("intxtquantity");
        // Initial class
        dvd.entity.Album albs = new dvd.entity.Album();
        // Set data to class
        albs.setAlbumName(get1[0]);
        albs.setAlbumID(Integer.parseInt(get2[0]));
        albs.setAlbumPrice(get3[0]);
        albs.setQuantity(Integer.parseInt(get4[0]));
        // Action Update to database
        if (this.albumhand.UpdateAlbum(albs) == true) {
            this.message = dvd.libraries.UImessage.generalMessage("blue",
                    "Update Album Sucess!", "", "");
        }else{
              this.message = dvd.libraries.UImessage.generalMessage("red",
                    "Error System", "Please try again !", "");
        }
    }
}
