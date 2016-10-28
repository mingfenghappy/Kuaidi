package com.ins.kuaidi.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.ins.kuaidi.R;
import com.ins.kuaidi.common.LoadingViewUtil;
import com.ins.kuaidi.utils.GlideUtil;
import com.sobey.common.view.singlepopview.MySinglePopupWindow;

import java.util.ArrayList;

public class PayDetailActivity extends BaseBackActivity implements View.OnClickListener{

    private ViewGroup showingroup;
    private View showin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydetail);
        setToolbar();

        initBase();
        initView();
//        initCtrl();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initBase() {
    }

    private void initView() {
        showingroup = (ViewGroup) findViewById(R.id.showingroup);
    }

    private void initData() {
        showin = LoadingViewUtil.showin(showingroup, R.layout.layout_loading, showin);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //加载成功
                initCtrl();
                LoadingViewUtil.showout(showingroup, showin);

                //加载失败
//                LoadingViewUtil.showin(showingroup,R.layout.layout_lack,showin,new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        initData();
//                    }
//                });
            }
        }, 1000);
    }

    private void initCtrl() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_tripdetail_totaydetail:
                break;
        }
    }
}