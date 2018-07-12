package com.example.lyw.nasproject.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by Administrator on 2018/7/12.
 */

public class EyelidView extends View {
    private Paint paint;
    private float eyeHeight;
    private float progress;
    private int duration = 1012;
    private ValueAnimator valueAnimator;
    private boolean isFill=true;

    public EyelidView(Context context) {
        this(context,null);
    }

    public EyelidView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EyelidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#d0ced1"));
        paint.setStyle(Paint.Style.FILL);

        valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(duration);
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

    }
    public void startLoading(){
        valueAnimator.start();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isFill){
            eyeHeight = progress * getHeight();
        }else {
            eyeHeight = (1.0f-progress) * getHeight();
        }
        eyeHeight = eyeHeight >= (getHeight() / 2) ? (getHeight() / 2) : eyeHeight;
        canvas.drawRect(0,0,getWidth(),eyeHeight,paint);
    }
}
