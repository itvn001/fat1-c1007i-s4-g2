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
public class Categories {
    
    private dvd.libraries.MapperData<dvd.entity.Categories> mapperCommo;
    public Categories() {
        this.mapperCommo = new MapperData<dvd.entity.Categories>();
    }
    public List<dvd.entity.Categories> listCategories() {
        return this.mapperCommo.getDataWithView("aShowCategories", dvd.entity.Categories.class);
    }
}
