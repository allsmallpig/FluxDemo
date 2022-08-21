package jdk8Practise.task;

/**
 * @author ltz
 * @version V1.0
 * @date 2021/1/15 11:08
 * @Description
 * @email goodmanalibaba@foxmail.com
 */

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

/**
 * 小程序 AES 解密工具
 *
 * @author Kilde
 */
@Data
public class WXBizDataCrypt {

    public static void main(String[] args) {

        String appId = "wx4f4bc4dec97d474b";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String encryptedData = "iUhaOjeR0C8l75Kz9PPtTIJCeZFK+s1b5dE4I+WO2l4gFYoclRG0afO+8NBAAyUwz6c6Rtm/s56S1tp2WlgumMK7eb7zmS/eA0s89mDHCMmgI9ca3QJzPSOTPSJh8ybEptTyA9kfpxJFrAckkzo1JZX8V3uOKjj1g23jhdHeh6Ld+QRLUfEcjs8bYJAwG63B475iAjM3xXyhsQGwkNU2oQ==";
        String iv = "r7BXXKkLb8qrSNn05n0qiA==";

        WXBizDataCrypt pc = new WXBizDataCrypt(appId,sessionKey);
        JSONObject decrypt = pc.decrypt(encryptedData, iv);

        System.out.println(decrypt.toString());

    }


    public static JSONObject getMoreInfoFromEncryptedData(String appId, String sessionKey, String encryptedData, String iv) {
        WXBizDataCrypt pc = new WXBizDataCrypt(appId, sessionKey);
        JSONObject decrypt = pc.decrypt(encryptedData, iv);
        return decrypt;
    }

    private String appId;
    private String sessionKey;

    public WXBizDataCrypt(String appId, String sessionKey) {
        this.appId = appId;
        this.sessionKey = sessionKey;
    }


    /**
     * 解密成json
     *
     * @param encryptedData
     * @param iv
     * @return
     */
    public  JSONObject decrypt(String encryptedData, String iv) {
        byte[] encryptedDataDecode = Base64.getDecoder().decode(encryptedData);
        byte[] sessionKeyDecode = Base64.getDecoder().decode(this.sessionKey);
        byte[] ivDecode = Base64.getDecoder().decode(iv);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] decodeData = decode(encryptedDataDecode, sessionKeyDecode, ivDecode);
        String stringData = new String(decodeData);
        JSONObject jsonObject = JSONObject.parseObject(stringData);
        return jsonObject;
    }


    /**
     * 解密算法 AES-128-CBC
     * 填充模式 PKCS#7
     *
     * @param encryptedDataDecode 目标密文
     * @return
     * @throws Exception
     */
    private byte[] decode(byte[] encryptedDataDecode, byte[] sessionKeyDecode, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(sessionKeyDecode, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(iv));// 初始化
            byte[] result = cipher.doFinal(encryptedDataDecode);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理iv
     *
     * @param iv
     * @return
     * @throws Exception
     */
    private AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }


}
