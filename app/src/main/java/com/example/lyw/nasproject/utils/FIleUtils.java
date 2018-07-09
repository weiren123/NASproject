package com.example.lyw.nasproject.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FIleUtils {
    public static String getPrivateKeyPath(){
        String rootPath = getRootPath();
        String filePath = rootPath + "/privateKey.keystore";
        File file = new File(filePath);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    public static String getPublicKeyPath(){
        String rootPath = getRootPath();
        String filePath = rootPath + "/publicKey.keystore";
        File file = new File(filePath);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    private static String getRootPath() {
        String filepath= Environment.getExternalStorageDirectory().getPath() + File.separator + "mykey"+ File.separator + "pripub";

        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
        }

        return filepath;
    }

}
