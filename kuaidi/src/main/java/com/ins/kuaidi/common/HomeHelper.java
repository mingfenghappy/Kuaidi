package com.ins.kuaidi.common;

import android.view.View;

import com.ins.kuaidi.ui.activity.HomeActivity;
import com.ins.middle.entity.Trip;

/**
 * Created by Administrator on 2016/11/10.
 */

public class HomeHelper {
    public static void setTrip(HomeActivity activity, Trip trip) {
        if (trip == null) {
            setInit(activity);
            return;
        }
        switch (trip.getStatus()) {
            case 2001:
                setMatching(activity);
                break;
            case 2002:
                //司机发送支付定金状态
                setMatched(activity);
                break;
            case 2003:
                //司机等待乘客支付定金状态
                setPayFirst(activity);
                break;
            case 2004:
                //2004 乘客已支付预付款
                setPayLastFalse(activity);
                break;
            case 2005:
                //2005 司机接到乘客
                if (trip.getIsPay() == 1) {
                    setHasPayLast(activity);
                } else {
                    setPayLast(activity);
                }
                break;
            case 2006:
                //2006 乘客已抵达
                if (trip.getIsPay() == 1) {
//                    setHasPayLast(activity);
                    setFresh(activity);
                } else {
                    setPayLast(activity);
                }
                break;
            case 2007:
                setInit(activity);
                //订单已取消
                break;
        }
    }

    //2001
    public static void setMatching(HomeActivity activity) {
        activity.img_home_cancel.setVisibility(View.VISIBLE);
        activity.driverView.setVisibility(View.GONE);
        activity.lay_map_center.setVisibility(View.GONE);
        activity.holdcarView.setVisibility(View.GONE);
        activity.btn_go.setVisibility(View.VISIBLE);
        activity.btn_go.setEnabled(false);
        activity.btn_go.setText("正在为您安排车辆...");
    }

    //2002
    //司机发送支付定金状态
    public static void setMatched(HomeActivity activity) {
        activity.img_home_cancel.setVisibility(View.GONE);
        activity.driverView.setVisibility(View.VISIBLE);
        activity.holdcarView.setVisibility(View.GONE);
        activity.lay_map_center.setVisibility(View.GONE);
        activity.btn_go.setVisibility(View.VISIBLE);
        activity.btn_go.setEnabled(false);
        activity.btn_go.setText("支付定金");
    }

    //2003
    //司机等待乘客支付定金状态
    public static void setPayFirst(HomeActivity activity) {
        activity.driverView.setVisibility(View.VISIBLE);
        activity.lay_map_center.setVisibility(View.GONE);
        activity.holdcarView.setVisibility(View.GONE);
        activity.btn_go.setVisibility(View.VISIBLE);
        activity.btn_go.setEnabled(true);
        activity.btn_go.setText("支付定金");
    }

    //2004 乘客已支付预付款
    //2006 乘客已抵达
    public static void setPayLast(HomeActivity activity) {
        activity.driverView.setVisibility(View.VISIBLE);
        activity.lay_map_center.setVisibility(View.GONE);
        activity.holdcarView.setVisibility(View.GONE);
        activity.btn_go.setVisibility(View.VISIBLE);
        activity.btn_go.setEnabled(true);
        activity.btn_go.setText("支付尾款");
    }

    public static void setPayLastFalse(HomeActivity activity) {
        setPayLast(activity);
        activity.btn_go.setEnabled(false);
    }

    //乘客已经支付尾款（特殊状态）
    public static void setHasPayLast(HomeActivity activity) {
        setPayLast(activity);
        activity.btn_go.setVisibility(View.GONE);
    }

    //2005 司机接到乘客
    public static void setGetPassenger(HomeActivity activity) {
        activity.driverView.setVisibility(View.VISIBLE);
        activity.lay_map_center.setVisibility(View.GONE);
        activity.holdcarView.setVisibility(View.GONE);
    }

    //2007
    //初始状态
    public static void setInit(HomeActivity activity) {
        activity.driverView.setVisibility(View.GONE);
        activity.holdcarView.setVisibility(View.GONE);
        activity.lay_map_center.setVisibility(View.VISIBLE);
        activity.btn_go.setVisibility(View.GONE);
        activity.btn_go.setEnabled(true);
        activity.btn_go.setText("呼叫快车");

        activity.holdcarView.clear();
    }

    public static void setFresh(HomeActivity activity) {
        setFresh(activity, 0);
    }

    public static void setFresh(HomeActivity activity, int afterId) {
        activity.setUserData();
        activity.baiduMap.clear();
        activity.ptsArray.clear();
        activity.netHelper.netGetTrip(afterId);
        activity.locationer.isFirstLoc = true;
        activity.carMap.removeFromMap();
    }
}
