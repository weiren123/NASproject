package com.example.lyw.nasproject.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.example.lyw.nasproject.R;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

/**
 * Created by cyandev on 2016/12/14.
 */
public class HeaderFloatTwoVBehavior extends CoordinatorLayout.Behavior<View> {

    private WeakReference<View> dependentView;
    private ArgbEvaluator argbEvaluator;

    public HeaderFloatTwoVBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        argbEvaluator = new ArgbEvaluator();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency != null && dependency.getId() == R.id.scrolling_header) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Resources resources = getDependentView().getResources();
        final float progress = 1.f -
                Math.abs(dependency.getTranslationY() / (dependency.getHeight() - resources.getDimension(R.dimen.collapsed_header_height)));

        // Translation
        final float collapsedOffset = resources.getDimension(R.dimen.collapsed_float_twov_offset_y);
        final float initOffset = resources.getDimension(R.dimen.init_float_twov_offset_y);
        final float translateY = collapsedOffset + (initOffset - collapsedOffset) * progress;
        child.setTranslationY(translateY);


        // Background
//        child.setBackgroundColor((int) argbEvaluator.evaluate(
//                progress,
//                resources.getColor(R.color.colorCollapsedBackground),
//                resources.getColor(R.color.colorInitBackground)));
        int width = parent.getWidth();
        // Margins
        final float collapsedMargin = resources.getDimension(R.dimen.collapsed_float_margin);
        final float initMargin = resources.getDimension(R.dimen.init_float_margin);
        Logger.e("width:"+ width);
        float translateX = width /2 - width /2* progress;
        Logger.e("11translateX:"+translateX);
        if(translateX> width /2-170){
            translateX = width /2-170;
        }
        child.setTranslationX(translateX);
//        final int margin = (int) (collapsedMargin + (initMargin - collapsedMargin) * progress);
//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        lp.setMargins(margin, 0, margin, 0);
//        child.setLayoutParams(lp);
        return true;
    }

    private View getDependentView() {
        return dependentView.get();
    }

}
