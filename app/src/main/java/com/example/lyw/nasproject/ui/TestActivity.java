package com.example.lyw.nasproject.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.lyw.nasproject.R;
import com.example.lyw.nasproject.widget.LoadingDialog;

public class TestActivity extends FragmentActivity {

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mLoadingDialog =  new LoadingDialog();
        findViewById(R.id.button).setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mLoadingDialog.show(getSupportFragmentManager(),"");
                    }
                });
    }
}
