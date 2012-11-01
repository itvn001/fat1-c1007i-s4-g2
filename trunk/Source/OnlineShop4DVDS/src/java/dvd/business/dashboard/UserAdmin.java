/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.dashboard;

import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class UserAdmin {
    private dvd.libraries.MapperData<dvd.entity.UserAdmin> Uhand;
    private dvd.libraries.HandlingBusiness hand;
    private String[] paramvalues;
    public UserAdmin(){
        this.hand = null;
        this.paramvalues = null;
    }
    public List<dvd.entity.UserAdmin> login(String username,String password){
        try{
            this.paramvalues = new String[]{
                username,
                password
            };
            this.Uhand = new MapperData<dvd.entity.UserAdmin>();
            return this.Uhand.getDataWithProc("aLogin", "?,?", this.paramvalues, dvd.entity.UserAdmin.class);
        }
        catch(Exception e){
            return null;
        }
    }
}
