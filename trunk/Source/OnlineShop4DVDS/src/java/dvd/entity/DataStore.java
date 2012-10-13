/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Administrator
 */
@Entity
public class DataStore {

    @Column(name = "DataID")
    @Id
    private int dataID;
    @Column(name = "AlbumID")
    private int albumID;
    @Column(name = "SupID")
    private int supID;
    @Column(name = "DataName")
    private String dataName;
    @Column(name = "DataPath")
    private String dataPath;
    @Column(name = "DataDescription")
    private String dataDescription;
    @Column(name = "DataPublic")
    private Boolean dataPublic;
    @Column(name = "DataStatus")
    private Boolean dataStatus;

    public int getDataID() {
        return dataID;
    }

    public void setDataID(int dataID) {
        this.dataID = dataID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getSupID() {
        return supID;
    }

    public void setSupID(int supID) {
        this.supID = supID;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription;
    }

    public Boolean getDataPublic() {
        return dataPublic;
    }

    public void setDataPublic(Boolean dataPublic) {
        this.dataPublic = dataPublic;
    }

    public Boolean getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Boolean dataStatus) {
        this.dataStatus = dataStatus;
    }
    @Column(name = "AlbumName")
    private String albumName;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    @Column(name = "DataImage")
    private String dataImage;

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }
}
