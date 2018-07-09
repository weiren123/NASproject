package com.example.lyw.nasproject.utils;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Administrator on 2018/7/9.
 * 签名和校验类
 */

public class RSASignature {
    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * RSA签名
     * @param content 待签名数据
     * @param privateKey 商户私钥
     * @param encode 字符集编码
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String encode)
    {
        try
        {
            PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( Base64.decode(privateKey,Base64.DEFAULT) );

            KeyFactory keyf                 = KeyFactory.getInstance("RSA");
            PrivateKey priKey               = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(encode));

            byte[] signed = signature.sign();

            return Base64.encodeToString(signed,Base64.DEFAULT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String sign(String content) {
        try {
            String privateKey = RsaKeyUtil.loadPrivateKeyByFile();
            PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( Base64.decode(privateKey,Base64.DEFAULT) );
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update( content.getBytes());
            byte[] signed = signature.sign();
            return Base64.encodeToString(signed,Base64.DEFAULT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean doCheck(String content, String sign) {
        try {
            String publicKey = RsaKeyUtil.loadPublicKeyByFile();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey,Base64.DEFAULT);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update( content.getBytes() );

            boolean bverify = signature.verify( Base64.decode(sign,Base64.DEFAULT) );
            return bverify;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
