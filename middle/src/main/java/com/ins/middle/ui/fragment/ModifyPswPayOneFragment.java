package com.ins.middle.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ins.middle.R;
import com.ins.middle.ui.activity.ModifyPswPayActivity;
import com.sobey.common.utils.KeyBoardUtil;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
public class ModifyPswPayOneFragment extends BaseFragment implements View.OnClickListener{

    private int position;
    private View rootView;
    private ViewGroup showingroup;
    private View showin;

    private View btn_go;
    private ModifyPswPayActivity activity;


    public static Fragment newInstance(int position) {
        ModifyPswPayOneFragment f = new ModifyPswPayOneFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments().getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modifypswpayone, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBase();
        initView();
        initData();
        initCtrl();
    }

    private void initBase() {
        activity = (ModifyPswPayActivity) getActivity();
    }

    private void initView() {
        showingroup = (ViewGroup) getView().findViewById(R.id.showingroup);
        btn_go = getView().findViewById(R.id.btn_go);
    }

    private void initData() {
    }

    private void initCtrl() {
        btn_go.setOnClickListener(this);
    }

    private void freshCtrl() {
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_go) {
            KeyBoardUtil.hideKeybord(getActivity());
            activity.next();

        }
    }
}