/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

/**
 *
 * @author Administrator
 */
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.beanutils.BeanUtils;

public class MapperData<T> {

    private Connection cn = null;
    private ResultSet rs;
    private PreparedStatement ps;

    public MapperData() {
        this.rs = null;
        this.cn = new Connection();
    }

    public List<T> getDataWithProc(String nameproc, String symbolParam,
            int[] paramnumber, String[] paramvalues, Class outputClass) {
        ResultSet rs1 = SetProcBusiness(nameproc, symbolParam, paramnumber, paramvalues);
        List<T> t2 = mapRersultSetToObject(rs1, outputClass);
        return t2;
    }
    /**
     * get list data with view query from sql
     * @param nameView name view
     * @param outputClass name class with list output
     * @return List data with generic List<T>
     */
    public List<T> getDataWithView(String nameView, Class outputClass) {
        ResultSet rs2 = null;
        try 
        {
            String squey = "SELECT * FROM " + nameView;
            this.ps = cn.GetConnect().prepareCall(squey);
            rs2 = this.ps.executeQuery();
            List<T> t1 = mapRersultSetToObject(rs2, outputClass);
            return t1;
        } 
        catch (SQLException ex) {
            Logger.getLogger(HandlingBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private ResultSet SetProcBusiness(String nameproc, String symbolParam, int[] paramnumber, String[] paramvalues) {
        try {
            String squery = "{call " + nameproc + "(" + symbolParam + ")}";
            this.ps = cn.GetConnect().prepareCall(squery);
            int i1 = 1;
            for (int i = 0; i < paramnumber.length; i++) {
                this.ps.setString(i1, paramvalues[i]);
                i1++;
            }
            this.rs = this.ps.executeQuery();
            return this.rs;
        } catch (SQLException ex) {
            Logger.getLogger(HandlingBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Method handling main
     * @param rs ResultSet from query data
     * @param outputClass name class
     * @return List object<T>
     */
    @SuppressWarnings("unchecked")
    private List<T> mapRersultSetToObject(ResultSet rs, Class outputClass) {
        List<T> outputList = null;
        try {
            // make sure resultset is not null
            if (rs != null) {
                // check if outputClass has 'Entity' annotation
                if (outputClass.isAnnotationPresent(Entity.class)) {
                    // get the resultset metadata
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // get all the attributes of outputClass
                    Field[] fields = outputClass.getDeclaredFields();
                    while (rs.next()) {
                        T bean = (T) outputClass.newInstance();
                        for (int _iterator = 0; _iterator < rsmd
                                .getColumnCount(); _iterator++) {
                            // getting the SQL column name
                            String columnName = rsmd
                                    .getColumnName(_iterator + 1);
                            // reading the value of the SQL column
                            Object columnValue = rs.getObject(_iterator + 1);
                            // iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
                            for (Field field : fields) {
                                if (field.isAnnotationPresent(Column.class)) {
                                    Column column = field
                                            .getAnnotation(Column.class);
                                    if (column.name().equalsIgnoreCase(
                                            columnName)
                                            && columnValue != null) {
                                        BeanUtils.setProperty(bean, field.getName(), columnValue);
                                        break;
                                    }
                                }
                            }
                        }
                        if (outputList == null) {
                            outputList = new ArrayList<T>();
                        }
                        outputList.add(bean);
                    }

                } else {
                    // throw some error
                }
            } else {
                return null;
            }
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MapperData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException e) {
        } catch (SQLException e) {
        } catch (InstantiationException e) {
        }
        return outputList;
    }
}
