package com.sobey.common.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoadingViewUtil {

    public static View showin(ViewGroup root, int src, View preview, View.OnClickListener listener) {
        View showin = showin(root,src,preview,true);
        showin.setOnClickListener(listener);
        return showin;
    }

    public static View showin(ViewGroup root, int src, View preview, boolean needHide, View.OnClickListener listener) {
        View showin = showin(root,src,preview,needHide);
        showin.setOnClickListener(listener);
        return showin;
    }

    public static View showin(ViewGroup root, int src, View preview) {
        return showin(root,src,preview,true);
    }

    /**
     * 废弃的方法
     */
//    public static View showin(ViewGroup root, int src) {
//        return showin(root,src,true);
//    }

    /**
     * showin 是否隐藏背景
     */
    public static View showin(ViewGroup root, int src, View preview, boolean needHide) {

        if (preview!=null) {
            root.removeView(preview);
        }
        if (root == null) {
            return null;
        }

        //设置lack
        View loadingView = LayoutInflater.from(root.getContext()).inflate(src, root, false);

        //隐藏其余项目
        if (needHide) {
            int count = root.getChildCount();
            for (int i = 0; i < count; i++) {
                root.getChildAt(i).setVisibility(View.GONE);
            }
        }
        //添加lack
        root.addView(loadingView);

        return loadingView;
    }

    /**
     * out
     */
    public static void showout(ViewGroup root, View viewin) {
        if (root == null || viewin == null) {
            return;
        }

        //删除中心视图
        root.removeView(viewin);
        //显示其余项目
        int count = root.getChildCount();
        for (int i = 0; i < count; i++) {
            if (root.getChildAt(i).getVisibility() != View.VISIBLE)
                root.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
}
