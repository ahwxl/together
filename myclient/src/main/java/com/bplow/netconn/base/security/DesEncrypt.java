package com.bplow.netconn.base.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class DesEncrypt {
	
	private SecretKey  bankSecretKey;
	
	public void init(){
		Base64 base64 = new Base64();//zTd5itOwEPj4+O9/Uez9L803eYrTsBD4  3/eAXseKV5QErmt1yDg08t/3gF7HileU
		//base64.decode("3/eAXseKV5QErmt1yDg08t/3gF7HileU".getBytes())
		byte[] secKeys =base64.decode("3/eAXseKV5QErmt1yDg08t/3gF7HileU".getBytes());
		System.out.println("对称秘钥是:"+new String(secKeys));
        bankSecretKey = new SecretKeySpec(secKeys, "DESede");
		
	}
	
    public byte[] encrypt(byte[] plaintext) {

        try {
            Cipher ecipher = Cipher.getInstance("DESede");
            ecipher.init(Cipher.ENCRYPT_MODE, bankSecretKey);
            return ecipher.doFinal(plaintext);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
	
	public byte[] decrypt(byte[] ciphertext) {

        try {
            Cipher ecipher = Cipher.getInstance("DESede");
            ecipher.init(Cipher.DECRYPT_MODE, bankSecretKey);
            return ecipher.doFinal(ciphertext); //解密   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
