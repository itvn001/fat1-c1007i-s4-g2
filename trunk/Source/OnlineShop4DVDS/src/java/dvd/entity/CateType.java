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
public class CateType {

    @Column(name = "CateTypeID")
    @Id
    private int cateTypeID;
    @Column(name = "CateTypeName")
    private String cateTypeName;

    public int getCateTypeID() {
        return cateTypeID;
    }

    public void setCateTypeID(int cateTypeID) {
        this.cateTypeID = cateTypeID;
    }

    public String getCateTypeName() {
        return cateTypeName;
    }

    public void setCateTypeName(String cateTypeName) {
        this.cateTypeName = cateTypeName;
    }
}
