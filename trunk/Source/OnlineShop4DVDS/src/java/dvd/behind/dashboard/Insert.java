/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.libraries.UImessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Insert implements Serializable {
    public Insert(){
        
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    private String number;

    public Insert(String sname, String snumber) {
        this.name = sname;
        this.number = snumber;

    }
    private String message;

    public String getMessage() {
        this.message = UImessage.generalMessage("blue", "This message displaying", "", "");
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    private List<Insert> cars;

    public List<Insert> getCars() {
        this.cars = new ArrayList<Insert>();
        this.cars.add(new Insert("Honda", "123"));
        this.cars.add(new Insert("Yamaha", "121"));
        this.cars.add(new Insert("Yoyouta", "434"));
        this.cars.add(new Insert("Plonma", "656"));
        this.cars.add(new Insert("Yahoo", "423"));
        this.cars.add(new Insert("Acn", "562"));
        this.cars.add(new Insert("Anova", "2334"));
        return cars;
    }

    public void setCars(List<Insert> cars) {
        this.cars = cars;
    }
}
