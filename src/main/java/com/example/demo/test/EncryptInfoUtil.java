package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 处理加密数据实现类
 */
public class EncryptInfoUtil {

    /**
     * 处理商户发送的加密数据
     *
     * @param data       请求报文密文
     * @param encryptKey 根据商户公钥得到的加密随机串
     * @param privateKey 私钥
     * @param publicKey  公钥
     * @return
     */
    public DecodeDataVo decodeEncryptData(String data, String encryptKey, String privateKey, String publicKey) {
        DecodeDataVo dataVo = DecodeDataVo.getInstance();
        // setp1. 根据商户私钥，解密客户端加密的随机密钥
        String decryptRandomKey;
        try {
            decryptRandomKey = RSAUtil.decrypt(encryptKey, privateKey.replaceAll("\\s", ""));
        } catch (Exception e) {
            dataVo.setResultCode("***");// todo 自己系统业务码
            dataVo.setResultMsg("解密失败");
            return dataVo;
        }
        // setp2. 根据setp1解密出的随机密钥来进行解密报文操作
        String decryptData = AESUtil.decryptFromBase64(data, decryptRandomKey);
        // setp3. 将解密的json解析为map
        TreeMap<String, String> map = JSON.parseObject(decryptData, new TypeReference<TreeMap<String, String>>() {
        });
        String sign = StringUtils.trimToEmpty(map.get("sign"));
        // setp4. 将明文map值进行验签
        StringBuilder signData = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.equals(entry.getKey(), "sign")) {
                continue;
            }
            signData.append(entry.getValue() == null ? "" : entry.getValue());
        }

        boolean passSign = RSAUtil.checkSign(signData.toString(), sign, publicKey.replaceAll("\\s", ""));

        if (passSign) {
            // setp5. 验签通过，需要进行返回客户端处理
            System.out.println("验签通过");
        } else {
            dataVo.setResultCode("***");// todo 自己系统业务码
            dataVo.setResultMsg("***");
            return dataVo;
        }
        dataVo.setData(decryptData);
        return dataVo;
    }

    /**
     * 加密数据
     *
     * @throws Exception
     */
    public EncryptDataVo encryptData(String data, String privateKey, String publicKey) throws Exception {
        // setp1. 组装报文
        TreeMap<String, Object> orderMap = JSON.parseObject(data, new TypeReference<TreeMap<String, Object>>() {
        });
        // setp2. 获取sign值
        StringBuffer signData = new StringBuffer();
        for (Map.Entry<String, Object> entry : orderMap.entrySet()) {
            signData.append(entry.getValue() == null ? "" : entry.getValue());
        }
        // setp3. RSA数字签名
        String signTemp = signData.toString();
        String sign = "";
        if (StringUtils.isNotEmpty(privateKey)) {
            sign = RSAUtil.sign(signTemp, privateKey.replaceAll("\\s", ""));// RSA签名，此处私钥需要剔除空格
        }
        // setp4. 请求报文转--->JSON
        orderMap.put("sign", sign);
        String json = JSON.toJSONString(orderMap);
        // setp5. 生成16位随机密钥
        String randomKey = RandomUtil.getRandomCompound(16);
        // setp6. 请求报文--->AES加密
        String encryptData = AESUtil.encryptToBase64(json, randomKey);
        // setp7. RSA加密
        String encryptRandomKey = RSAUtil.encrypt(randomKey, publicKey.replaceAll("\\s", ""));
        EncryptDataVo encryptDataVo = EncryptDataVo.getInstance();
        encryptDataVo.setData(encryptData);
        encryptDataVo.setEncryptKey(encryptRandomKey);
        // encryptDataVo.setAppId(appId); todo 需设置商户id
        return encryptDataVo;
    }

    /**
     * 加密list数据
     *
     * @throws Exception
     */
    public EncryptDataVo encryptListData(String data, String privateKey, String publicKey) throws Exception {
        // setp1. 组装报文
        List<Map> orderList = JSONArray.parseArray(data, Map.class);
        // setp2. 获取sign值
        StringBuffer signData = new StringBuffer();
        for (Map<String, Object> orderMap : orderList) {
            for (Map.Entry<String, Object> entry : orderMap.entrySet()) {
                signData.append(entry.getValue() != null ? entry.getValue() : "");
            }
        }
        // setp3. RSA数字签名
        String signTemp = signData.toString();
        String sign = "";
        if (StringUtils.isNotEmpty(privateKey)) {
            sign = RSAUtil.sign(signTemp, privateKey.replaceAll("\\s", ""));// RSA签名，此处私钥需要剔除空格
        }
        // setp4. 请求报文转--->JSON
        Map<String, String> transMap = Maps.newHashMap();
        transMap.put("sign", sign);
        transMap.put("data", data);
        String json = JSON.toJSONString(transMap);
        // setp5. 生成16位随机密钥
        String randomKey = RandomUtil.getRandomCompound(16);
        // setp6. 请求报文--->AES加密
        String encryptData = AESUtil.encryptToBase64(json, randomKey);
        // setp7. RSA加密
        String encryptRandomKey = RSAUtil.encrypt(randomKey, publicKey.replaceAll("\\s", ""));
        EncryptDataVo encryptDataVo = EncryptDataVo.getInstance();
        encryptDataVo.setData(encryptData);
        encryptDataVo.setEncryptKey(encryptRandomKey);
        // encryptDataVo.setAppId(appId); todo 需设置商户id
        return encryptDataVo;
    }

}
