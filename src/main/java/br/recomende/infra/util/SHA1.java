package br.recomende.infra.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that generates hashes for SHA-1.
 * 
 */
public class SHA1 {
	
	private static final String method = "SHA-1";
	
	private static final String HEX_DIGITS = "0123456789abcdef";
	
	/**
	 * Transform the generated hash by digester into a hash on string format
	 * composed by hexadecimals digits. 
	 * 
	 * @param bytes hash in byte array format
	 * @return hash in string format.
	 */
	private static String byteArrayToHexDigits(byte[] bytes) {
  	    StringBuilder hash = new StringBuilder(40);
 	    for (int i = 0; i < bytes.length; i++) {
 	    	int j = ((int) bytes[i]) & 0xFF;
 			hash.append(HEX_DIGITS.charAt(j /16));
 			hash.append(HEX_DIGITS.charAt(j % 16));
 	    }
 	    
 	    return hash.toString();
	}
	
	/**
	 * Generates the hash of a string using the chosen method.
	 * 
	 * @param message string to generate hash.
	 * @return hash of string passed.
	 */
	public static String crypt(String message) {
		if (message != null) {
			return crypt(message.getBytes());
		}
		return null;
	}
	
	/**
	 * Generates the hash of a file using the chosen method.
	 * 
	 * @param file file to generate hash.
	 * @return hash of file passed.
	 * @throws IOException if the file doesn't exist.
	 */
	public static String crypt(File file) throws IOException {		
    	try {    		
    		InputStream in = new FileInputStream(file);
    		BufferedInputStream buffer = new BufferedInputStream(in);
    		
      		MessageDigest md = MessageDigest.getInstance(method);
      		
     	    md.reset();
     	      	    
     	    int nBytes;
	   		byte[] buf = new byte[20240];			
	   		while ((nBytes = buffer.read(buf)) > 0) {
	   			md.update(buf, 0, nBytes);
	   		}
	   		
     	    return byteArrayToHexDigits(md.digest());
     	    
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
	}
	
	/**
	 * Generates the hash of a byte array using the chosen method.
	 * 
	 * @param bytes byte array to generate hash.
	 * @return hash of byte array.
	 */
	public static String crypt(byte[] bytes) {
    	try {
      		MessageDigest md = MessageDigest.getInstance(method);
      		
     	    md.reset();
     	    
     	    return byteArrayToHexDigits(md.digest(bytes));
     	    
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	
        return null;
	}
	
}