package com.example.lyw.nasproject.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.model.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nebulas.Constants;
import io.nebulas.api.SmartContracts;
import io.nebulas.model.GoodsModel;
import io.nebulas.utils.Util;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.scrolling_header)
    ImageView scrollingHeader;
    @BindView(R.id.edit_search)
    LinearLayout editSearch;
    @BindView(R.id.tl_2)
    CommonTabLayout mTabLayout_2;
    @BindView(R.id.btn)
    Button btn;

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        inttData();
    }

    private void inttData() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasPay(v);
            }
        });
    }
    public void nasPay(View view){

        if (view == null) {
            return;
        }
        serialNumber = Util.getRandomCode(Constants.RANDOM_LENGTH);

        String to = "n1ULQeCi1FEbDn4tktufhzZXCTnv4eJQb4C";//入账钱包地址，钱包地址，钱包地址

        GoodsModel goods = new GoodsModel();
        goods.name = "ss";
        goods.desc = "aa";

        SmartContracts.pay(this, Constants.MAIN_NET, goods,  to, value , serialNumber);

    }
    private void initView() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout_2.setTabData(mTabEntities);
    }
}
