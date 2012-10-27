/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.dashboard;

import dvd.entity.FeedBack;
import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Feedback {

    private dvd.libraries.MapperData<dvd.entity.FeedBack> feeMap = new MapperData<FeedBack>();
    private String[] pvalue;

    public Feedback() {
        this.pvalue = null;
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
}
