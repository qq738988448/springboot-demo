package com.example.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Rsa工具类
 */
public class RSAUtil {

    private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);
    public static final String CHAR_ENCODING = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 指定key的大小
     */
    private static int KEYSIZE = 2048;

    public static void main(String[] args) throws Exception {
        generateKeyPair();
        // String source = "hello world!";
        // System.out.println("准备用公钥加密的字符串为：" + source);
        //
        // String cryptograph = encrypt(source, PUBLICKEY);// 生成的密文
        // System.out.print("用公钥加密后的结果为:" + cryptograph);
        // System.out.println();
        //
        // String target = decrypt(cryptograph, PRIVATEKEY);// 解密密文
        // System.out.println("用私钥解密后的字符串为：" + target);
        // System.out.println();
    }

    /**
     * 生成密钥对算法
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> generateKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();

        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
        logger.info("publicKeyBase64 = {}", publicKeyBase64);

        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();

        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);
        logger.info("privateKeyBase64 = {}", privateKeyBase64);

        // RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
        // BigInteger bint = rsp.getModulus();
        // byte[] b = bint.toByteArray();
        // String rsaPublicKey = new BASE64Encoder().encode(b);

        Map<String, String> map = new HashMap<>();
        map.put("publicKey", publicKeyBase64);
        map.put("privateKey", privateKeyBase64);

        // logger.info("RSAPublicKey = {}" , rsaPublicKey);
        // map.put("modulus", rsaPublicKey);

        return map;
    }

    /**
     * 公钥加密算法--->加密
     *
     * @param source
     *            数据源
     * @param publicKey
     *            公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String source, String publicKey) throws Exception {
        Key key = getPublicKey(publicKey);
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return new String(new BASE64Encoder().encode(b1));
    }

    /**
     * 私钥解密算法--->解密
     *
     * @param cryptograph
     *            密文
     * @param privateKey
     *            私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph, String privateKey) throws Exception {
        Key key = getPrivateKey(privateKey);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = new BASE64Decoder().decodeBuffer(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * 获取公钥
     *
     * @param key
     *            密钥字符串（经过base64编码）
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 获取私钥
     *
     * @param key
     *            密钥字符串（经过base64编码）
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 根据私钥进行数据签名
     *
     * @param content
     * @param privateKey
     * @return
     */
    public static String sign(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);

            Signature signature = Signature.getInstance("SHA1WithRSA");

            signature.initSign(priKey);
            signature.update(content.getBytes(CHAR_ENCODING));

            byte[] signed = signature.sign();

            return new String(new BASE64Encoder().encode(signed));
        } catch (Exception e) {
            logger.error("sign(...) 异常：", e, e.getStackTrace());
        }
        return null;
    }

    /**
     * 根据公钥进行签名验签
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean checkSign(String content, String sign, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);

            Signature signature = Signature.getInstance("SHA1WithRSA");

            signature.initVerify(pubKey);
            signature.update(content.getBytes("UTF-8"));

            boolean bverify = signature.verify(new BASE64Decoder().decodeBuffer(sign));
            return bverify;

        } catch (Exception e) {
            logger.error("checkSign(...) 异常：", e, e.getStackTrace());
        }
        return false;
    }

}
