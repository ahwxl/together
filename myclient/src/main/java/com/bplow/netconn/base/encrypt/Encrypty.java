package com.bplow.netconn.base.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.alibaba.common.lang.StringUtil;

@Component
public class Encrypty {
	
	/** 定义 加密算法*/
    private static final String ALGORITHM = "DESede";

    /** 密钥和版本分割符, 避免分割符与字符串冲突，这儿分割符比较奇怪 */
    private static final String SPLITER   = "#^^^^^#";


    public byte[] encrypt(byte[] plaintext) {
        try {
            Cipher c1 = Cipher.getInstance(ALGORITHM + "/ECB/PKCS5Padding");

            //由于密钥会更新，因此每次调用都需要重新获取
            SecretKey deskey = initKey();
            if (deskey == null) {
                return null;
            }

            c1.init(Cipher.ENCRYPT_MODE, deskey);

            byte[] encoded = c1.doFinal(plaintext);
            return encoded;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 解密
     * @see com.alipay.bcm.biz.certifier.AbstractCertifier#decrypt(byte[])
     */
    public byte[] decrypt(byte[] ciphertext) {
        try {

            Cipher c2 = Cipher.getInstance(ALGORITHM + "/ECB/PKCS5Padding");

            //由于密钥会更新，因此每次调用都需要重新获取
            SecretKey deskey = initKey();
            if (deskey == null) {
                return null;
            }

            c2.init(Cipher.DECRYPT_MODE, deskey);

            byte[] output = c2.doFinal(ciphertext);

            return new String(output, "GBK").getBytes();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 双字节转单字节
     * 
     * @param pszIn
     * @return
     */
    private byte[] doubleByteToByte(byte[] pszIn) {
        int iLen = pszIn.length;
        if ((pszIn.length % 2) != 0) {
            return new byte[0];
        }
        byte[] pszOut = new byte[iLen / 2];
        for (int i = 0, j = 0; i < iLen; i += 2, j++) {
            byte hiByte = 0;
            byte loByte = 0;
            if (pszIn[i] >= 'a') {
                hiByte = (byte) (pszIn[i] - 0x57);
            } else if (pszIn[i] >= 'A') {
                hiByte = (byte) (pszIn[i] - 0x37);
            } else {
                hiByte = (byte) (pszIn[i] - 0x30);
            }
            if (pszIn[i + 1] >= 'a') {
                loByte = (byte) (pszIn[i + 1] - 0x57);
            } else if (pszIn[i + 1] >= 'A') {
                loByte = (byte) (pszIn[i + 1] - 0x37);
            } else {
                loByte = (byte) (pszIn[i + 1] - 0x30);
            }
            pszOut[j] = (byte) (((hiByte << 4) & (byte) 0xF0) | ((loByte & (byte) 0x0F)));
        }
        return pszOut;
    }

    /**
     * 初始密钥
     * <p>
     *  注意：此处密钥生成算法由银行提供，与银行保持一致
     * </p>  
     * 
     * @return
     */
    public SecretKey initKey() {
        try {
            String keyPwd = "11111111111111111111111111111111#^^^^^#2014031812345678";

            byte[] deskeys = new byte[24];
            //次处密钥包含了密钥版本,需要解析,格式:key^keyVersion
            String[] values = StringUtil.split(keyPwd, SPLITER);

            byte[] keyBytes = values[0].getBytes("ISO-8859-1");

            byte[] singelkeyBytes = doubleByteToByte(keyBytes);
            System.arraycopy(singelkeyBytes, 0, deskeys, 0, singelkeyBytes.length);

            byte[] key8 = new byte[8];
            System.arraycopy(singelkeyBytes, 0, key8, 0, 8);

            System.arraycopy(key8, 0, deskeys, 16, key8.length);

            return new SecretKeySpec(deskeys, ALGORITHM);

        } catch (Exception e) {
        }

        return null;
    }

}
