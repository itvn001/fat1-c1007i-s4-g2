/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Administrator
 */
public class Encryption {

    private Cipher encrypt;
    private DESKeySpec keyspec;
    private SecretKeyFactory keyfactory;
    private SecretKey key;
    private Encryption coding;

    public Encryption() {
        this.encrypt = null;
        this.keyspec = null;
        this.keyfactory = null;
        this.key = null;
        this.coding = null;
    }
    //This method JoinString when String input <8 char

    public String JoinString(String s) {
        String str2 = "!0oSn8*z";
        return s.concat(str2);
    }

    public Encryption(SecretKey key) {
        try {
            this.encrypt = Cipher.getInstance("DES");
            this.encrypt.init(Cipher.ENCRYPT_MODE, key);
        } catch (Exception ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String encrypt(String str) throws Exception {
        byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = this.encrypt.doFinal(utf8);
        return new sun.misc.BASE64Encoder().encode(enc);
    }
    //Mehod Descrip_pass and return 1 String has Description
    public String Encript_Pass(String pass) {
        if (pass.length() < 8)//if length <8 then join String for = 8.--> to decrip with EncripKeySpec 
        {
            try {
                keyspec = new DESKeySpec(JoinString(pass).getBytes());//key password user

                keyfactory = SecretKeyFactory.getInstance("DES");//Initialization typt Coding

                key = keyfactory.generateSecret(keyspec);//Create key
                coding = new Encryption(key);
                return coding.encrypt(pass);
            } catch (Exception ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else//
        {
            try //
            {
                keyspec = new DESKeySpec(pass.getBytes());

                keyfactory = SecretKeyFactory.getInstance("DES");//Initialization typt Coding

                key = keyfactory.generateSecret(keyspec);//Create key

                coding = new Encryption(key);

                return coding.encrypt(pass);
                // System.out.println(coding.encrypt(pass));
            } catch (Exception ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }
    public static void main(String[]a){
        Encryption en = new Encryption();
        System.out.println(en.Encript_Pass("demo"));
    }
}
