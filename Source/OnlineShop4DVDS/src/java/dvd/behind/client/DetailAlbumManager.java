/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.AlbumManager;
import dvd.business.client.CollectionManager;
import dvd.entity.Album;
import dvd.entity.CollectionCate;
import dvd.entity.DataStore;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class DetailAlbumManager {

    /**
     * Creates a new instance of DetailAlbumManager
     */
    private int albumId;
    private int UserId;
    private String firstPath;
    private String firstImage;
    private String firstTitle;
    private List<SelectItem> listItem;
    private String[] collectionPlaylist;
    private String playListNew;
    static private String message;
    static private boolean displayMessage;
    static private boolean typeMessage;
    private String titleAddtoList;
    AlbumManager albumManager;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public DetailAlbumManager() {
        titleAddtoList = "DataStore";
        try {
            if (session.getAttribute("UserId") != null && session.getAttribute("UserId") != "") {
                UserId = Integer.parseInt("" + session.getAttribute("UserId"));
            }
        } catch (Exception e) {
        }
    }

    public void resetMessage() {
        message = "";
        displayMessage = false;
    }
    private int countnumbers = 0;

    public int getCountnumbers() {
        countnumbers++;
        return countnumbers;
    }

    public void setCountnumbers(int _countnumbers) {
        countnumbers = _countnumbers++;
    }

    public void addAlbumID(int _albumID) {
        albumId = _albumID;
        if (_albumID != 0) {
            session.setAttribute("idPageOld", _albumID);
        }
    }

    public Album showInforAlbum() {
        Album album = new Album();
        try {
            albumManager = new AlbumManager();
            ResultSet rs = albumManager.showInforAlbum(albumId);
            if (rs.next()) {
                album.setAlbumID(rs.getInt(1));
                album.setCateID("" + rs.getInt(2));
                album.setAlbumName(rs.getString(3));
                album.setAlbumPrice("" + rs.getDouble(4));
                album.setAlbumDateCreate(rs.getString(5));
                album.setAlbumStatus(rs.getBoolean(6));
                album.setAlbumImage(rs.getString(7));
                album.setQuantity(rs.getInt(8));
                album.setCateName(rs.getString(9));
                album.setAlbumDetails(rs.getString(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return album;
    }

    public List<DataStore> showDataStore() {
        List<DataStore> listDS = null;
        try {
            albumManager = new AlbumManager();
            listDS = albumManager.showDataStore(albumId);
            firstImage = listDS.get(0).getDataImage();
            firstPath = listDS.get(0).getDataPath();
            firstTitle = listDS.get(0).getDataName();
        } catch (Exception e) {
        }
        return listDS;
    }

    public List<CollectionCate> listCollectionCate() {
        List<CollectionCate> listCCate = null;
        try {
            CollectionManager collectionManager = new CollectionManager();
            listCCate = collectionManager.listCollectionCate(UserId);
        } catch (Exception e) {
        }
        return listCCate;
    }

    public String showNameSuppllier() {
        try {
            albumManager = new AlbumManager();
            return albumManager.getNameSupplier(albumId);
        } catch (Exception e) {
        }
        return "No Suppllier";
    }

    public void listAlbumContentSave() {
        try {
            if (this.collectionPlaylist.length == 0) {
                displayMessage = true;
                message = "Not successfully added to the list! Because it is not already selected personal list";
                typeMessage = false;
                FacesContext.getCurrentInstance().getExternalContext().redirect("DetailsAlbum.xhtml?id=" + session.getAttribute("idPageOld"));
            } else {
                CollectionManager cm = new CollectionManager();
                if (titleAddtoList.equals("Album")) {
                    AlbumManager am = new AlbumManager();
                    List<DataStore> listDS = am.showDataStore(Integer.parseInt("" + session.getAttribute("idPageOld")));
                    for (DataStore dataStore : listDS) {
                        for (String cp : collectionPlaylist) {
                            if (!cm.checkExistDataStore(Integer.parseInt(cp), dataStore.getDataID())) {
                                cm.listContentSave(Integer.parseInt(cp), dataStore.getDataID());
                            }
                        }
                    }
                } else {
                    for (String cp : collectionPlaylist) {
                        if (!cm.checkExistDataStore(Integer.parseInt(cp), Integer.parseInt(titleAddtoList))) {
                            cm.listContentSave(Integer.parseInt(cp), Integer.parseInt(titleAddtoList));
                        }
                    }
                }
            }
            displayMessage = true;
            message = "Add to my list success";
            typeMessage = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("DetailsAlbum.xhtml?id=" + session.getAttribute("idPageOld"));
        } catch (IOException ex) {
            Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewDataStore() {
        try {
            CollectionManager cm = new CollectionManager();
            if (cm.addNewDataStore(playListNew, UserId)) {
                int newCCId = cm.listCollectionCateNew(UserId);
                if (newCCId > 0) {
                    if (titleAddtoList.equals("Album")) {
                        AlbumManager am = new AlbumManager();
                        List<DataStore> listDS = am.showDataStore(Integer.parseInt("" + session.getAttribute("idPageOld")));
                        for (DataStore dataStore : listDS) {
                            if (!cm.checkExistDataStore(newCCId, dataStore.getDataID())) {
                                cm.listContentSave(newCCId, dataStore.getDataID());
                            }
                        }
                    } else {
                        if (!cm.checkExistDataStore(newCCId, Integer.parseInt(titleAddtoList))) {
                            cm.listContentSave(newCCId, Integer.parseInt(titleAddtoList));
                        }
                    }
                    displayMessage = true;
                    message = "Create new list success";
                    typeMessage = true;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("DetailsAlbum.xhtml?id=" + session.getAttribute("idPageOld"));
                } else {
                    displayMessage = true;
                    message = "Create new list false!";
                    typeMessage = false;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("DetailsAlbum.xhtml?id=" + session.getAttribute("idPageOld"));
                }
            }
        } catch (Exception e) {
            try {
                displayMessage = true;
                message = "Create new list false!";
                typeMessage = false;
                FacesContext.getCurrentInstance().getExternalContext().redirect("DetailsAlbum.xhtml?id=" + session.getAttribute("idPageOld"));
            } catch (IOException ex) {
                Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the AlbumId
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * @param AlbumId the AlbumId to set
     */
    public void setAlbumId(int AlbumId) {
        this.albumId = AlbumId;
    }

    /**
     * @return the firstPath
     */
    public String getFirstPath() {
        return firstPath;
    }

    /**
     * @param firstPath the firstPath to set
     */
    public void setFirstPath(String firstPath) {
        this.firstPath = firstPath;
    }

    /**
     * @return the firstImage
     */
    public String getFirstImage() {
        return firstImage;
    }

    /**
     * @param firstImage the firstImage to set
     */
    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    /**
     * @return the firstTitle
     */
    public String getFirstTitle() {
        return firstTitle;
    }

    /**
     * @param firstTitle the firstTitle to set
     */
    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    /**
     * @return the collectionPlaylist
     */
    public String[] getCollectionPlaylist() {
        return collectionPlaylist;
    }

    /**
     * @param collectionPlaylist the collectionPlaylist to set
     */
    public void setCollectionPlaylist(String[] collectionPlaylist) {
        this.collectionPlaylist = collectionPlaylist;
    }

    /**
     * @return the listItem
     */
    public List<SelectItem> getListItem() {
        try {
            listItem = new ArrayList<SelectItem>();
            List<CollectionCate> listCC = listCollectionCate();
            for (CollectionCate cc : listCC) {
                SelectItem item = new SelectItem(cc.getCollecCateID(), cc.getCollectCateName());
                listItem.add(item);
            }
        } catch (Exception e) {
        }
        return listItem;
    }

    /**
     * @param listItem the listItem to set
     */
    public void setListItem(List<SelectItem> listItem) {
        this.listItem = listItem;
    }

    /**
     * @return the playListNew
     */
    public String getPlayListNew() {
        return playListNew;
    }

    /**
     * @param playListNew the playListNew to set
     */
    public void setPlayListNew(String playListNew) {
        this.playListNew = playListNew;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the displayMessage
     */
    public boolean isDisplayMessage() {
        return displayMessage;
    }

    /**
     * @param displayMessage the displayMessage to set
     */
    public void setDisplayMessage(boolean displayMessage) {
        this.displayMessage = displayMessage;
    }

    /**
     * @return the typeMessage
     */
    public boolean isTypeMessage() {
        return typeMessage;
    }

    /**
     * @param typeMessage the typeMessage to set
     */
    public void setTypeMessage(boolean typeMessage) {
        this.typeMessage = typeMessage;
    }

    /**
     * @return the titleAddtoList
     */
    public String getTitleAddtoList() {
        return titleAddtoList;
    }

    /**
     * @param titleAddtoList the titleAddtoList to set
     */
    public void setTitleAddtoList(String titleAddtoList) {
        this.titleAddtoList = titleAddtoList;
    }
}
