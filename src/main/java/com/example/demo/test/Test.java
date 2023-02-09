package com.example.demo.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Test {


    public static void main(String[] args) throws Exception {
        EncryptInfoUtil encryptInfoUtil = new EncryptInfoUtil();
        // 加密数据
        String data = "{\"aaa\":\"bbb\"}";
        // 加密
        EncryptDataVo encryptDataVo = encryptInfoUtil.encryptData(data, privateKey1, publicKey2);
        // 解密
        DecodeDataVo decodeDataVo = encryptInfoUtil.decodeEncryptData(encryptDataVo.getData(), encryptDataVo.getEncryptKey(), privateKey2, publicKey1);
        System.out.println(decodeDataVo.getData());

    }


    static final String publicKey1 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs6fQJF+bL3jOu8OoA1/U\n" +
            "RlAM78heG4+zaGbbnhQVWevPaax7U26kYs6Fe9Q5+V9Pc/oT21vTuHQi4Bk82pFW\n" +
            "aQL4mws+0R8GghI9Jc1C06xZnAz9rjApRb63gM2m7qolEnjELaPgbHatlmg5ZcdY\n" +
            "GRfP/8PRRFs2e/9JpMBmcUrApFlWMgaglVz5o4WPwWS4aFqYQlgNYkFovnZw2jJs\n" +
            "m2s+07b0LsYNEaAu98pp6fwG+qv9iTvk0hWa9zyUysOcXtLaYmAbMZIDZuQj7PIW\n" +
            "0StoDvTC8oDNtscsWctUexcGlFra6phrOgd+NdDRdeoj+BSgqpk2nNpU5svRWwpI\n" +
            "2wIDAQAB";
    static final String publicKey2 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw6mFQC1KGZWI7nGpiXJ4\n" +
            "5M9ToYOnvUX0NUzdpOEcG2c1EdNoHrbIynWWPgBFvl8glwjoj1wRSte8tPXI5jdj\n" +
            "8t/zuKn0eKFZ1THcCwUYgOnd3TDwhS4mQJs9dzcIYI1bHY4Lk/dX4JTmen105cnU\n" +
            "xaL0tmqtQn8cCDevy4DyKD3n/HAa6PNsYX0xk/9x8wTemRYL9Lt7qgJ3j1aqhkus\n" +
            "X621HiGA0HNkhLedUIogNjCdL6X7C/tfpWvJXyVBvJ0M6tbxUkSjEcc1uG1cMu9c\n" +
            "DJ8Ja2b+dHlAYEtDyGCO2JZHc4siVDTFguZg8CZOz0Bzjr5QaRuNaA9aAdVzMP9R\n" +
            "mwIDAQAB";

    static final String privateKey1 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzp9AkX5sveM67\n" +
            "w6gDX9RGUAzvyF4bj7NoZtueFBVZ689prHtTbqRizoV71Dn5X09z+hPbW9O4dCLg\n" +
            "GTzakVZpAvibCz7RHwaCEj0lzULTrFmcDP2uMClFvreAzabuqiUSeMQto+Bsdq2W\n" +
            "aDllx1gZF8//w9FEWzZ7/0mkwGZxSsCkWVYyBqCVXPmjhY/BZLhoWphCWA1iQWi+\n" +
            "dnDaMmybaz7TtvQuxg0RoC73ymnp/Ab6q/2JO+TSFZr3PJTKw5xe0tpiYBsxkgNm\n" +
            "5CPs8hbRK2gO9MLygM22xyxZy1R7FwaUWtrqmGs6B3410NF16iP4FKCqmTac2lTm\n" +
            "y9FbCkjbAgMBAAECggEAEtwrHaZrxDAKg+umV7RXtIny0blXJ82OKAekRpQVKbt3\n" +
            "qsBQshGLcFP972H4NBWtYZ9/NigNse9/OcdsfAWF7MBDGQO1NH7tT/paMP27Zj1R\n" +
            "qvAyMXcc3hWHbh4oyCVUfvvunFv302siDggi/mxO81Nsoo0MCwEIVzwVnxYTqxDg\n" +
            "2RLCvS1C4Q+cZ4rFPK4C1/GhSig08CqpDcUkReRFSBAz1PeEWIowXuCrN65ZKn0K\n" +
            "LTj44sY4VecsUBwkyaoIFQuUv3R7mQJDpF1ueaMYVNVRhDvH7Xko6lA2E1I109hY\n" +
            "1fRYtZHt19STsLfpM8kDkqfODKtAPQDj+r3Sl7+kAQKBgQDeuBhWLodfH3JfSuZ/\n" +
            "iNOzFBgg/dHtFIT9U5bxQi8Zx7dLCjeVgGG9XPmMEVBXQeL8U/uyKEoTN/IZdkjx\n" +
            "odPQJCyBGYA/ZYLg/n3IOR/hU0AbJwEQLoqau/pniZtbT/CFpsjzTqJf8G5zHBfn\n" +
            "6IqjlqvFOH3bsguZ3pKr5opXywKBgQDOgFyKu3W6YPyOxLU2C8th+2Ld0w3mPkWa\n" +
            "Iucpc/lh2Ntl5te69Ur5o1jdh3XfuSHMWl3otYR03zeqT9GFLSXo1xcZxF0Q4GFq\n" +
            "yzATZvZztV/ApMcwopTraJmN6OI80CFE3GjgGJapSYH9zkbU4AtIlC+tb/mJDBrm\n" +
            "D0zkWtERMQKBgCGx4mSvd2WJwWjvUP38emK+aIQdQnRGxXP6AH0RkUSqNW3R5uTk\n" +
            "XmUiutJZEDmTSz73ib3DYKmVrjfx8Ek2PXBg+Kazb/anaeyWM/tlwQ/641j4Rq3n\n" +
            "DKsqQ1EEvY9MAcyGliJRRDmVi3A40NkSEl97fRNCX7AsOJsX1ELThxyPAoGBAJC/\n" +
            "JR8Ru7oDFcU1WRgjOFLcFTcMI6gKaltar9I8use9bDyHjRw9qGpH95iAxT0m1ewa\n" +
            "Q81Evy308V3uSR0iwnntmwGyxNF5lo93EaHoAESnFBFC72TF8cD5NvlkDoE2Grvc\n" +
            "C59ZXPIY1oeDud6plP8w8CKQZMeNtqUggHNovTdhAoGASzLTJ4pd/wLtmzxvb2Ng\n" +
            "iXL+kdKjd3GGIuQoyi49zagZLl9cUKmc1FjtWgtHBqQ9xNgFMr0dP+Veisv0v+Ya\n" +
            "ZMFUVsSIU/Is5LKoBzQ65g9L0yNJLz6hjlP0AiY0pjwggBjI2/Zo4ucDQx+Z+o1S\n" +
            "vOVXZw70176F2WA1EXxQAZI=";

    static final String privateKey2 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDqYVALUoZlYju\n" +
            "camJcnjkz1Ohg6e9RfQ1TN2k4RwbZzUR02getsjKdZY+AEW+XyCXCOiPXBFK17y0\n" +
            "9cjmN2Py3/O4qfR4oVnVMdwLBRiA6d3dMPCFLiZAmz13NwhgjVsdjguT91fglOZ6\n" +
            "fXTlydTFovS2aq1CfxwIN6/LgPIoPef8cBro82xhfTGT/3HzBN6ZFgv0u3uqAneP\n" +
            "VqqGS6xfrbUeIYDQc2SEt51QiiA2MJ0vpfsL+1+la8lfJUG8nQzq1vFSRKMRxzW4\n" +
            "bVwy71wMnwlrZv50eUBgS0PIYI7YlkdziyJUNMWC5mDwJk7PQHOOvlBpG41oD1oB\n" +
            "1XMw/1GbAgMBAAECggEAAv0O3PkUHansCoBP75qBADWueFQTds+fuESxSfhqPnzo\n" +
            "EZqiB34ROQ5sugu1BQBc9hGbw7zLQtJivnzWkbWc4DPNfC+UXVdRkPEPBFgSlL19\n" +
            "twgtVEttKCV23eBIT3k5QA1QXfU0MWj00EAkTfI0PsSLalHczZw1aGa5V6ljiXvJ\n" +
            "t/0770mN97tal047iE+aQjs9g+Mib9z4K0XJwBUlcslVWd03Q6PvNC+0sPMQ1BQF\n" +
            "dRxlNjqGnDj7xBfVf4DHN8PeHoWgiHng5CYOIGTOQHvh+ZIz3aikGTa3MJ81L1PU\n" +
            "B6eLAGIJ4bHR8fBXaGH3By2ZcMyLTGwQDXmzQWa4QQKBgQDzQ3ncKJyFhuC2/Gut\n" +
            "K/sfjzh3fDaw9zcGczfqjtBDmsCSqQjn8Vx+pCztYuW+Vfho4o9fkonp+6OsIcIj\n" +
            "VD8nnQEUX0T8EgX0ABSbhk5HyNMviAuBzHDe4hOe7M+jcaV9ipOJKTsECu0lMIhI\n" +
            "K+hMsrOjnRk+rXB9nmvAfpUhoQKBgQDN6AfpMwsi4W8hKy/QZxl06kgIzUJ4mugT\n" +
            "s4gcUs8oVsGfd0C3V36Do/QVzXsCMNFCE9bYLpPFQ5aAtGka/y9r8TvvQ14CBII/\n" +
            "7EbHuGkpauQHjN7T5o95022AtiAvjIgZETpvgCDRIySL260GtWgonPfY8FQe6ddC\n" +
            "Suy1OnohuwKBgFfvCjvFCl9SFCTRNfMRNOOBl/3JDtQFaQSR3aKSIUJuzDG3nJqL\n" +
            "Ks40dnEREM8usG7phr4bcHL+HXn5cf3nVDTzkhJVJdCgizpaVRirAdz9ASnrWY1/\n" +
            "40UOH92Q6LXCPPoWf6JTHxwk/vg0hO7hwEUJS9pWzWtUspcWDwk0OKBBAoGBALE/\n" +
            "7I9ywUNljKSg6KgeGGvswdXR3Ea2EtHi2miHYIz5IfSEU9GAIAhyOswJ9roMDBc1\n" +
            "vNycO9i0NfrVUyVKRyhEobN16pcSCo28d7G1rdFZkrJtMeR3tUrKFpmWLl7AOuhZ\n" +
            "TFRvl9Bw0CUVFf2Qa/mKJlE5kTCLXCJVHS9lB33BAoGAJXZ/oqj1IMCIcNGp+DqA\n" +
            "EvIQ26eNSkMNZkJw0grFSlsgg/n/KZjp70yj5JNesJsnHjBNHp/2SucvdzT1KOOY\n" +
            "9aqydqAayGFlgzpRPBuZexZs5IL2AhUnh6qTH5lLQU46pm1/eu/oqkdBqnWjIDC6\n" +
            "TOv6ehVR0yDQ8GoUZCGskqg=";
}
