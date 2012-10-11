/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.business.client;

import dvd.entity.Categories;
import dvd.libraries.MapperData;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CategoryManager {
    
    private List<dvd.entity.Categories> ListCate;
    private MapperData<dvd.entity.Categories> map = new MapperData<Categories>();
    public List<Categories> showListCate() {
        this.ListCate = map.getDataWithView("showAllCategory", Categories.class);
        return ListCate;
    }

    public void setListCate(List<Categories> ListCate) {
        this.ListCate = ListCate;
    }
    
}
