package com.example.lyw.nasproject.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.lyw.nasproject.R;


/**
 * Created by Administrator on 2018/4/22.
 */

public class BottomDialog extends Dialog {
    public BottomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0xFFFFFF));
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mystyle);

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
    }
}
