/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

/**
 *
 * @author Administrator
 */
public class Encryption {

    private static String algorithm = "DESede";
    private static Key key = null;
    private static Cipher cipher = null;

    private static void setUp() throws Exception {
        key = KeyGenerator.getInstance(algorithm).generateKey();
        cipher = Cipher.getInstance(algorithm);
    }

    public static void main(String[] args)
            throws Exception {
        setUp();
        System.out.println(encrypt("09"));
    }

    private static byte[] encrypt(String input)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = input.getBytes();
        return cipher.doFinal(inputBytes);
    }

    private static String decrypt(byte[] encryptionBytes)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] recoveredBytes =
                cipher.doFinal(encryptionBytes);
        String recovered =
                new String(recoveredBytes);
        return recovered;
    }
}
