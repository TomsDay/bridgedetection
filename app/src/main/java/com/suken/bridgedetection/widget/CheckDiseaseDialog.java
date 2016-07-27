package com.suken.bridgedetection.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.suken.bridgedetection.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.jp.wheelview.WheelView;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseDao;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

/**
 * Created by gaofeng on 16/5/8.
 */
public class CheckDiseaseDialog implements View.OnClickListener {

    private int minYear = 1970;  //最小年份
    private int fontSize = 13;     //字体大小
    private WheelView hourWheel, minuteWheel, secondWheel;
    public static ArrayList<String> hourContent = new ArrayList<String>();
    public static ArrayList<String> minuteContent = new ArrayList<String>();
    public static ArrayList<String> secondContent = new ArrayList<String>();
    private Context mContext;
    //    private EditText mEditText;
    MaintenanceDiseaseDao maintenanceDiseaseDao;
    private CheckDiseaseDialogReturn checkDiseaseDialogReturn;

    ArrayList<String> proKinds = new ArrayList<String>();
    List<MaintenanceDiseaseBean> maintenanceDiseaseBeen;

    public CheckDiseaseDialog(Context context, CheckDiseaseDialogReturn checkXMDDialogReturn) {
        this.mContext = context;
        this.checkDiseaseDialogReturn = checkXMDDialogReturn;
        maintenanceDiseaseDao = new MaintenanceDiseaseDao();
        initContent();
    }

    public void initContent() {

        maintenanceDiseaseBeen = maintenanceDiseaseDao.queryAll();
        if(TextUtil.isListEmpty(maintenanceDiseaseBeen)){
            ToastUtil.showMessage("暂无病害库数据");
            return;
        }
        Log.i("aaa", "initContent: "+maintenanceDiseaseBeen.size());
        for (int i = 0; i < maintenanceDiseaseBeen.size(); i++){
            if (i > 0 && (maintenanceDiseaseBeen.get(i-1).getYjml()).equals(maintenanceDiseaseBeen.get(i).getYjml())){
                continue;
            } else {

                hourContent.add(maintenanceDiseaseBeen.get(i).getYjml());
                Log.i("aaa", "list.get(i).getYjml(): "+maintenanceDiseaseBeen.get(i).getYjml());

            }

        }
        xmlb = hourContent.get(0);


        List<MaintenanceDiseaseBean> listMC = maintenanceDiseaseDao.queryByYJML(hourContent.get(0));
        for (int i = 0; i < listMC.size(); i++){
            if (i > 0 && (listMC.get(i-1).getEjml()).equals(listMC.get(i).getEjml())){
                continue;
            } else {
                minuteContent.add(listMC.get(i).getEjml());
                Log.i("aaa", "list.get(i).getEjml(): "+listMC.get(i).getEjml());

            }

        }
        xmmc = minuteContent.get(0);

        List<MaintenanceDiseaseBean> listXM = maintenanceDiseaseDao.queryByYjmlandEjml(hourContent.get(0), minuteContent.get(0));

        for (int i = 0; i < listXM.size(); i++){
            if (i > 0 && (listXM.get(i-1).getBhmc()).equals(listXM.get(i).getBhmc())){
                continue;
            } else {
                secondContent.add(listXM.get(i).getBhmc());
                Log.i("aaa", "list.get(i).getBhmc(): "+listXM.get(i).getBhmc());

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
        if(TextUtil.isListEmpty(maintenanceDiseaseBeen)){
            ToastUtil.showMessage("暂无病害库数据");
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
                List<MaintenanceDiseaseBean> list = maintenanceDiseaseDao.queryByYJML(text);
                Log.i("aaa","list ====== "+list.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getEjml()).equals(list.get(i).getEjml())){
                        continue;
                    } else {
                        minuteContent.add(list.get(i).getEjml());
                        Log.i("aaa", "list.get(i).getEjml(): "+list.get(i).getEjml());

                    }

                }
                minuteContent = removeDuplicate(minuteContent);

                minuteWheel.setData(minuteContent);
                minuteWheel.setDefault(0);

                xmmc = minuteContent.get(0);
                secondContent.clear();
                List<MaintenanceDiseaseBean> list2 = maintenanceDiseaseDao.queryByYjmlandEjml(xmlb,xmmc);
                Log.i("aaa","list2 ====== "+list2.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getBhmc()).equals(list.get(i).getBhmc())){
                        continue;
                    } else {
                        secondContent.add(list.get(i).getBhmc());
                        Log.i("aaa", "list.get(i).getBhmc(): "+list.get(i).getBhmc());

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
                List<MaintenanceDiseaseBean> list = maintenanceDiseaseDao.queryByYjmlandEjml(xmlb,text);
                Log.i("aaa","list ====== "+list.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getBhmc()).equals(list.get(i).getBhmc())){
                        continue;
                    } else {
                        secondContent.add(list.get(i).getBhmc());
                        Log.i("aaa", "list.get(i).getBhmc(): "+list.get(i).getBhmc());

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
                    checkDiseaseDialogReturn.returnBean(maintenanceDiseaseDao.queryByBhmc(ximmc));
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
    public interface CheckDiseaseDialogReturn{
        public void returnBean(List<MaintenanceDiseaseBean> bean);
    }
}
