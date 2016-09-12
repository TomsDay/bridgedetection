package com.suken.bridgedetection.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.EditText;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.CatalogueByUIDBean;
import com.suken.bridgedetection.bean.CatalogueByUIDDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.jp.wheelview.WheelView;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

/**
 * Created by gaofeng on 16/5/8.
 */
public class    CheckXMDialog implements View.OnClickListener {

    private int minYear = 1970;  //最小年份
    private int fontSize = 13;     //字体大小
    private WheelView hourWheel, minuteWheel, secondWheel;
    public static ArrayList<String> hourContent = new ArrayList<String>();
    public static ArrayList<String> minuteContent = new ArrayList<String>();
    public static ArrayList<String> secondContent = new ArrayList<String>();
    private Context mContext;
    //    private EditText mEditText;
    CatalogueByUIDDao catalogueByUIDDao;
    private CheckXMDDialogReturn checkXMDDialogReturn;

    ArrayList<String> proKinds = new ArrayList<String>();

    List<CatalogueByUIDBean> catalogueByUIDBeen;

    public CheckXMDialog(Context context, CheckXMDDialogReturn checkXMDDialogReturn) {
        this.mContext = context;
        this.checkXMDDialogReturn = checkXMDDialogReturn;
        catalogueByUIDDao = new CatalogueByUIDDao();
        initContent();
    }

    public void initContent() {

        catalogueByUIDBeen = catalogueByUIDDao.queryAll();
        if(TextUtil.isListEmpty(catalogueByUIDBeen)){
            ToastUtil.showMessage("暂无细目库数据");
            return;
        }
        Log.i("aaa", "initContent: "+catalogueByUIDBeen.size());
        for (int i = 0; i < catalogueByUIDBeen.size(); i++){
            if (i > 0 && (catalogueByUIDBeen.get(i-1).getXmlb()).equals(catalogueByUIDBeen.get(i).getXmlb())){
                continue;
            } else {

                hourContent.add(catalogueByUIDBeen.get(i).getXmlb());
                Log.i("aaa", "list.get(i).getXmlb(): "+catalogueByUIDBeen.get(i).getXmlb());

            }

        }
        xmlb = hourContent.get(0);


        List<CatalogueByUIDBean> listMC = catalogueByUIDDao.queryByLB(hourContent.get(0));
        for (int i = 0; i < listMC.size(); i++){
            if (i > 0 && (listMC.get(i-1).getXmmc()).equals(listMC.get(i).getXmmc())){
                continue;
            } else {
                minuteContent.add(listMC.get(i).getXmmc());
                Log.i("aaa", "list.get(i).getXmmc(): "+listMC.get(i).getXmmc());

            }

        }
        xmmc = minuteContent.get(0);

        List<CatalogueByUIDBean> listXM = catalogueByUIDDao.queryByLBandxmmc(hourContent.get(0), minuteContent.get(0));

        for (int i = 0; i < listXM.size(); i++){
            if (i > 0 && (listXM.get(i-1).getXimmc()).equals(listXM.get(i).getXimmc())){
                continue;
            } else {
                secondContent.add(listXM.get(i).getXimmc());
                Log.i("aaa", "list.get(i).getXimmc(): "+listXM.get(i).getXimmc());

            }

        }
        ximmc = secondContent.get(0);


//        String [] array = (String[]) list.toArray();
//        for (int i = 0; i < 24; i++) {
//            hourContent[i] = String.valueOf(i);
//            if (hourContent[i].length() < 2) {
//                hourContent[i] = "0" + hourContent[i];
//            }
//        }
//
//        for (int i = 0; i < 60; i++) {
//            minuteContent[i] = String.valueOf(i);
//            if (minuteContent[i].length() < 2) {
//                minuteContent[i] = "0" + minuteContent[i];
//            }
//        }
//        for (int i = 0; i < 60; i++) {
//            secondContent[i] = String.valueOf(i);
//            if (secondContent[i].length() < 2) {
//                secondContent[i] = "0" + secondContent[i];
//            }
//        }
    }


    String xmlb, xmmc;
    String ximmc;
    public void onClick(View v) {
        if(TextUtil.isListEmpty(catalogueByUIDBeen)){
            ToastUtil.showMessage("暂无细目库数据");
            return;
        }
        int id = v.getId();
        Log.i("aaa ", "onClick: "+id);
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.checkcm_dialog, null);

