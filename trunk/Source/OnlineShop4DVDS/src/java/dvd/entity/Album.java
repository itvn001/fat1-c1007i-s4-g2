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
public class Album {

    @Column(name = "AlbumID")
    @Id
    private int albumID;
    @Column(name = "CateID")
    private String cateID;
    @Column(name = "CateName")
    private String cateName;

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    @Column(name = "AlbumName")
    private String albumName;
    @Column(name = "AlbumPrice")
    private String albumPrice;
    @Column(name = "AlbumDateCreate")
    private String albumDateCreate;
    @Column(name = "AlbumStatus")
    private Boolean albumStatus;
    @Column(name = "AlbumImage")
    private String albumImage;
    @Column(name = "Quantity")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

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

    public String getAlbumDateCreate() {
        return albumDateCreate;
    }

    public void setAlbumDateCreate(String albumDateCreate) {
        this.albumDateCreate = albumDateCreate;
    }

    public Boolean getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(Boolean albumStatus) {
        this.albumStatus = albumStatus;
    }
}
