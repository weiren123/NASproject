package com.example.lyw.nasproject.utils;

import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Administrator on 2018/7/9.
 *  秘钥管理类
 */

public class RsaKeyUtil {
    /**
     * 随机生成密钥对
     */
    public static void genKeyPair(Handler handler) {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位(这里是常用值)
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString = Base64.encodeToString(publicKey.getEncoded(),Base64.DEFAULT);
            // 得到私钥字符串
            String privateKeyString = Base64.encodeToString(privateKey.getEncoded(),Base64.DEFAULT);
            Log.e("publicKeyString",publicKeyString);
            Log.e("privateKeyString",privateKeyString);
            // 将密钥对写入到文件（文件后缀不重要只要系统能识别的文件都行，只是为了存储用）
            String publicKeyPath = FIleUtils.getPublicKeyPath();
            String privateKeyPath = FIleUtils.getPrivateKeyPath();
            FileWriter pubfw = new FileWriter(publicKeyPath);
            FileWriter prifw = new FileWriter(privateKeyPath);
            BufferedWriter pubbw = new BufferedWriter(pubfw);
            BufferedWriter pribw = new BufferedWriter(prifw);
            pubbw.write(publicKeyString);
            pribw.write(privateKeyString);
            pubbw.flush();
            pubbw.close();
            pubfw.close();
            pribw.flush();
            pribw.close();
            prifw.close();

            handler.sendEmptyMessage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载公钥
     */
    public static RSAPublicKey loadPublicKey() throws Exception {
        String publicKeyStr = loadPublicKeyByFile();
        try {
            byte[] buffer = Base64.decode(publicKeyStr,Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载公钥
     * @throws Exception 加载公钥时产生的异常
     */
    public static String loadPublicKeyByFile() throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FIleUtils.getPublicKeyPath()));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            Log.e("publicKeyStr",sb.toString());
            return sb.toString();
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    public static RSAPrivateKey loadPrivateKey() throws Exception {
        String privateKeyStr = loadPrivateKeyByFile();
        try {
            byte[] buffer = Base64.decode(privateKeyStr,Base64.DEFAULT);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     * @throws Exception
     */
    public static String loadPrivateKeyByFile() throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FIleUtils.getPrivateKeyPath()));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            Log.e("privateKeyStr",sb.toString());
            return sb.toString();
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }
}
