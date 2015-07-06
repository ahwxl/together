package com.bplow.netconn.base.certifier;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class PointExpressRsaCertifier {
	Logger log = LoggerFactory.getLogger(PointExpressRsaCertifier.class);
	/** 默认编码 */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /** 签名算法 */
    private static final String SIG_ALGORITHM    = "SHA1withRSA";

    private static final String LINE_BREAK       = "\r\n";

    /**银行公钥 */
    private PublicKey           bankPublicKey;

    /**支付宝私钥 */
    private PrivateKey          alipayPrivateKey;

    /** 
     * @see com.alipay.bcm.biz.certifier.Certifier#init()
     */
    public void init() {


        try {
            //生成私钥
            String merPriKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMevwr8LgSmw4od3"+"\r"+
            		"H+j7zMQy4ArnDGAPRWD6+GO1eqCZvdc9g3E0lqgTeQCu5uHASqRkjvzRqW242ktH"+"\r"+
            		"TaW1CSZ6358TlbD1R/8YHuhwphRzWmAAwXhDxAGRo0L5+CgIzxSaLYTFWNdVQkYJ"+"\r"+
            		"Bn+AmO6An2nr8J1UMkUN0VosqtFdAgMBAAECgYBFLjZJG8I+4GJKzlGG1R2m531J"+"\r"+
            		"KazW5tOBetFtMeVvGHR1yoDsSmzbhAuK2g1xLr5tX81N95E8SyuNowFapHKrfgk2"+"\r"+
            		"zV/FZly1ot2RJvsOXDwrE7eVSDZQP1Ugg04tB2XcUIZ4lgOlX96Ck0usZeAgXrkX"+"\r"+
            		"Xqe9zpjXqUTOzgqZQQJBAPRjnzYUGud20s62KzPBNtGX/nOZhWOEXdRqZwaSILjP"+"\r"+
            		"mI1XLNx8dOh7Jg8Ny0kJ4DKGJ5exKaRAqm801kYZz/ECQQDRLHHN5SLYEVP6zvhK"+"\r"+
            		"V8ExYt3ZTN/hO+zIbN9rKmb/U4VQWs0qJu5Dd6Reykm9lFRwBSSzfKiv/ilWgI1i"+"\r"+
            		"eYQtAkAq5CkXJqQqJ3KMEPC4HpruYgyoYTNF6f9Fvn7ialcP2G9frwMSl001WBev"+"\r"+
            		"4K3i3bbZZy4fs1opl6fR6KYKm5EhAkBhFoMAMVG5/lj/eh+OyiqW1K/qU586uQjP"+"\r"+
            		"gpE0+apy6n5fbLX70bQXxqmZgcU2ZGnI0OpXEQ5vGcZwKxz0zzhFAkEAorSmQeO9"+"\r"+
            		"KgpZaTMWM+/qqvBlyB/AEbnMkPauPNVDEPWWdLa9EltmalWQi3YkFFGm4b3xLY3I"+"\r"+
            		"MaSLURoVte863Q=="+"\r";
            byte[] privateKeys;
                privateKeys = Base64.decodeBase64(merPriKey);
                PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeys);
                KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
                this.alipayPrivateKey = mykeyFactory.generatePrivate(privateKeySpec);

            //生成公钥
            String bankPubkey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHr8K/C4EpsOKHdx/o+8zEMuAK"+"\r"+
"5wxgD0Vg+vhjtXqgmb3XPYNxNJaoE3kArubhwEqkZI780altuNpLR02ltQkmet+f"+"\r"+
"E5Ww9Uf/GB7ocKYUc1pgAMF4Q8QBkaNC+fgoCM8Umi2ExVjXVUJGCQZ/gJjugJ9p"+"\r"+
"6/CdVDJFDdFaLKrRXQIDAQAB"+"\r";
            if (!StringUtils.isEmpty(bankPubkey)) {
                byte[] pubKeys;
                pubKeys = Base64.decodeBase64(bankPubkey);
                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeys);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                this.bankPublicKey = kf.generatePublic(pubKeySpec);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 报文签名。
     * 
     * @param unsigned 未加密的报文。
     * @return 加密之后的报文。
     * @see com.alipay.bcm.biz.certifier.AbstractCertifier#sign(java.lang.String)
     */
    public String sign(String unsigned) {

    	log.info("PointExpressRsaCertifier待签名数据=" + unsigned);
        String signed = null;
        try {
            byte sigData[] = null;
            byte sourceData[] = unsigned.getBytes(DEFAULT_ENCODING);

            //初始化签名
            Signature sig = Signature.getInstance(SIG_ALGORITHM);
            sig.initSign(alipayPrivateKey);
            sig.update(sourceData);

            //签名
            sigData = sig.sign();

            //base64加密
            signed = new String(Base64.encodeBase64(sigData));
        } catch (Exception e) {
        	log.error("[PointExpressRsaCertifier]签名异常");
        }
        log.info("PointExpressRsaCertifier签名后数据=" + signed);
        return signed;
    }

    /**
     * 报文验签。
     * 
     * @param signed 银行的签名
     * @param src 未签名的源报文
     * @return 验签是否成功
     * @see com.alipay.bcm.biz.certifier.AbstractCertifier#verify(java.lang.String, java.lang.String)
     */
    public boolean verify(String signed, String unsigned) {
    	log.debug("PointExpressRsaCertifier验签 - unsigned="
                                                      + unsigned + ";signed=" + signed);
        boolean valid = false;
        try {
            byte sourceData[] = unsigned.getBytes(DEFAULT_ENCODING);

            //base64解密
            byte[] sigData = Base64.decodeBase64(signed);
            //初始化签名
            Signature sig = Signature.getInstance(SIG_ALGORITHM);
            sig.initVerify(bankPublicKey);
            sig.update(sourceData);

            //验签
            valid = sig.verify(sigData);
        } catch (Exception e) {
            log.error("[PointExpressRsaCertifier]签名异常");
        }

        log.info("PointExpressRsaCertifier验签结果=" + valid);

        return valid;

    }

}
