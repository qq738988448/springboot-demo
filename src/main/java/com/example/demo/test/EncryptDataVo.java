package com.example.demo.test;

import java.io.Serializable;

/**
 * 加密返回数据
 */
public class EncryptDataVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;// 加密数据
    private String encryptKey;// 加密随机key
    private String appId;// 商户id

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public static EncryptDataVo resultError(String data) {
        EncryptDataVo instance = EncryptDataVo.getInstance();
        instance.setData(data);
        return instance;
    }

    public static EncryptDataVo getInstance() {
        return new EncryptDataVo();
    }
}
