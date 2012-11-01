/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.dashboard;

import dvd.entity.FeedBack;
import dvd.libraries.HandlingBusiness;
import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Feedback {

    private dvd.libraries.MapperData<dvd.entity.FeedBack> feeMap = new MapperData<FeedBack>();
    private dvd.libraries.HandlingBusiness handB;
    private String[] pvalue;

    public Feedback() {
        this.pvalue = null;
        this.handB = null;
    }

    public int showCountComment(String id) {
        try {
            this.pvalue = new String[]{id};
            List<dvd.entity.FeedBack> listfe = 
                     feeMap.getDataWithProc("aShowCommentWdata", "?", this.pvalue, dvd.entity.FeedBack.class);
            for (dvd.entity.FeedBack object : listfe) {
                return object.getCountcm();
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }
    public List<dvd.entity.FeedBack> showListAllComment(String id){
        try{
            this.pvalue = new String[]{id};
            return this.feeMap.getDataWithProc("aShowCommentAll", "?", pvalue, dvd.entity.FeedBack.class);
        }
        catch(Exception e){
            return null;
        }
    }
    public Boolean updateComment(String id,String status){
        try{
            this.pvalue = new String[]{id,status};
            this.handB = new HandlingBusiness();
            return this.handB.UpdateToDB("aUpdateComment", "?,?", this.pvalue);
        }
        catch(Exception e){
            return null;
        }
    }
    public Boolean deleteComment(String id){
        try{
            this.pvalue = new String[]{id};
            this.handB = new HandlingBusiness();
            return this.handB.DeleteToDB("aDeleteComment", "?", this.pvalue);
        }
        catch(Exception e){
            return null;
        }
    }
}
