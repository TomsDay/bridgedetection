package com.suken.bridgedetection.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.util.Logger;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/7/8.
 */
public class CkeckXMDialog extends Dialog {
    private Activity mContext;
    private NewWheelView checkXM_WheelView1,checkXM_WheelView2, checkXM_WheelView3;
    private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};
    private LinearLayout linearlayout;


    public CkeckXMDialog(Activity context) {
        super(context, R.style.dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkcm_dialog);

//        checkXM_WheelView1 = (NewWheelView) findViewById(R.id.checkXM_WheelView1);
//        checkXM_WheelView2 = (NewWheelView) findViewById(R.id.checkXM_WheelView2);
//        checkXM_WheelView3 = (WheelView) findViewById(R.id.checkXM_WheelView3);
        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);

        checkXM_WheelView1 = new NewWheelView(mContext);
        checkXM_WheelView2 = new NewWheelView(mContext);

        checkXM_WheelView1.setActivity(mContext);
        checkXM_WheelView1.setOffset(1);
        checkXM_WheelView1.setItems(Arrays.asList(PLANETS));


        checkXM_WheelView2.setActivity(mContext);
        checkXM_WheelView2.setOffset(1);
        checkXM_WheelView2.setItems(Arrays.asList(PLANETS));
        linearlayout.addView(checkXM_WheelView1);
        linearlayout.addView(checkXM_WheelView2);
//        checkXM_WheelView1.setAdapter(new StrericWheelAdapter(PLANETS));
//        checkXM_WheelView1.setCurrentItem(1);
//        checkXM_WheelView1.setCyclic(true);
//        checkXM_WheelView1.setInterpolator(new AnticipateOvershootInterpolator());

//        checkXM_WheelView2.setAdapter(new StrericWheelAdapter(PLANETS));
//        checkXM_WheelView2.setCurrentItem(2);
//        checkXM_WheelView2.setCyclic(true);
//        checkXM_WheelView2.setInterpolator(new AnticipateOvershootInterpolator());
//
//        checkXM_WheelView3.setAdapter(new StrericWheelAdapter(PLANETS));
//        checkXM_WheelView3.setCurrentItem(3);
//        checkXM_WheelView3.setCyclic(false);
//        checkXM_WheelView3.setInterpolator(new AnticipateOvershootInterpolator());


    }
}
