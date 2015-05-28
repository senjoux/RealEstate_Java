/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Technique;

/**
 *
 * @author FATHLLAH Wael
 */
import java.security.MessageDigest;

public class MD5 {
 
    public MD5() {
    }
 
    public String hexStringFromBytes(byte[] b) {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String hex = "";
        int msb;
        int lsb = 0;
        int i;
 
        for (i = 0; i < b.length; i++) {
            msb = ((int) b[i] & 0x000000FF) / 16;
            lsb = ((int) b[i] & 0x000000FF) % 16;
            hex = hex + hexChars[msb] + hexChars[lsb];
        }
        return hex;
    }
 
    

     public static void main(String[] args) throws Exception
  { 
      MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
         
        // get md5 for word "PASSWORD"
        digest.update("PASSWORD".getBytes());
        byte[] passwordBytes = digest.digest();
        System.out.println(bytesToHex(passwordBytes));
        //319F4D26E3C536B5DD871BB2C52E3178
        //319F4D26E3C536B5DD871BB2C52E3178
  }
     final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
     public static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for ( int j = 0; j < bytes.length; j++ ) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
}
}