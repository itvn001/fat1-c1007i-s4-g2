package dvd.libraries;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class HandlingBusiness {

    private Connection cn = null;
    private PreparedStatement ps;
    private CallableStatement cs;
    private int result;

    /**
     * Constructor Initial all field
     */
    public HandlingBusiness() {
        this.result = 0;
        this.ps = null;
        this.cs = null;
        this.cn = new Connection();
    }

    /**
     * Method insert to database
     *
     * @param nameproc name proc to insert
     * @param symbolParam string values for example: '?,?,?' with 3 values to
     * insert
     * @param paramnumber array parameter first
     * @param paramvalues array parameter second
     * @return if true insert done, else failed
     */
    public Boolean InsertToDB(String nameproc, String symbolParam, int[] paramnumber, String[] paramvalues) {
        return SetSameMethod(nameproc, symbolParam, paramnumber, paramvalues);
    }

    /**
     * Method Delete to database
     *
     * @param nameproc name proc to Delete
     * @param symbolParam string values for example: '?,?,?' with 3 values to
     * insert
     * @param paramnumber array parameter first
     * @param paramvalues array parameter second
     * @return if true insert done, else failed
     */
    public Boolean DeleteToDB(String nameproc, String symbolParam, int[] paramnumber, String[] paramvalues) {
        return SetSameMethod(nameproc, symbolParam, paramnumber, paramvalues);
    }

    /**
     * Method Edit to database
     *
     * @param nameproc name proc to Edit
     * @param symbolParam string values for example: '?,?,?' with 3 values to
     * insert
     * @param paramnumber array parameter first
     * @param paramvalues array parameter second
     * @return if true insert done, else failed
     */
    public Boolean UpdateToDB(String nameproc, String symbolParam, int[] paramnumber, String[] paramvalues) {
        return SetSameMethod(nameproc, symbolParam, paramnumber, paramvalues);
    }

    private Boolean SetSameMethod(String nameproc, String symbolParam, int[] paramnumber, String[] paramvalues) {
        try {
            String squery = "{call " + nameproc + "(" + symbolParam + ")}";
            this.ps = cn.GetConnect().prepareCall(squery);
            int i1 = 1;
            for (int i = 0; i < paramnumber.length; i++) {
                this.ps.setString(i1, paramvalues[i]);
                i1++;
            }
            this.result = this.ps.executeUpdate();
            return getResult(this.result);
        } catch (SQLException ex) {
            Logger.getLogger(HandlingBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    private Boolean getResult(int result) {
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] f) {
        HandlingBusiness g = new HandlingBusiness();
        int[] pa = new int[2];
        String[] pav = new String[]{
            "Ten dah muc",
            "Thong tin"
        };
        g.InsertToDB("addcate", "?,?", pa, pav);
    }
}
