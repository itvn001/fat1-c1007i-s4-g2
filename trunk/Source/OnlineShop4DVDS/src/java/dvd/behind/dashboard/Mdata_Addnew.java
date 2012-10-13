/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.DataStore;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Mdata_Addnew {

    private String idAlbum = "";
    private List<SelectItem> listuserguest = new ArrayList<SelectItem>();
    private dvd.business.dashboard.DataStore dataHand = null;

    public String getIdAlbum() {
        return idAlbum;
    }
    private String typeGuestUser;

    public String getTypeGuestUser() {
        return typeGuestUser;
    }

    public void setTypeGuestUser(String typeGuestUser) {
        this.typeGuestUser = typeGuestUser;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Mdata_Addnew() {
        this.listuserguest.add(new SelectItem("true", "Guest"));
        this.listuserguest.add(new SelectItem("false", "Users"));
    }

    public void cateAlbumValueChange(ValueChangeEvent e) {
        this.idAlbum = e.getNewValue().toString();
    }

    public List<SelectItem> getListuserguest() {
        return listuserguest;
    }

    public void setListuserguest(List<SelectItem> listuserguest) {
        this.listuserguest = listuserguest;
    }

    public void userGuestChange(ValueChangeEvent e) {
        this.typeGuestUser = e.getNewValue().toString();
    }
    private String dataAlbumName = "";

    public String getDataAlbumName() {
        return dataAlbumName;
    }

    public void setDataAlbumName(String dataAlbumName) {
        this.dataAlbumName = dataAlbumName;
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method ad`d new Dvd Data to album
     */
    public void btnsavedata_Click() {
        try {
            //DVDStore/album/
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(true);
            String pathimageData = "";
            String pathda = "";
            if (session.getAttribute("image_sg") != null) {
                pathimageData = session.getAttribute("image_sg").toString();
            } else {
                pathimageData = "albumDefault.png";
            }
            if (session.getAttribute("path_da") != null) {
                pathda = session.getAttribute("path_da").toString();
            } else {
                pathda = "albumDefault.png";
            }
            this.dataHand = new DataStore();
            //Set data to class
            dvd.entity.DataStore dbs = new dvd.entity.DataStore();
            dbs.setAlbumID(Integer.parseInt(this.idAlbum));
            dbs.setAlbumName(this.dataAlbumName);
            dbs.setDataPath("DVDStore/album/" + pathda);
            Boolean useguest = true;
            if (this.typeGuestUser.equals("true")) {
                dbs.setDataPublic(true);
            } else if (this.typeGuestUser.equals("false")) {
                dbs.setDataPublic(false);
            }
            dbs.setDataImage("DVDStore/album/" + pathimageData);
            if (this.dataHand.CreateDataStore(dbs) == true) {
                this.message = dvd.libraries.UImessage.generalMessage("blue", "Create Data Success", "", "");
            } else {
                this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "", "Please try again !");
            }
        } catch (Exception e) {
        }
    }
}
