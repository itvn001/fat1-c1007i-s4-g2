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
public class Users {
    private dvd.libraries.MapperData<dvd.entity.Users> userHand = new MapperData<dvd.entity.Users>();
    private String[] paramvalues;
    public Users(){
        this.paramvalues = null;
    }
    public List<dvd.entity.Users> listUser(){
        try{
            this.paramvalues = new String[]{"1"};
            return this.userHand.getDataWithProc("aShowUser", "?", this.paramvalues, dvd.entity.Users.class);
        }
        catch(Exception e){
            return null;
        }
    }
    public List<dvd.entity.Users> listDetailsUser(String id){
        try{
            this.paramvalues = new String[]{id};
            return this.userHand.getDataWithProc("aShowUserDetails", "?", this.paramvalues, dvd.entity.Users.class);
        }
        catch(Exception e){
            return null;
        }
    }
}
