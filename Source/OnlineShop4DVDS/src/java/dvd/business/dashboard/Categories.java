/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.dashboard;

import dvd.entity.CateType;
import dvd.libraries.HandlingBusiness;
import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Categories {

    private dvd.libraries.MapperData<dvd.entity.Categories> mapperCommo;
    private dvd.libraries.HandlingBusiness handB;
    private String[] paramvalues;

    public Categories() {
        this.paramvalues = null;
        this.handB = null;
        this.mapperCommo = new MapperData<dvd.entity.Categories>();
    }

    public List<dvd.entity.Categories> listCategories() {
        return this.mapperCommo.getDataWithView("aShowCategories", dvd.entity.Categories.class);
    }

    public List<dvd.entity.Categories> listCategories_Main(String id) {
        this.paramvalues = new String[]{id};
        return this.mapperCommo.getDataWithProc("aShowCategories_main", "?", this.paramvalues, dvd.entity.Categories.class);
    }

    public List<dvd.entity.CateType> listCateType() {
        this.paramvalues = new String[]{"1"};
        dvd.libraries.MapperData<dvd.entity.CateType> ltype = new MapperData<CateType>();
        return ltype.getDataWithProc("aShowCateType", "?", paramvalues, dvd.entity.CateType.class);
    }

    public Boolean publishCate(String id) {
        try {
            this.paramvalues = new String[]{id};
            this.handB = new HandlingBusiness();
            return this.handB.UpdateToDB("aChangePublishCategories", "?", paramvalues);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean CreateCategories(dvd.entity.Categories bs) {
        try {
            this.paramvalues = new String[]{
                bs.getCateTypeID(),
                bs.getCateName(),
                bs.getCateStatus() + ""
            };
            this.handB = new HandlingBusiness();
            return this.handB.InsertToDB("aInsertCategories", "?,?,?", paramvalues);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean UpdateCategories(dvd.entity.Categories bs) {
        try {
            this.paramvalues = new String[]{
                Integer.toString(bs.getCateID()),
                bs.getCateTypeID(),
                bs.getCateName(),
                bs.getCateStatus() + ""
            };
            this.handB = new HandlingBusiness();
            return this.handB.UpdateToDB("aUpdateCategories", "?,?,?,?", paramvalues);
        } catch (Exception e) {
            return false;
        }
    }
}
