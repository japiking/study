package com.checkpay.util;

import java.io.*;
import java.net.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;
import java.security.spec.*;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtil {
	
	
	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 
		                             0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
	
    public static byte[] EncryptAes256(String input, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        if(input == null || key == null || input.length() < 1 || key.length() < 1) {
            return null;
        }
        
        AlgorithmParameterSpec iv = new IvParameterSpec(ivBytes);        
        SecretKeySpec k = new SecretKeySpec(key.getBytes("UTF-8"), "AES");        
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, k, iv);
        
        byte[] inBytes = input.getBytes("UTF-8");
        byte[] encBytes = c.doFinal(inBytes);
       
        
        return encBytes;
    }
	
    public static String DecryptAes256(byte[] input, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        if(input == null || key == null || input.length < 1 || key.length() < 1) {
            return null;
        }
        
        AlgorithmParameterSpec iv = new IvParameterSpec(ivBytes);
        SecretKeySpec k = new SecretKeySpec(key.getBytes("UTF-8"), "AES");        
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, k, iv);
        
        String decString = new String(c.doFinal(input), "UTF-8");
        
        return decString;
    }      
    
    /**
     * Encryption with Base64 Encoding
     * @author  CHECKPAY Corporation
     * @date        Oct 15, 2014
     * @param input
     * @param key
     * @param urlencode
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String EncryptAesBase64(String input, String key, boolean urlencode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        if(input == null || key == null || input.length() < 1 || key.length() < 1) {
            return null;
        }
        
        AlgorithmParameterSpec iv = new IvParameterSpec(ivBytes);        
        SecretKeySpec k = new SecretKeySpec(key.getBytes("UTF-8"), "AES");        
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, k, iv);
        
        byte[] inBytes = input.getBytes("UTF-8");
        byte[] encBytes = c.doFinal(inBytes);

        String b64EncString = Base64.encodeBase64String(encBytes);
        
        return urlencode ? URLEncoder.encode(b64EncString, "UTF-8") : b64EncString;
    }
    
    /**
     * Decryption with Base64 Encoded String
     * @author CHECKPAY Corporation
     * @date    Oct 15, 2014
     * @param input
     * @param key
     * @param urldecode
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String DecryptAesBase64(String input, String key, boolean urldecode) throws Exception
    {

    	String decString 	= "";		//복호화된 데이터
    	boolean decryptFail = false;	//데이터 복호화 실패여부
    	Throwable err 		= null;
    	
    	input = input.replaceAll(" ", "+");
    	input = input.replaceAll("_", "/");
    	input = input.replaceAll("-", "+");
    	//URL디코딩 없이 데이터 복호화를 합니다.
    	try{
    		decString = DecryptAesBase64_2(input, key, false);
    	}catch(Exception e){
    		err 		= e;    		
    		decryptFail = true;			//데이터 복호화 실패
    	}    	
        
        
        //URL디코딩 없이 데이터 복호화시 에러가 발생할 경우,
    	//URL디코딩을 하고 데이터를 복호화 합니다.
    	if(decryptFail){
    		try{
        		decString = DecryptAesBase64_2(input, key, true);
        	}catch(Exception e){
        		if(urldecode){
        			e.printStackTrace();
        		}else{
        			err.printStackTrace();
        		}
        		throw e;
        	}
    	}
        return decString;
    }      
    /**
     * Decryption with Base64 Encoded String
     * @param input
     * @param key
     * @param urldecode
     * @param dv
     * @return
     * @throws Exception
     */
    public static String DecryptAesBase64_2(String input, String key, boolean urldecode) throws Exception{

    	String decString = "";
    	try{
    		if(input == null || key == null || input.length() < 1 || key.length() < 1) {
    			return null;
    		}
    		
    		AlgorithmParameterSpec iv = new IvParameterSpec(ivBytes);
    		SecretKeySpec k = new SecretKeySpec(key.getBytes("UTF-8"), "AES");        
    		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    		c.init(Cipher.DECRYPT_MODE, k, iv);
    		
    		String b64EncString = urldecode ? URLDecoder.decode(input, "UTF-8") : input;
    		
    		byte[] encBytes = Base64.decodeBase64(b64EncString);
    		
    		decString = new String(c.doFinal(encBytes), "UTF-8");
    		
    	}catch(Exception e){
    		throw e;
    	}
        
        return decString;
    }  
    /**
     * 
     * @author CHECKPAY Corporation
     * @date        Oct 15, 2014
     * @param input
     * @param key
     * @param method
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalStateException
     * @throws UnsupportedEncodingException
     */
    public static String getHmacSha256(String input, String key, boolean urlencode) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException
    {
        if(input == null || key == null || input.length() < 1 || key.length() < 1 ) {
            return null;
        }
        
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);
        
        byte[] inBytes = input.getBytes("UTF-8");
        byte[] encBytes = mac.doFinal(inBytes);
        String b64EncString = Base64.encodeBase64String(encBytes);
        
        return urlencode ? URLEncoder.encode(b64EncString, "UTF-8") : b64EncString;
    }
    
    /**
     * 
     * @author CHECKPAY Corporation
     * @date        Oct 6, 2014
     * @param skey - 비밀키값
     * @param data - 암호화+URL인코딩+BASE64인코딩 된 데이터 부분(앞 14자리는 제외)
     * @param hmac - 전달받은 HMAC 값
     * @return - true: 무결성 검증 완료 false: 오류
     */
    public static boolean VerifyMac(String skey, String data, String hmac, String vm, boolean urldecode) throws Exception {

    	boolean isSuccess 	= true;		//데이터 무결성 검증 성공 여부
    	Throwable err 		= null;
    	
    	//URL디코딩 없이 데이터 복호화를 합니다.
    	data = data.replaceAll(" ", "+");
    	data = data.replaceAll("_", "/");
    	data = data.replaceAll("-", "+");
    	hmac = hmac.replaceAll(" ", "+");
    	hmac = hmac.replaceAll("_", "/");
    	hmac = hmac.replaceAll("-", "+");
    	try{
    		isSuccess = VerifyMac2(skey, data, hmac, vm, false);
    	}catch(Exception e){
    		isSuccess = false;
    		err 		= e;    		
    	}
        
    	//URL디코딩 없이 데이터 복호화시 에러가 발생할 경우,
    	//URL디코딩을 하고 데이터를 복호화 합니다.
    	if(!isSuccess){
    		try{
    			isSuccess = VerifyMac2(skey, data, hmac, vm, true);
        	}catch(Exception e){
        		isSuccess = false;
        		if(urldecode){
        			e.printStackTrace();
        		}else{
        			err.printStackTrace();
        		}
        		throw e;
        	}
    	}
        return isSuccess;
    }
    /**
     * 
     * @author Yelopay Corporation
     * @date        Oct 6, 2014
     * @param skey - 비밀키값
     * @param data - 암호화+URL인코딩+BASE64인코딩 된 데이터 부분(앞 14자리는 제외)
     * @param hmac - 전달받은 HMAC 값
     * @return - true: 무결성 검증 완료 false: 오류
     */
    public static boolean VerifyMac2(String skey, String data, String hmac, String vm, boolean urldecode) throws Exception {

        String decryptedData = SecurityUtil.DecryptAesBase64(data, skey, urldecode);
        String checkHmac = SecurityUtil.getHmacSha256(decryptedData.substring(14, decryptedData.length()), skey, urldecode);
        checkHmac 	= URLDecoder.decode(checkHmac, "UTF-8");
        hmac 		= URLDecoder.decode(hmac, "UTF-8");
        if (hmac.equals(checkHmac))
            return true;
        
        return false;
    }
    /**
     * null 값 대체
     * @param objValue
     * @return
     */
    public static String null2void(Object objValue, String strDefValue){
    	String strReturn = "";

    	if(objValue == null) {
    		strReturn = strDefValue;
    	} else {
    		strReturn = objValue.toString();
    	}

    	return strReturn;
    }
    /**
     * null 값 대체
     * @param objValue
     * @return
     */
    public static String null2void(Object objValue) {
    	String strReturn = "";

    	if(objValue == null) { 
    		strReturn = "";
    	} else {
    		strReturn = objValue.toString();
    	}

    	return strReturn;
    }
    
    /**
     * URL Connection 연결하여 리턴 결과를
     * JSONObject로 받는다.     
     * @param url
     * @return
     */
    public static String connect(String url){
    	
    	HttpURLConnection con 		= null;                                                                                                                                                            
		BufferedWriter bwriter 		= null;                                                                                                                                                           
		DataInputStream in 			= null;                                                                                                                                                               
		ByteArrayOutputStream bout 	= null;                                                                                                                                                       
		try {                                                                                                                                                                                    
			URL req = new URL(url);                                                                                                                                    
			con = (HttpURLConnection)req.openConnection();                                                                                                                                       
			con.setConnectTimeout(2 * 60 * 1000);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", 			"text/html; charset=UTF-8");
			                                                                                                                                                                                     
			bwriter = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));                                                                                                         
			bwriter.flush();                                                                                                                                                                     
	
			in = new DataInputStream(con.getInputStream());                                                                                                                                      
			bout = new ByteArrayOutputStream();                                                                                                                                                  
	                                                                                                                                                                                             
	        while (true) {
		        byte[] buf = new byte[2048];
	            int n = in.read(buf);
	            if (n == -1) break;
	            bout.write(buf, 0, n);
	        }
	        bout.flush();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {                                                                                                                                                                                
				if ( bwriter != null ) bwriter.close();                                                                                                                                          
				if ( in != null ) in.close();                                                                                                                                                    
				if ( bout != null ) bout.close();                                                                                                                                                
				if ( con != null ) con.disconnect();                                                                                                                                             
			} catch(Exception se) {}                                                                                                                                                                                    
		}     
    	
    	return new String(bout.toByteArray());
    }
	
}
