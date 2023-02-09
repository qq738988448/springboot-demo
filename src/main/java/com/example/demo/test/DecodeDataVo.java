package com.example.demo.test;

import java.io.Serializable;

/**
 * 解密数据
 */
public class DecodeDataVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String resultMsg;

    private String resultCode;

    private String data;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static DecodeDataVo getInstance() {
        return new DecodeDataVo();
    }
}
