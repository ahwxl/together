package com.bplow.netconn.base.certifier;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bplow.netconn.base.pairencrypt.SignatureUtil;

@Service
public class CommonCertifier {
	
	Logger log = LoggerFactory.getLogger(CommonCertifier.class);
	
	/** 私钥 */
    private byte[] privateKey;

    /** 公钥 */
    private byte[] publicKey;

    /** 算法 */
    private String algorithm;
    
    /**
     * 初始化密钥内容
     * @see com.alipay.bcm.biz.certifier.Certifier#init()
     */
    public void init() {

        String keyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqfuP7kHYZbFabUdrsIfRZAtSjj9BC8G5DA3CrcTNiOch81/2kkqSDAOEjATFrvBC+XiO3TEc5Rc2/oZ9vLF4p7tI30fkLzx8Cy1BOYetnyCBdG49ZiFTmU3y5gvpgPFaSBwtpIzwpo32tGqd9LGlILjKFJ8NWK0M59ilXaLL5ckYMsErtBsK6ItDZUGfQdcIp8eLhRa557+DlOvRbu8CRJRL0tW0ASW9kWqCWWLrQHVLspl8ieb2v6vfeK94YH5stn+pwKUzT23kEfjwpU1R9TyD7ySOielwHOECdbRbrKk7OAw781U5X3k0sOeNU5Nw6xMNJzuv8ftBY7hJBiRAkwIDAQAB";
        publicKey = Base64.decodeBase64(keyStr);
        //publicKey = keyStr.getBytes();
        keyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCp+4/uQdhlsVptR2uwh9FkC1KOP0ELwbkMDcKtxM2I5yHzX/aSSpIMA4SMBMWu8EL5eI7dMRzlFzb+hn28sXinu0jfR+QvPHwLLUE5h62fIIF0bj1mIVOZTfLmC+mA8VpIHC2kjPCmjfa0ap30saUguMoUnw1YrQzn2KVdosvlyRgywSu0Gwroi0NlQZ9B1winx4uFFrnnv4OU69Fu7wJElEvS1bQBJb2RaoJZYutAdUuymXyJ5va/q994r3hgfmy2f6nApTNPbeQR+PClTVH1PIPvJI6J6XAc4QJ1tFusqTs4DDvzVTlfeTSw541Tk3DrEw0nO6/x+0FjuEkGJECTAgMBAAECggEBAIcqO3Q4tat/kKlO3oocJdvIyRfFoqKHo+66znBBCzLun+eYCkiftWyKK47viIYoFQms3OV0VUax5BAWv8sY0BmIalTqJL+O+BAnJzNo+R2MyoPb2UTqAUDpY9mb5UycHq8ygPTVAdNfFaq3EO1viR/w8Pfe1c0KpjWB51UCy+Hmbzz4twcON5r1ilNFdBt6w/Sntfsjf/S1vdcLx8ZNiCAPd1LHHZxtHlZs0IdFrPBtgl55c4Cl0ntFT/g7RQOjKVuObnPN84FXf23KbxRhMKVoRhKT8Q5PVjNqqMAYjDjfKrHF6Yw0IAR9Z6+A/xnos5DDUG+OM6c8ea9vJ6B7VykCgYEA1+Q8DVEJcSi+nyrkcjKccn/jRW7GeKXklxkACtXiWAJ/1THVybRM3cfbYSEHsIppTJLaBi1f/cvOgNXktrAcYbewNe4ob8EKWWqYrIuEtcRMDXgLu5ALnYGFTKfphSBh1ZfgnuEy/Pa3tj/wHqGHq5RK2GTPjDTChhovsw42Ni0CgYEAyY/owceOPp+QQICStuRZYBZ+XOVsQ9SPhUaKL9c6qaanTtUULAzONeOl6ysmnI+NE2rPbsQxKrrBEC/lCWfV9L0X4X7Ouc2790pIIw5P1WwxbowSpqo5hl9dH8aBeshAo7+JuUl/hDh4QktVjRHAF80bkEw8meyqOmVlwG2bSb8CgYAyXxsv1DeKwoHvazeP+YUNJg+l9Jm0LqiuJHQhExRTiom++XizLjE9EdN6zxUXOMQmzKC4DkA2XCYbY0yQ33hPyGcBvkaLBJRgloF2yLq3GkzQW7EJGyvKnRy37PmMSSjqiBwtlceqw/nLORHSY8fe3aO055iRUwIL/fIhKfC2JQKBgQClzYFz1cnG7c7loF4PoGt8xUQQ+pBCg9nDkjEeBXg2EebSzCiZy7bdUXQsrQRICTXNYTFdNnoTYihqPluzjvzLI7k/PuaipQAX/by1SZKWRzeqbgLxollLlaqu9sWP0KaLjIWoKzN/+kvCjOHE93MCoTApVO0M2Ud2Xe6DiiYRVQKBgQC6wTn8Epni+ekrWG0s6kkcWxGuN99PLPM7VfwtEr80GkC+sCJ6cM+ZeZdQadiCPzCFJUNwogD9fsnjzrPex4h68+Eup4nTBvdEJRwYa2PU5dviYrbQ8jrSACebAfX4xYIUgfQQM/bHA0YblGYguNFr1aamu6JqQv+JALfhSPCq5Q==";
        privateKey = Base64.decodeBase64(keyStr);
        //privateKey = keyStr.getBytes();
        algorithm = "SHA256withRSA";
    }

    /**
     * 签名
     * @see com.alipay.bcm.biz.certifier.AbstractCertifier#sign(java.lang.String)
     */
    public String sign(String unsigned) {
        String signed = null;
        if (unsigned != null) {
            try {
                signed = new String(Base64.encodeBase64(SignatureUtil.sign(unsigned.getBytes(),
                    privateKey, algorithm)));
            } catch (Exception e) {
            	log.error("", e);
            }
        }
        log.info("sign:{}",signed);
        return signed;
    }

    /**
     * 验签
     * @see com.alipay.bcm.biz.certifier.AbstractCertifier#verify(java.lang.String, java.lang.String)
     */
    public boolean verify(String signed, String unsigned) {
        boolean result = false;
        if (signed != null && unsigned != null) {
            try {
                result = SignatureUtil.verify(unsigned.getBytes(),
                		Base64.decodeBase64(signed), publicKey, algorithm);
            } catch (Exception e) {
            }
        }
        log.info("verify sign 结果:{}",result);
        return result;
    }

    /**
     * Setter method for property <tt>algorithm</tt>.
     * 
     * @param algorithm value to be assigned to property algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
	

}
