package com.example.lyw.nasproject.ui;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.base.BaseActivity;
import com.example.lyw.nasproject.base.contract.MainContract;
import com.example.lyw.nasproject.model.TabEntity;
import com.example.lyw.nasproject.presenter.MainPresenter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import io.nebulas.Constants;
import io.nebulas.api.SmartContracts;
import io.nebulas.model.GoodsModel;
import io.nebulas.utils.Util;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.fl_change)
    FrameLayout flChange;
    @BindView(R.id.tl_2)
    CommonTabLayout mTabLayout_2;
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private String[] mTitles = {"首页", "资讯", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String serialNumber;
    private String value = "100";

    @Override
    protected int getLayoutView() {
        return R.layout.activity_home;
    }

    public void nasPay(View view) {

        if (view == null) {
            return;
        }
        serialNumber = Util.getRandomCode(Constants.RANDOM_LENGTH);

        String to = "n1ULQeCi1FEbDn4tktufhzZXCTnv4eJQb4C";//入账钱包地址，钱包地址，钱包地址

        GoodsModel goods = new GoodsModel();
        goods.name = "ss";
        goods.desc = "aa";

        SmartContracts.pay(this, Constants.MAIN_NET, goods, to, value, serialNumber);

    }

    @Override
    protected void initInject() {

        getActivityComponten().inject(this);
    }

    @Override
    public void showFragment() {

//        new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                ToastUtil.shortShow("service");
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        };

        HomeFragment homeFragment = new HomeFragment();
        NewsWebFragment newsWebFragment = new NewsWebFragment();
        MyModelFragment myModelFragment = new MyModelFragment();
        mFragments2.add(homeFragment);
        mFragments2.add(newsWebFragment);
        mFragments2.add(myModelFragment);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout_2.setTabData(mTabEntities, this, R.id.fl_change, mFragments2);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getMainData();
    }
}
