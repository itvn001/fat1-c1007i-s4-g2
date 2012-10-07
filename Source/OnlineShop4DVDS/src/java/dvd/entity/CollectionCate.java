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
public class CollectionCate {

    @Column(name = "CollecCateID")
    @Id
    private int collecCateID;
    @Column(name = "CollectCateName")
    private String collectCateName;
    @Column(name = "UserID")
    private int userID;
    @Column(name = "DateCreate")
    private String dateCreate;

    public int getCollecCateID() {
        return collecCateID;
    }

    public void setCollecCateID(int collecCateID) {
        this.collecCateID = collecCateID;
    }

    public String getCollectCateName() {
        return collectCateName;
    }

    public void setCollectCateName(String collectCateName) {
        this.collectCateName = collectCateName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
