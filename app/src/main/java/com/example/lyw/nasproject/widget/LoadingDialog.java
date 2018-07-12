package com.example.lyw.nasproject.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.lyw.nasproject.R;

import static android.view.animation.AnimationUtils.loadAnimation;

/**
 * Created by Administrator on 2018/7/12.
 */

public class LoadingDialog extends DialogFragment {
    private Dialog mDialog;
    private ImageView mouse;
    private Animation operatingAnim;
    private ImageView ivLefteye;
    private ImageView ivRightteye;
    private Animation lefteyeAnim;
    private Animation righteyeAnim;
    private EyelidView leftEyelid,rightEyelid;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(mDialog == null){
            mDialog = new Dialog(getActivity(), R.style.cart_dialog);
            mDialog.setContentView(R.layout.catloading_main);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.getWindow().setGravity(Gravity.CENTER);

            operatingAnim = loadAnimation(getActivity(),R.anim.animition);
            lefteyeAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.animition);
            righteyeAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.animition);
            LinearInterpolator linearInterpolator = new LinearInterpolator();
            operatingAnim.setInterpolator(linearInterpolator);
            lefteyeAnim.setInterpolator(linearInterpolator);
            righteyeAnim.setInterpolator(linearInterpolator);
            mouse = (ImageView) mDialog.findViewById(R.id.mouse);
            ivLefteye = (ImageView) mDialog.findViewById(R.id.eye_left);
            ivRightteye = (ImageView) mDialog.findViewById(R.id.eye_right);
            leftEyelid = (EyelidView) mDialog.findViewById(R.id.eyelid_left);
            rightEyelid = (EyelidView) mDialog.findViewById(R.id.eyelid_right);

        }

        return mDialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        mouse.setAnimation(operatingAnim);
        ivLefteye.setAnimation(lefteyeAnim);
        ivRightteye.setAnimation(righteyeAnim);
        leftEyelid.startLoading();
        rightEyelid.startLoading();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mDialog = null;
        System.gc();
    }
}
