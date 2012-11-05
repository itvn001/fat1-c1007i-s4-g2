/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.entity.DataStore;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Mdata_Edit {

    /**
     * Creates a new instance of Mdata_Edit
     */
    public Mdata_Edit() {
    }
    private dvd.business.dashboard.DataStore daHand = null;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void btnsavedataEdit(int id, String _linkimage, String _pathSong) {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            Map<String, String[]> requestParameterValuesMap =
                    ctx.getExternalContext().getRequestParameterValuesMap();
            String imagelink;
            String pathSong;
            String[] get = requestParameterValuesMap.get("txtnamedata");
            if (session.getAttribute("imageLink") == null) {
                imagelink = _linkimage;
            } else {
                imagelink = session.getAttribute("imageLink").toString();
            }
            if (session.getAttribute("pathSong") == null) {
                pathSong = _pathSong;
            } else {
                pathSong = session.getAttribute("pathSong").toString();
            }
            String name = get[0];
            //Set data to class
            dvd.entity.DataStore dt = new DataStore();
            dt.setDataID(id);
            dt.setDataName(name);
            dt.setDataImage("DVDStore/data/" + imagelink);
            dt.setDataPath("DVDStore/data/" + pathSong);
            // Initial
            this.daHand = new dvd.business.dashboard.DataStore();
            // Call method excute
            this.daHand.UpdateDataStore(dt);
            this.message = dvd.libraries.UImessage.generalMessage("blue", "Update Data Success", "", "");
        } catch (Exception e) {
            this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "", "Please try again!");
        }
    }
}
