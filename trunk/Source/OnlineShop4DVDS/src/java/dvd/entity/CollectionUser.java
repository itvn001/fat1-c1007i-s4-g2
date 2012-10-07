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
public class CollectionUser {

    @Column(name = "CollectID")
    @Id
    private int collectID;
    @Column(name = "CollectionCate")
    private int collectionCate;
    @Column(name = "DataID")
    private int dataID;
    @Column(name = "AlbumID")
    private int albumID;

    public int getCollectID() {
        return collectID;
    }

    public void setCollectID(int collectID) {
        this.collectID = collectID;
    }

    public int getCollectionCate() {
        return collectionCate;
    }

    public void setCollectionCate(int collectionCate) {
        this.collectionCate = collectionCate;
    }

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
}
