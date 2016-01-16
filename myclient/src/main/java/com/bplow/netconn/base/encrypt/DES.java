package com.bplow.netconn.base.encrypt;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {


    private static String       ALGORITHM = "DES";
    /** 加密模式*/
    private static String       EN_MODE   = "DES/ECB/NoPadding";

    /**
     * DES加密
     * 
     * @param src  源字节数据
     * @param key  密钥数组
     * @param transformation 模式
     * @param ivBytes 向量
     * @return
     */
    public static byte[] encrypt(byte[] src, byte[] key, String transformation, byte[] ivBytes) {
        byte[] cipherByte = null;
        try {
            AlgorithmParameterSpec iv = new IvParameterSpec(ivBytes);
            Key enkey = new SecretKeySpec(key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, enkey, iv);
            cipherByte = cipher.doFinal(src);
        } catch (Exception e) {
        }
        return cipherByte;
    }

    /**
     * DES解密
     * 
     * @param src  源字节数据
     * @param key  密钥数组
     * @param transformation 模式
     * @param ivBytes 向量
     * @return
     */
    public static byte[] decrypt(byte[] src, byte[] key, String transformation, byte[] ivBytes) {
        byte[] plainByte = null;
        try {
            AlgorithmParameterSpec iv = new IvParameterSpec(ivBytes);
            Key enkey = new SecretKeySpec(key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, enkey, iv);
            plainByte = cipher.doFinal(src);
        } catch (Exception e) {
        }
        return plainByte;
    }

    /**
     * 加密函数
     * 
     * @param data
     *            加密数据
     * @param key
     *            密钥
     * @return 返回加密后的数据
     */
    public static byte[] ECBencrypt(byte[] data, byte[] key) {

        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(EN_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }

        return null;
    }

    /**
     * 解密函数
     * 
     * @param data
     *            解密数据
     * @param key
     *            密钥
     * @return 返回解密后的数据
     */
    public static byte[] ECBdecrypt(byte[] data, byte[] key) {

        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(EN_MODE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }
}
