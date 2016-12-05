package com.ins.middle.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ins.middle.R;
import com.ins.middle.common.AppData;
import com.ins.middle.utils.PackageUtil;
import com.sobey.common.utils.StrUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class DomainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text_domain_test;
    private TextView text_domain_deve_xie;
    private TextView text_domain_deve_li;
    private TextView text_domain_cust;
    private EditText edit_domain;
    private CheckBox check_domain_vali;
    private CheckBox check_domain_toast;
    private CheckBox check_domain_fresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);

        initBase();
        initView();
        initData();
        initCtrl();
    }

    private void initBase() {
    }

    private void initView() {
        text_domain_test = (TextView) findViewById(R.id.text_domain_test);
        text_domain_deve_xie = (TextView) findViewById(R.id.text_domain_deve_xie);
        text_domain_deve_li = (TextView) findViewById(R.id.text_domain_deve_li);
        text_domain_cust = (TextView) findViewById(R.id.text_domain_cust);
        edit_domain = (EditText) findViewById(R.id.edit_domain);
        check_domain_vali = (CheckBox) findViewById(R.id.check_domain_vali);
        check_domain_toast = (CheckBox) findViewById(R.id.check_domain_toast);
        check_domain_fresh = (CheckBox) findViewById(R.id.check_domain_fresh);

        findViewById(R.id.lay_domain_test).setOnClickListener(this);
        findViewById(R.id.lay_domain_deve_xie).setOnClickListener(this);
        findViewById(R.id.lay_domain_deve_li).setOnClickListener(this);
        findViewById(R.id.lay_domain_cust).setOnClickListener(this);
        findViewById(R.id.btn_go).setOnClickListener(this);
    }

    private void initData() {
    }

    private void initCtrl() {
        AppData.Config.showVali = true;
        AppData.Config.showTestToast = true;
        AppData.Config.showFreshBtn = true;
        check_domain_vali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppData.Config.showVali = isChecked;
            }
        });
        check_domain_toast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppData.Config.showTestToast = isChecked;
            }
        });
        check_domain_fresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppData.Config.showFreshBtn = isChecked;
            }
        });
        String domain = AppData.App.getDomain();
        if (!StrUtils.isEmpty(domain)) {
            edit_domain.setText(domain);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.lay_domain_test) {
            edit_domain.setText(text_domain_test.getText());
        } else if (i == R.id.lay_domain_deve_xie) {
            edit_domain.setText(text_domain_deve_xie.getText());
        } else if (i == R.id.lay_domain_deve_li) {
            edit_domain.setText(text_domain_deve_li.getText());
        } else if (i == R.id.lay_domain_cust) {
            edit_domain.setText(text_domain_cust.getText());
        } else if (i == R.id.btn_go) {
            String domain = edit_domain.getText().toString();
            if (!StrUtils.isEmpty(domain)) {
                AppData.Url.domain = "http://" + domain + "/Carpooling/";
                modify();
                AppData.App.saveDomain(domain);
                startActivity(PackageUtil.getSmIntent("LoadUpActivity"));
                finish();
            }
        }
    }

    private void modify() {
        try {
            Object o = AppData.Url.class.newInstance();//获取对象
//        Field f=Constants.class.getField("param1");//根据key获取参数
            Field[] field = AppData.Url.class.getFields();//获取全部参数
            for (int i = 0; i < field.length; i++) {
                Field f = field[i];
                if ((Modifier.STATIC + Modifier.PUBLIC) == f.getModifiers()) {//获取字段的修饰符，public 1,static 8
                    if (f.getType().getName().indexOf("String") != -1) {
                        String strtemp = (String) f.get(o);
                        f.set(o, replace(AppData.Url.domain, strtemp));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String replace(String domain, String str) {
        int index = str.indexOf("Carpooling/");
        if (str.endsWith("Carpooling/")) {
            return domain;
        } else {
            String substring = str.substring(index + "Carpooling/".length());
            return domain + substring;
        }
    }
}