        hourWheel = (WheelView) view.findViewById(R.id.hourwheel);
        minuteWheel = (WheelView) view.findViewById(R.id.minutewheel);
        secondWheel = (WheelView) view.findViewById(R.id.secondwheel);


        hourWheel.setOnSelectListener(new WheelView.OnSelectListener(){
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                xmlb = text;
                Log.i("aaa","text ====== "+text);
                minuteContent.clear();
                List<CatalogueByUIDBean> list = catalogueByUIDDao.queryByLB(text);
                Log.i("aaa","list ====== "+list.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getXmmc()).equals(list.get(i).getXmmc())){
                        continue;
                    } else {
                        minuteContent.add(list.get(i).getXmmc());
                        Log.i("aaa", "list.get(i).getXmmc(): "+list.get(i).getXmmc());

                    }

                }
                minuteContent = removeDuplicate(minuteContent);

                minuteWheel.setData(minuteContent);
                minuteWheel.setDefault(0);

                xmmc = minuteContent.get(0);
                secondContent.clear();
                List<CatalogueByUIDBean> list2 = catalogueByUIDDao.queryByLBandxmmc(xmlb,xmmc);
                Log.i("aaa","list2 ====== "+list2.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getXimmc()).equals(list.get(i).getXimmc())){
                        continue;
                    } else {
                        secondContent.add(list.get(i).getXimmc());
                        Log.i("aaa", "list.get(i).getXimmc(): "+list.get(i).getXimmc());

                    }

                }
                secondContent = removeDuplicate(secondContent);

                secondWheel.setData(secondContent);
                secondWheel.setDefault(0);
                ximmc = secondContent.get(0);
            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        minuteWheel.setOnSelectListener(new WheelView.OnSelectListener(){
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                xmmc = text;
                secondContent.clear();
                List<CatalogueByUIDBean> list = catalogueByUIDDao.queryByLBandxmmc(xmlb,text);
                Log.i("aaa","list ====== "+list.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getXimmc()).equals(list.get(i).getXimmc())){
                        continue;
                    } else {
                        secondContent.add(list.get(i).getXimmc());
                        Log.i("aaa", "list.get(i).getXimmc(): "+list.get(i).getXimmc());

                    }

                }
                secondContent = removeDuplicate(secondContent);

                secondWheel.setData(secondContent);
                secondWheel.setDefault(0);
                ximmc = secondContent.get(0);
//                minuteWheel.setData(hourContent);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        secondWheel.setOnSelectListener(new WheelView.OnSelectListener(){
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                ximmc = text;
//                secondWheel.setData(hourContent);
            }

            @Override
            public void selecting(int id, String text) {
            }
        });








        hourWheel.setData(removeDuplicate(hourContent));
        hourWheel.setDefault(0);
        minuteWheel.setData(removeDuplicate(minuteContent));
        minuteWheel.setDefault(0);
        secondWheel.setData(removeDuplicate(secondContent));
        secondWheel.setDefault(0);
        xmlb = hourContent.get(0);
        xmmc = minuteContent.get(0);
//        hourWheel.setAdapter(new XMWheelAdapter(hourContent));
//        hourWheel.setCurrentItem(0);
//        hourWheel.setCyclic(true);
//        hourWheel.setInterpolator(new AnticipateOvershootInterpolator());
//        hourWheel.setData(hourContent);
//        minuteContent.setData(hourContent);
//        hourWheel.setData(hourContent);
//
//        minuteWheel.setAdapter(new XMWheelAdapter(minuteContent));
//        minuteWheel.setCurrentItem(0);
//        minuteWheel.setCyclic(true);
//        minuteWheel.setInterpolator(new AnticipateOvershootInterpolator());
//
//        secondWheel.setAdapter(new XMWheelAdapter(secondContent));
//        secondWheel.setCurrentItem(0);
//        secondWheel.setCyclic(true);
//        secondWheel.setInterpolator(new AnticipateOvershootInterpolator());
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (ximmc != null) {
                            checkXMDDialogReturn.returnBean(catalogueByUIDDao.queryByXMMC(ximmc));
                        }
                        dialog.cancel();
                    }
                })

        .show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private ArrayList<String> removeDuplicate(ArrayList<String> arrayList){
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = arrayList.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        arrayList.clear();
        arrayList.addAll(newList);
        return arrayList;
    }
    public interface CheckXMDDialogReturn{
        public void returnBean(List<CatalogueByUIDBean> bean);
    }
}
