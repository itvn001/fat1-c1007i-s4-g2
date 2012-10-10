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
public class Mdvd_Addnew {

    private List<SelectItem> listCategories;
    private dvd.business.dashboard.Categories categories = new dvd.business.dashboard.Categories();
    private dvd.business.dashboard.Album albumhand = null;
    // Message Flag
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Mdvd_Addnew() {
        List<dvd.entity.Categories> lis = this.categories.listCategories();
        this.listCategories = new ArrayList<SelectItem>();
        this.listCategories.add(new SelectItem(0, "--select--"));
        for (dvd.entity.Categories catelist : lis) {
            this.listCategories.add(new SelectItem(catelist.getCateID(),
                    catelist.getCateName()));
        }
        //Add data to List Quantity
        this.listQuantity = new ArrayList<SelectItem>();
        for (int i = 1; i < 100; i++) {
            this.listQuantity.add(new SelectItem(i, i + ""));
        }
    }
    private String idCategories = "0";

    public String getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(String idCategories) {
        this.idCategories = idCategories;
    }

    public List<SelectItem> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List<SelectItem> listCategories) {
        this.listCategories = listCategories;
    }

    public void categoriesValuesChange(ValueChangeEvent e) {
        this.idCategories = e.getNewValue().toString();
    }
    private String numberQuantity = "";

    public String getNumberQuantity() {
        return numberQuantity;
    }

    public void setNumberQuantity(String numberQuantity) {
        this.numberQuantity = numberQuantity;
    }

    public void quantityListChange(ValueChangeEvent e) {
        this.numberQuantity = e.getNewValue().toString();

    }
    private List<SelectItem> listQuantity;

    public List<SelectItem> getListQuantity() {
        return listQuantity;
    }

    public void setListQuantity(List<SelectItem> listQuantity) {
        this.listQuantity = listQuantity;
    }
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    public void btnumaddnew_Click() {
        try {
            dvd.entity.Album album = new Album();
            String filename = "";
            if (session.getAttribute("se_nameonlyBean") != null) {
                filename = session.getAttribute("se_nameonlyBean").toString();
            }
            //Set class to album
            album.setCateID(this.idCategories);
            album.setAlbumName(this.albumName);
            album.setAlbumPrice(this.albumPrice);
            album.setQuantity(Integer.parseInt(this.numberQuantity));
            album.setAlbumImage("DVDStore/album/" + filename);
            this.albumhand = new dvd.business.dashboard.Album();
            // Excute create record
            if(this.albumhand.CreateAlbum(album) ==true){
                this.message = dvd.libraries.UImessage.generalMessage("blue", "Create New Album Success", "", "");
            }else{
                this.message = dvd.libraries.UImessage.generalMessage("red", "Add Fail", "#", "please try again!");
            }
        } catch (Exception e) {
        }
    }
    private String albumName;
    private String albumPrice;
    private String filestr;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(String albumPrice) {
        this.albumPrice = albumPrice;
    }

    public String getFilestr() {
        return filestr;
    }

    public void setFilestr(String filestr) {
        this.filestr = filestr;
    }
}
