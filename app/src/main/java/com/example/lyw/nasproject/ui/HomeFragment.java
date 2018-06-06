package com.example.lyw.nasproject.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.base.BaseFragment;
import com.example.lyw.nasproject.base.contract.HomeContract;
import com.example.lyw.nasproject.presenter.HomePresenter;
import com.example.lyw.nasproject.utils.SystemUtil;
import com.example.lyw.nasproject.widget.BottomDialog;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/5.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.scrolling_header)
    ImageView scrollingHeader;
    @BindView(R.id.twoV)
    LinearLayout twoV;
    @BindView(R.id.edit_search)
    LinearLayout editSearch;
    @BindView(R.id.iv_twov)
    ImageView iVTwoV;
    private BottomDialog dialog;
    private Bitmap mBitmap;

    @Override
    public void showContent() {
        iVTwoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showZXcode();
            }
        });
    }

    @Override
    public void showZXcode() {
        dialog = new BottomDialog(mActivity);
        dialog.setContentView(R.layout.dialog);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        dialog.getWindow().setLayout(width,
                height / 2);
        ImageView  ivCode = (ImageView) dialog.findViewById(R.id.iv_code);
        Button button = (Button) dialog.findViewById(R.id.btn);
        final String textContent = "ssssssss";
        mBitmap = CodeUtils.createImage(textContent, 400, 400,null);
        ivCode.setImageBitmap(mBitmap);
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemUtil.copyToClipBoard(mContext,textContent);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getFragmentComponet().inject(this);
    }

    @Override
    protected void initEventOrData() {
        mPresenter.getHomeData();
    }

}
