/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categories {

    @Column(name = "CateID")
    @Id
    private int cateID;
    @Column(name = "CateTypeID")
    private String cateTypeID;
    @Column(name = "CateName")
    private String cateName;
    @Column(name = "CateStatus")
    private Boolean cateStatus;
    @Column(name = "CateTypeName")
    private String cateTypeName;

    public String getCateTypeName() {
        return cateTypeName;
    }

    public void setCateTypeName(String cateTypeName) {
        this.cateTypeName = cateTypeName;
    }
    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateTypeID() {
        return cateTypeID;
    }

    public void setCateTypeID(String cateTypeID) {
        this.cateTypeID = cateTypeID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Boolean getCateStatus() {
        return cateStatus;
    }

    public void setCateStatus(Boolean cateStatus) {
        this.cateStatus = cateStatus;
    }
}