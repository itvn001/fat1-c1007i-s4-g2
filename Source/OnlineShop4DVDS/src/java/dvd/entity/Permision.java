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
public class Permision {

    @Column(name = "PerID")
    @Id
    private int perID;
    @Column(name = "PerName")
    private String perName;

    public int getPerID() {
        return perID;
    }

    public void setPerID(int perID) {
        this.perID = perID;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }
}
