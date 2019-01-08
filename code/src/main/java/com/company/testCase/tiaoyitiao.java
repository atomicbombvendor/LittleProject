package com.company.testCase;

import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import okhttp3.*;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
//代码链接： https://zhaoxuyang.com/微信跳一跳抓包改分-java实现/
public class tiaoyitiao {
    public static void main(String[] args) {
            String sessionid = "ZYFOFW06gJexCvao2g78UklHkPaXqmcX9f2cGD6grKZH3stLu8KZXilrVPLACK//3z2e7rvQitYRoYmw2EzQlBJ6OMxoW4vqR8bTBak8OdXHnBgp+eZ72zjd7WDqsaoa2qs0qoC/SOnwDVAAy7F8kA==";
            //这是跳一跳的分数
            String score = "401";
            //这是跳一跳的时间
            String times = "700";
            String result = Util.postData(score, times, sessionid);
            System.out.println(result);
    }
}

//调用加密执行请求的工具类
class Util {
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private static final String WXGAME_URL = "https://mp.weixin.qq.com/wxagame/wxagame_settlement";
    private static final String SESSIONID_ERROR = "SESSIONID有误，请检查";

    private static String getActionData(String sessionKey, String encryptedData, String iv) {
        byte[] sessionKeyBy = sessionKey.getBytes();
        byte[] en = new byte[0];
        try {
            en = encryptedData.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] ivBy = iv.getBytes();
        byte[] enc = Pkcs7Encoder.encryptOfDiyIV(en, sessionKeyBy, ivBy);
        return new String(Base64.toBase64String(enc));
    }

    public static String postData(String score, String times, String session_id) {
        String result = null;
        String content = "{\"score\": " + score + ", \"times\": " + times + "}";
        String AES_KEY;
        try {
            AES_KEY = session_id.substring(0, 16);
        } catch (Exception e) {
            return SESSIONID_ERROR;
        }

        String AES_IV = AES_KEY;
        OkHttpClient okHttpClient = new OkHttpClient();
        String actionData = Util.getActionData(AES_KEY, content, AES_IV);
        String json = "{\"base_req\":{\"session_id\":\"" + session_id + "\",\"fast\":1},\"action_data\":\"" + actionData + "\"}";
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(WXGAME_URL)
                .header("Accept","*/*")
                .header("Accept-Language","zh-cn")
                .header("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Mobile/14E304 MicroMessenger/6.6.1 NetType/WIFI Language/zh_CN")
                .header("Content-Length","680")
                .header("Content-Type","application/json")
                .header("Referer","https://servicewechat.com/wx7c8d593b2c3a7703/5/page-frame.html")
                .header("Host","mp.weixin.qq.com")
                .header("Connection","keep-alive")
                .post(requestBody)
                .build();
        ResponseBody responseBody = null;
        try {
            responseBody = okHttpClient.newCall(request).execute().body();
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
        return result;
    }
}

//Pkcs7加密解密工具类
class Pkcs7Encoder {
    // 算法名称
    static  final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    static  final String algorithmStr = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;
    boolean isInited = false;

    //默认对称解密算法初始向量 iv
    static byte[] iv = { 0x30, 0x31, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36, 0x30, 0x37, 0x30, 0x38 };

    public static void init(byte[] keyBytes) {

        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 加密方法
     *      --使用默认iv时
     * @param content
     *            要加密的字符串
     * @param keyBytes
     *            加密密钥
     * @return
     */
    public static byte[] encrypt(byte[] content, byte[] keyBytes) {
        byte[] encryptedText =  encryptOfDiyIV(content,keyBytes,iv);
        return encryptedText;
    }


    /**
     * 解密方法
     *      --使用默认iv时
     * @param encryptedData
     *            要解密的字符串
     * @param keyBytes
     *            解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes) {
        byte[] encryptedText = decryptOfDiyIV(encryptedData,keyBytes,iv);
        return encryptedText;
    }
    /**
     * 加密方法
     *      ---自定义对称解密算法初始向量 iv
     * @param content
     *              要加密的字符串
     * @param keyBytes
     *              加密密钥
     * @param ivs
     *         自定义对称解密算法初始向量 iv
     * @return 加密的结果
     */
    public static byte[] encryptOfDiyIV(byte[] content, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }
    /**
     * 解密方法
     *
     * @param encryptedData
     *            要解密的字符串
     * @param keyBytes
     *            解密密钥
     * @param ivs
     *         自定义对称解密算法初始向量 iv
     * @return
     */
    public static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes,byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }
}
