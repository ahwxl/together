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

        String keyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7yBekcrcOy7p1fS+9YjxqbJjnfroFfeXSYmiKtZTucNdWbAhPemVvD+blLo1kH21jhx2waL4ztck2h4W6tjZ4NfOHNRFONCWIPYTCxY9wdg44fRGwdL+vkHIN0UeIwgBqhIQt4xZXCi7ksxnnwOrmLuAlRtnTkYdmgZ5RjWtD1/qrs3cksP+O4L0V6Z0uSn9oA7LGyYIwiFJCGQsBotfRnelFKLI+n8jHlRb6MNV7hLYK7gVht+QZH7hUNu/RcCHYrzFN/2vh+3k/jqoX4+7AZKG/RuQyq4fGwkPn3oD/JorZadbLWAD/ynwrb+fm7oreYv+MtKh8n8zeaGfE89P8QIDAQAB";
        keyStr =        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2ox8DZlXp26kDVLbfTTh04OWmSDxpd9IrDQO40K6QYDzF0lD+ASGgtYqz0wHxTKfMd4oXTWeG1PHr+K74nZlKZVn5qKNvSHDq3b9g0JEbbvZt+skEiHiFQOfqyHyaBV1ixdQnBUwoMgEj8jE4gXgE1eE0Wm1OHe/QVkvGMnLv3mwysiXLbDYtAR0anX04ZrLkBPLZEFA71BHt0HRlw0kmNMdeH+S8OwjHGywKZKUwV18n8oxdx7THQ5fIao62eIdHjwWradS+lpAppbhmDlc0meHNDsOtXmiJ0JfRp6mykmwQ7Ex7q2oNxJnsoHHc71LZPWrDewldxGbA5L4Pn3JWQIDAQAB";
        keyStr =        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7yBekcrcOy7p1fS+9YjxqbJjnfroFfeXSYmiKtZTucNdWbAhPemVvD+blLo1kH21jhx2waL4ztck2h4W6tjZ4NfOHNRFONCWIPYTCxY9wdg44fRGwdL+vkHIN0UeIwgBqhIQt4xZXCi7ksxnnwOrmLuAlRtnTkYdmgZ5RjWtD1/qrs3cksP+O4L0V6Z0uSn9oA7LGyYIwiFJCGQsBotfRnelFKLI+n8jHlRb6MNV7hLYK7gVht+QZH7hUNu/RcCHYrzFN/2vh+3k/jqoX4+7AZKG/RuQyq4fGwkPn3oD/JorZadbLWAD/ynwrb+fm7oreYv+MtKh8n8zeaGfE89P8QIDAQAB";
        keyStr =        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtKfHI7FCxA30aIn6/Y7+z3MLfM+sMIKJf3iAdBmiqtzMV9VpAFd7VW4OfR0usXmsNlpDM8oZKQnluoEKBRMKwJJS2K4q9y6oByHZPJAzDlZAB+MO+d50Ce1XfPRwCZyepMRbOc/ba+d+QCWhD0XuvRD7ge3v6cHqt6IPf8Nci32RRvBnS/KbGhtOWi/Ekp6vOzWpDv1+ffrlraazJoQiHevpNEKnuN8P7c5jqzXVULOQwW2+nCExKbnlrPTOTkjCjsD9v8i+8fDhp+aX/cJoFMQ36DHAib6cDKjleIvPiijCr5OkHZKMtK1ZNw1Qz2/uUlLHwk3/GZwz9HcL5vdFWQIDAQAB";
        
        publicKey = Base64.decodeBase64(keyStr);
        //publicKey = keyStr.getBytes();
        keyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCp+4/uQdhlsVptR2uwh9FkC1KOP0ELwbkMDcKtxM2I5yHzX/aSSpIMA4SMBMWu8EL5eI7dMRzlFzb+hn28sXinu0jfR+QvPHwLLUE5h62fIIF0bj1mIVOZTfLmC+mA8VpIHC2kjPCmjfa0ap30saUguMoUnw1YrQzn2KVdosvlyRgywSu0Gwroi0NlQZ9B1winx4uFFrnnv4OU69Fu7wJElEvS1bQBJb2RaoJZYutAdUuymXyJ5va/q994r3hgfmy2f6nApTNPbeQR+PClTVH1PIPvJI6J6XAc4QJ1tFusqTs4DDvzVTlfeTSw541Tk3DrEw0nO6/x+0FjuEkGJECTAgMBAAECggEBAIcqO3Q4tat/kKlO3oocJdvIyRfFoqKHo+66znBBCzLun+eYCkiftWyKK47viIYoFQms3OV0VUax5BAWv8sY0BmIalTqJL+O+BAnJzNo+R2MyoPb2UTqAUDpY9mb5UycHq8ygPTVAdNfFaq3EO1viR/w8Pfe1c0KpjWB51UCy+Hmbzz4twcON5r1ilNFdBt6w/Sntfsjf/S1vdcLx8ZNiCAPd1LHHZxtHlZs0IdFrPBtgl55c4Cl0ntFT/g7RQOjKVuObnPN84FXf23KbxRhMKVoRhKT8Q5PVjNqqMAYjDjfKrHF6Yw0IAR9Z6+A/xnos5DDUG+OM6c8ea9vJ6B7VykCgYEA1+Q8DVEJcSi+nyrkcjKccn/jRW7GeKXklxkACtXiWAJ/1THVybRM3cfbYSEHsIppTJLaBi1f/cvOgNXktrAcYbewNe4ob8EKWWqYrIuEtcRMDXgLu5ALnYGFTKfphSBh1ZfgnuEy/Pa3tj/wHqGHq5RK2GTPjDTChhovsw42Ni0CgYEAyY/owceOPp+QQICStuRZYBZ+XOVsQ9SPhUaKL9c6qaanTtUULAzONeOl6ysmnI+NE2rPbsQxKrrBEC/lCWfV9L0X4X7Ouc2790pIIw5P1WwxbowSpqo5hl9dH8aBeshAo7+JuUl/hDh4QktVjRHAF80bkEw8meyqOmVlwG2bSb8CgYAyXxsv1DeKwoHvazeP+YUNJg+l9Jm0LqiuJHQhExRTiom++XizLjE9EdN6zxUXOMQmzKC4DkA2XCYbY0yQ33hPyGcBvkaLBJRgloF2yLq3GkzQW7EJGyvKnRy37PmMSSjqiBwtlceqw/nLORHSY8fe3aO055iRUwIL/fIhKfC2JQKBgQClzYFz1cnG7c7loF4PoGt8xUQQ+pBCg9nDkjEeBXg2EebSzCiZy7bdUXQsrQRICTXNYTFdNnoTYihqPluzjvzLI7k/PuaipQAX/by1SZKWRzeqbgLxollLlaqu9sWP0KaLjIWoKzN/+kvCjOHE93MCoTApVO0M2Ud2Xe6DiiYRVQKBgQC6wTn8Epni+ekrWG0s6kkcWxGuN99PLPM7VfwtEr80GkC+sCJ6cM+ZeZdQadiCPzCFJUNwogD9fsnjzrPex4h68+Eup4nTBvdEJRwYa2PU5dviYrbQ8jrSACebAfX4xYIUgfQQM/bHA0YblGYguNFr1aamu6JqQv+JALfhSPCq5Q==";
        keyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC0p8cjsULEDfRoifr9jv7Pcwt8z6wwgol/eIB0GaKq3MxX1WkAV3tVbg59HS6xeaw2WkMzyhkpCeW6gQoFEwrAklLYrir3LqgHIdk8kDMOVkAH4w753nQJ7Vd89HAJnJ6kxFs5z9tr535AJaEPRe69EPuB7e/pweq3og9/w1yLfZFG8GdL8psaG05aL8SSnq87NakO/X59+uWtprMmhCId6+k0Qqe43w/tzmOrNdVQs5DBbb6cITEpueWs9M5OSMKOwP2/yL7x8OGn5pf9wmgUxDfoMcCJvpwMqOV4i8+KKMKvk6Qdkoy0rVk3DVDPb+5SUsfCTf8ZnDP0dwvm90VZAgMBAAECggEBAJj4sNFyWfy1bE3Q+K0YFoAHBKUtHLr+7fr4SJ0G0A0qZgXB+eWGLjNKvA4JVe6F/uMPT3RdHyz26oYgmiFt3F7wCCLeaBdidHmFmfOEjxcGxbVn+NhwG4YEaV6vhOc+rb8LXcv4S+502RzsQBM0vlFuPenpVotz3pKcMc+X15ds0gCFQNEl0Y1uMSVl6rl+D7ls+UfZfyUs0SqfAYAL7I2nKJtOV2U7j6DX/iDvxd8XDqGrj+SIe4X+PselK0fAHpqf8cSD7oIpgQI2kxwKq8hVZYCQP/dAD/RbYr9S9zecop3sdSp5sZM8/osGipVT3j61vSVRvL2INvVLRjJrHuECgYEA2z1+Ao8HeNbwo0mInhHynbw/YwNGHqaOmYKDNXnllMCf63eqmGv7Pn0eI45vYU9x3r/AQ1p5iBZlfX0+VHZU4u92Vxhqi9yhakG/EBK+VXKilBPLnajgM81nCiJqdk7G5TjrcLXoPdho3bZxlviqQDSe4C39m5+m03BLV/2EEj0CgYEA0vIZRXDWDHbaaBpfWfNlhzvwg2C92LrGmitqJQ+QYx6YxUUjQKdD/QEp5Ml2frZrH1MgOIXTZHnx4GeRjryZgqCxA5113C5XUl3FC0vt3bdfu7K3Fa2PxpoL3B4N+u/zCeT4U1Uk7vyHa3B+i5Hu6gKPLPlfvaESwqFVb3IOfU0CgYBKowGOD3FEwy/C9xqqbYPCDT8rDwdFEgDD4/mVL/1nibgnqmKKZ2BuIpwrEKd8YlZqta7XfyqWmMFOWl/o0ywM4ebQucA0AxV0nhNUfn0Li+4rKR4Ne1twZ0Hn4lWKF0GA8cK9aUA6Jb11lVHQSrFzEDbyLNYEr/JUUwtjViwrJQKBgQDMNyCL/17Zlj5+xflFhcLmlvqS1Zc1OCyImDscQeMQclCHuhUUxFmnl/bMzNTwev1dyAXLcpNiFEQ7cUiTHLaopZR44Xl2fU6AKNNXylwvZxVBCxT/4ZTtObj9kgMKyj1JeOeHFUi6LLOVKcT4rYDT/0JI0esEE+mrjrC9QXgRiQKBgQDUGyJsJky50F4kXUevIfQzLvXjeIBfIp/x3XUi0vQqHNUsljEmRYnfZURnaKxRWRCBafowUUPUNxak/7PwnawOOvCT1IUQED8xHDDEg+teQAK0gBapQSxUpqiE853dCvxA8aC/GLx5boZgMKJJqdg8N9bxdBvoHqs1Rz2Kdghrgg==";
        keyStr = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDajHwNmVenbqQNUtt9NOHTg5aZIPGl30isNA7jQrpBgPMXSUP4BIaC1irPTAfFMp8x3ihdNZ4bU8ev4rvidmUplWfmoo29IcOrdv2DQkRtu9m36yQSIeIVA5+rIfJoFXWLF1CcFTCgyASPyMTiBeATV4TRabU4d79BWS8Yycu/ebDKyJctsNi0BHRqdfThmsuQE8tkQUDvUEe3QdGXDSSY0x14f5Lw7CMcbLApkpTBXXyfyjF3HtMdDl8hqjrZ4h0ePBatp1L6WkCmluGYOVzSZ4c0Ow61eaInQl9GnqbKSbBDsTHurag3EmeygcdzvUtk9asN7CV3EZsDkvg+fclZAgMBAAECggEBAMtdnrIo1t/+nLmpOIUyCFICSMkFVte7Bqz3ZEMlfJwnmg05J/5Kd8CX+g4b+b8OdAS7bWQO2KklNNP+cEczVL91P9HlwW8VVDbh6tWRfIuAGgCC1nXpAa/8Rz+/vwoLre1YLLKRJUb32QbTFCIexXw6HGWhr/hztYzhqGmwnd/1bM/lv1oFJcM5g6FgTqZXtCe6NxhzAe3SOGZRZJtMn1oreD799FvHXejllFo8AI/vAUOPrPQVo+JSAdXHNVFkZndHI9JqoYqfRVIuhRhjS5VJyUr3ZECH9M62TP5mDih82/ZlyrF/E+pG81DaiZ/mztCG5aCrmuyjo2U2FWfameECgYEA7MUsyOR5sB/mEeOvt5C+loNxPP9scFbaAky4qiG0Oeab0zei1thzZuIKi7MnBHuBC9S9wwmQkvQdA72ztFKUUfW/1HFNa4xN/sPXCxvz/gGXBXcKUNvg/8gZ0vWzkxivUt4j8G0LAP2nzXpR4orUm+BnhhenHcSH9qxRd29Us2MCgYEA7Ex1BWVfUv3oJ2Aasxh7YM9R7ezU+QaHPOJIoTWJB4x1EmUsLVQqcnrHtSuyi5855Vj277SBOIDWVtL7jMW6B+TCZlSas6g1oZ+in8AbLIpEvfdaQMFU+f6HPETiBiqFbJiObTvgfVdTkyr/hl1WgYvI/SKGvHAVcjLQBEKVcxMCgYEAuQGk6eClOTrjoizgG7ZtRdy1Oe13xRwIQ23BiabGVcvdDKDXi7scCVMs3FYDcdwzTJRSJrktj7ZRiZ/McGVqYdR9rTlPPm8mbqCGQrb34w1I/Usz81SsLhEkJJQ6WgEa1cygFniN9Zs+aC9hFnxWfupM2hlXSspLVzL0gTer3nMCgYEAtbE0aanA2jeRwnULEprIg2/mN2Pbfn+ejRoRbqOTcdJ2UmaoyVHmMZBm7s9iwij0g30cosPQeHnYVXp1IZEHsfob4EM9kNbOJjBxAxTb0CuhH9CJ/TCq/m7P3W+5u7QGU7IaEbPwsW/sO0TcpZwvX/zz1nAt2cb1NpILkLsB3zECgYEA6aslq+7fGcSjGF3CwY2m45axCvofIyBvqABQnHMKaCUtgMqz+CByjJUYNWiWQaMikP7uYPytDHyayDoSkyHip4HiTSjJKYITcy1g807L4ooJg3eMQWPjgTplmXp7ATBpZvh6GqeH1DeiWRSWWOjt18adTogjC8RGR2EVaL7seMI=";
        keyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC0p8cjsULEDfRoifr9jv7Pcwt8z6wwgol/eIB0GaKq3MxX1WkAV3tVbg59HS6xeaw2WkMzyhkpCeW6gQoFEwrAklLYrir3LqgHIdk8kDMOVkAH4w753nQJ7Vd89HAJnJ6kxFs5z9tr535AJaEPRe69EPuB7e/pweq3og9/w1yLfZFG8GdL8psaG05aL8SSnq87NakO/X59+uWtprMmhCId6+k0Qqe43w/tzmOrNdVQs5DBbb6cITEpueWs9M5OSMKOwP2/yL7x8OGn5pf9wmgUxDfoMcCJvpwMqOV4i8+KKMKvk6Qdkoy0rVk3DVDPb+5SUsfCTf8ZnDP0dwvm90VZAgMBAAECggEBAJj4sNFyWfy1bE3Q+K0YFoAHBKUtHLr+7fr4SJ0G0A0qZgXB+eWGLjNKvA4JVe6F/uMPT3RdHyz26oYgmiFt3F7wCCLeaBdidHmFmfOEjxcGxbVn+NhwG4YEaV6vhOc+rb8LXcv4S+502RzsQBM0vlFuPenpVotz3pKcMc+X15ds0gCFQNEl0Y1uMSVl6rl+D7ls+UfZfyUs0SqfAYAL7I2nKJtOV2U7j6DX/iDvxd8XDqGrj+SIe4X+PselK0fAHpqf8cSD7oIpgQI2kxwKq8hVZYCQP/dAD/RbYr9S9zecop3sdSp5sZM8/osGipVT3j61vSVRvL2INvVLRjJrHuECgYEA2z1+Ao8HeNbwo0mInhHynbw/YwNGHqaOmYKDNXnllMCf63eqmGv7Pn0eI45vYU9x3r/AQ1p5iBZlfX0+VHZU4u92Vxhqi9yhakG/EBK+VXKilBPLnajgM81nCiJqdk7G5TjrcLXoPdho3bZxlviqQDSe4C39m5+m03BLV/2EEj0CgYEA0vIZRXDWDHbaaBpfWfNlhzvwg2C92LrGmitqJQ+QYx6YxUUjQKdD/QEp5Ml2frZrH1MgOIXTZHnx4GeRjryZgqCxA5113C5XUl3FC0vt3bdfu7K3Fa2PxpoL3B4N+u/zCeT4U1Uk7vyHa3B+i5Hu6gKPLPlfvaESwqFVb3IOfU0CgYBKowGOD3FEwy/C9xqqbYPCDT8rDwdFEgDD4/mVL/1nibgnqmKKZ2BuIpwrEKd8YlZqta7XfyqWmMFOWl/o0ywM4ebQucA0AxV0nhNUfn0Li+4rKR4Ne1twZ0Hn4lWKF0GA8cK9aUA6Jb11lVHQSrFzEDbyLNYEr/JUUwtjViwrJQKBgQDMNyCL/17Zlj5+xflFhcLmlvqS1Zc1OCyImDscQeMQclCHuhUUxFmnl/bMzNTwev1dyAXLcpNiFEQ7cUiTHLaopZR44Xl2fU6AKNNXylwvZxVBCxT/4ZTtObj9kgMKyj1JeOeHFUi6LLOVKcT4rYDT/0JI0esEE+mrjrC9QXgRiQKBgQDUGyJsJky50F4kXUevIfQzLvXjeIBfIp/x3XUi0vQqHNUsljEmRYnfZURnaKxRWRCBafowUUPUNxak/7PwnawOOvCT1IUQED8xHDDEg+teQAK0gBapQSxUpqiE853dCvxA8aC/GLx5boZgMKJJqdg8N9bxdBvoHqs1Rz2Kdghrgg==";
        keyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC0p8cjsULEDfRoifr9jv7Pcwt8z6wwgol/eIB0GaKq3MxX1WkAV3tVbg59HS6xeaw2WkMzyhkpCeW6gQoFEwrAklLYrir3LqgHIdk8kDMOVkAH4w753nQJ7Vd89HAJnJ6kxFs5z9tr535AJaEPRe69EPuB7e/pweq3og9/w1yLfZFG8GdL8psaG05aL8SSnq87NakO/X59+uWtprMmhCId6+k0Qqe43w/tzmOrNdVQs5DBbb6cITEpueWs9M5OSMKOwP2/yL7x8OGn5pf9wmgUxDfoMcCJvpwMqOV4i8+KKMKvk6Qdkoy0rVk3DVDPb+5SUsfCTf8ZnDP0dwvm90VZAgMBAAECggEBAJj4sNFyWfy1bE3Q+K0YFoAHBKUtHLr+7fr4SJ0G0A0qZgXB+eWGLjNKvA4JVe6F/uMPT3RdHyz26oYgmiFt3F7wCCLeaBdidHmFmfOEjxcGxbVn+NhwG4YEaV6vhOc+rb8LXcv4S+502RzsQBM0vlFuPenpVotz3pKcMc+X15ds0gCFQNEl0Y1uMSVl6rl+D7ls+UfZfyUs0SqfAYAL7I2nKJtOV2U7j6DX/iDvxd8XDqGrj+SIe4X+PselK0fAHpqf8cSD7oIpgQI2kxwKq8hVZYCQP/dAD/RbYr9S9zecop3sdSp5sZM8/osGipVT3j61vSVRvL2INvVLRjJrHuECgYEA2z1+Ao8HeNbwo0mInhHynbw/YwNGHqaOmYKDNXnllMCf63eqmGv7Pn0eI45vYU9x3r/AQ1p5iBZlfX0+VHZU4u92Vxhqi9yhakG/EBK+VXKilBPLnajgM81nCiJqdk7G5TjrcLXoPdho3bZxlviqQDSe4C39m5+m03BLV/2EEj0CgYEA0vIZRXDWDHbaaBpfWfNlhzvwg2C92LrGmitqJQ+QYx6YxUUjQKdD/QEp5Ml2frZrH1MgOIXTZHnx4GeRjryZgqCxA5113C5XUl3FC0vt3bdfu7K3Fa2PxpoL3B4N+u/zCeT4U1Uk7vyHa3B+i5Hu6gKPLPlfvaESwqFVb3IOfU0CgYBKowGOD3FEwy/C9xqqbYPCDT8rDwdFEgDD4/mVL/1nibgnqmKKZ2BuIpwrEKd8YlZqta7XfyqWmMFOWl/o0ywM4ebQucA0AxV0nhNUfn0Li+4rKR4Ne1twZ0Hn4lWKF0GA8cK9aUA6Jb11lVHQSrFzEDbyLNYEr/JUUwtjViwrJQKBgQDMNyCL/17Zlj5+xflFhcLmlvqS1Zc1OCyImDscQeMQclCHuhUUxFmnl/bMzNTwev1dyAXLcpNiFEQ7cUiTHLaopZR44Xl2fU6AKNNXylwvZxVBCxT/4ZTtObj9kgMKyj1JeOeHFUi6LLOVKcT4rYDT/0JI0esEE+mrjrC9QXgRiQKBgQDUGyJsJky50F4kXUevIfQzLvXjeIBfIp/x3XUi0vQqHNUsljEmRYnfZURnaKxRWRCBafowUUPUNxak/7PwnawOOvCT1IUQED8xHDDEg+teQAK0gBapQSxUpqiE853dCvxA8aC/GLx5boZgMKJJqdg8N9bxdBvoHqs1Rz2Kdghrgg==";
        
        privateKey = Base64.decodeBase64(keyStr);
        //privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC0p8cjsULEDfRoifr9jv7Pcwt8z6wwgol/eIB0GaKq3MxX1WkAV3tVbg59HS6xeaw2WkMzyhkpCeW6gQoFEwrAklLYrir3LqgHIdk8kDMOVkAH4w753nQJ7Vd89HAJnJ6kxFs5z9tr535AJaEPRe69EPuB7e/pweq3og9/w1yLfZFG8GdL8psaG05aL8SSnq87NakO/X59+uWtprMmhCId6+k0Qqe43w/tzmOrNdVQs5DBbb6cITEpueWs9M5OSMKOwP2/yL7x8OGn5pf9wmgUxDfoMcCJvpwMqOV4i8+KKMKvk6Qdkoy0rVk3DVDPb+5SUsfCTf8ZnDP0dwvm90VZAgMBAAECggEBAJj4sNFyWfy1bE3Q+K0YFoAHBKUtHLr+7fr4SJ0G0A0qZgXB+eWGLjNKvA4JVe6F/uMPT3RdHyz26oYgmiFt3F7wCCLeaBdidHmFmfOEjxcGxbVn+NhwG4YEaV6vhOc+rb8LXcv4S+502RzsQBM0vlFuPenpVotz3pKcMc+X15ds0gCFQNEl0Y1uMSVl6rl+D7ls+UfZfyUs0SqfAYAL7I2nKJtOV2U7j6DX/iDvxd8XDqGrj+SIe4X+PselK0fAHpqf8cSD7oIpgQI2kxwKq8hVZYCQP/dAD/RbYr9S9zecop3sdSp5sZM8/osGipVT3j61vSVRvL2INvVLRjJrHuECgYEA2z1+Ao8HeNbwo0mInhHynbw/YwNGHqaOmYKDNXnllMCf63eqmGv7Pn0eI45vYU9x3r/AQ1p5iBZlfX0+VHZU4u92Vxhqi9yhakG/EBK+VXKilBPLnajgM81nCiJqdk7G5TjrcLXoPdho3bZxlviqQDSe4C39m5+m03BLV/2EEj0CgYEA0vIZRXDWDHbaaBpfWfNlhzvwg2C92LrGmitqJQ+QYx6YxUUjQKdD/QEp5Ml2frZrH1MgOIXTZHnx4GeRjryZgqCxA5113C5XUl3FC0vt3bdfu7K3Fa2PxpoL3B4N+u/zCeT4U1Uk7vyHa3B+i5Hu6gKPLPlfvaESwqFVb3IOfU0CgYBKowGOD3FEwy/C9xqqbYPCDT8rDwdFEgDD4/mVL/1nibgnqmKKZ2BuIpwrEKd8YlZqta7XfyqWmMFOWl/o0ywM4ebQucA0AxV0nhNUfn0Li+4rKR4Ne1twZ0Hn4lWKF0GA8cK9aUA6Jb11lVHQSrFzEDbyLNYEr/JUUwtjViwrJQKBgQDMNyCL/17Zlj5+xflFhcLmlvqS1Zc1OCyImDscQeMQclCHuhUUxFmnl/bMzNTwev1dyAXLcpNiFEQ7cUiTHLaopZR44Xl2fU6AKNNXylwvZxVBCxT/4ZTtObj9kgMKyj1JeOeHFUi6LLOVKcT4rYDT/0JI0esEE+mrjrC9QXgRiQKBgQDUGyJsJky50F4kXUevIfQzLvXjeIBfIp/x3XUi0vQqHNUsljEmRYnfZURnaKxRWRCBafowUUPUNxak/7PwnawOOvCT1IUQED8xHDDEg+teQAK0gBapQSxUpqiE853dCvxA8aC/GLx5boZgMKJJqdg8N9bxdBvoHqs1Rz2Kdghrgg==".getBytes();
        //privateKey = keyStr.getBytes();
        algorithm = "SHA256withRSA";
    }
    
    public void init(String pulickeystr,String privatekeystr) {
    	
    	publicKey =  Base64.decodeBase64(pulickeystr);
    	privateKey = Base64.decodeBase64(privatekeystr);
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
