package com.example.lyw.nasproject.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.lyw.nasproject.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       ImageView imageView = (ImageView) findViewById(R.id.image);
        try {
            InputStream inputStream = getAssets().open("");

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds =true;
            BitmapFactory.decodeStream(inputStream, null, options);
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            //设置显示图片的中心区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options1 = new BitmapFactory.Options();
            options1.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(outWidth / 2 - 100, outHeight / 2 - 100, outWidth / 2 + 100, outHeight / 2 + 100), options1);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成RSA密钥对，包括PublicKey，PrivateKey
     * @param keyLength
     * @return
     */
    public static KeyPair generateRSAKeyPair(int keyLength){
        try {
            KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
            rsa.initialize(keyLength);
            return rsa.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
