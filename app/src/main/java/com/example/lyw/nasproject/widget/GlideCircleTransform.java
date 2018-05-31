package com.example.lyw.nasproject.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Administrator on 2018/4/19.
 */

public class GlideCircleTransform extends BitmapTransformation {

    public GlideCircleTransform(Context context) {
        super(context);
    }

    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
//        ByteArrayOutputStream mdataByte = new ByteArrayOutputStream();
//        source.compress(Bitmap.CompressFormat.JPEG, 100, mdataByte);
//        BitmapFactory.Options mopts = new BitmapFactory.Options();
//        mopts.inSampleSize = 6;
//        mopts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        Bitmap squared = BitmapFactory.decodeByteArray(mdataByte.toByteArray(), 0, mdataByte.size(), mopts).copy(Bitmap.Config.ARGB_8888, true);


        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
//            ByteArrayOutputStream dataByte = new ByteArrayOutputStream();
//            source.compress(Bitmap.CompressFormat.JPEG, 100, dataByte);
//            BitmapFactory.Options opts = new BitmapFactory.Options();
//            opts.inSampleSize = 6;
//            opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//            result = BitmapFactory.decodeByteArray(dataByte.toByteArray(), 0, dataByte.size(), opts).copy(Bitmap.Config.ARGB_8888, true);
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    @Override public String getId() {
        return getClass().getName();
    }
}
