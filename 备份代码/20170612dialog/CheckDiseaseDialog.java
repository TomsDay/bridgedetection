package com.suken.bridgedetection.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.suken.bridgedetection.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.jp.wheelview.WheelView;
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseDao;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

/**
 * Created by gaofeng on 16/5/8.
 */
public class CheckDiseaseDialog implements View.OnClickListener {

    private int minYear = 1970;  //最小年份
    private int fontSize = 13;     //字体大小
    private WheelView hourWheel, minuteWheel, secondWheel,dw_wheel;

    private EditText cm_seach_ev;

    public static ArrayList<String> hourContent = new ArrayList<String>();
    public static ArrayList<String> minuteContent = new ArrayList<String>();
    public static ArrayList<String> secondContent = new ArrayList<String>();
    public static ArrayList<String> dwmcContent = new ArrayList<String>();
    private Context mContext;
    //    private EditText mEditText;
    MaintenanceDiseaseDao maintenanceDiseaseDao;
    private CheckDiseaseDialogReturn checkDiseaseDialogReturn;


    List<MaintenanceDiseaseBean> maintenanceDiseaseBeen;

    public CheckDiseaseDialog(Context context, CheckDiseaseDialogReturn checkXMDDialogReturn) {
        this.mContext = context;
        this.checkDiseaseDialogReturn = checkXMDDialogReturn;
        maintenanceDiseaseDao = new MaintenanceDiseaseDao();
        //在onclick里面调用
//        initContent();
    }

    public void initContent() {

        maintenanceDiseaseBeen = maintenanceDiseaseDao.queryAll();
        if(TextUtil.isListEmpty(maintenanceDiseaseBeen)){
            ToastUtil.showMessage("暂无病害库数据");
            return;
        }
//        Log.i("aaa", "initContent: "+maintenanceDiseaseBeen.size());
//        for (int i = 0; i < maintenanceDiseaseBeen.size(); i++){
//            if (i > 0 && (maintenanceDiseaseBeen.get(i-1).getYjml()).equals(maintenanceDiseaseBeen.get(i).getYjml())){
//                continue;
//            } else {
//
//                hourContent.add(maintenanceDiseaseBeen.get(i).getYjml());
//                Log.i("aaa", "list.get(i).getYjml(): "+maintenanceDiseaseBeen.get(i).getYjml());
//
//            }
//
//        }
//        xmlb = hourContent.get(0);
//        //需要清空list
//        minuteContent.clear();

        getDataMethod(YJFL_INDEX, true);
        Logger.e("aaa", "xmlb==" + xmlb);
//        secondContent.clear();
//
//        List<MaintenanceDiseaseBean> listMC = maintenanceDiseaseDao.queryByYJML(hourContent.get(0));
//        for (int i = 0; i < listMC.size(); i++){
//            if (i > 0 && (listMC.get(i-1).getEjml()).equals(listMC.get(i).getEjml())){
//                continue;
//            } else {
//                minuteContent.add(listMC.get(i).getEjml());
//                Log.i("aaa", "list.get(i).getEjml(): "+listMC.get(i).getEjml());
//
//            }
//
//        }
//        xmmc = minuteContent.get(0);
        getDataMethod(EJFL_INDEX, true);
        Logger.e("aaa", "xmmc==" + xmmc);

//        List<MaintenanceDiseaseBean> listXM = maintenanceDiseaseDao.queryByYjmlandEjml(hourContent.get(0), minuteContent.get(0));
//
//        for (int i = 0; i < listXM.size(); i++){
//            if (i > 0 && (listXM.get(i-1).getBhmc()).equals(listXM.get(i).getBhmc())){
//                continue;
//            } else {
//                secondContent.add(listXM.get(i).getBhmc());
//                Log.i("aaa", "list.get(i).getBhmc(): "+listXM.get(i).getBhmc());
//
//            }
//
//        }
//        ximmc = secondContent.get(0);
        getDataMethod(XIMUMC_INDEX, true);
        Logger.e("aaa", "ximmc==" + ximmc);


        getDataMethod(DW_INDEX, true);
        Logger.e("aaa", "dwmc==" + dwmc);


    }


    String xmlb, xmmc;
    String ximmc,dwmc;

    //数据的本地id
    //不可能用id定位其他列的数据
    //id只能定位最终按确定选中的数据
    int dataID = 0;

    String contentET;

    public void onClick(View v) {

        //初始化piker
        initContent();
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
        dw_wheel = (WheelView) view.findViewById(R.id.dw_wheel);

        cm_seach_ev = (EditText) view.findViewById(R.id.cm_seach_ev);
        cm_seach_ev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contentET = s.toString();
                getDataMethod(YJFL_INDEX, false);
                getDataMethod(EJFL_INDEX, false);
                getDataMethod(XIMUMC_INDEX, false);
                getDataMethod(DW_INDEX, false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        hourWheel.setOnSelectListener(new WheelView.OnSelectListener(){
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text))
                    return;
                xmlb = text;
                dataID = id;
//                Log.i("aaa","text ====== "+text);
//                minuteContent.clear();
//                List<MaintenanceDiseaseBean> list = maintenanceDiseaseDao.queryByYJML(text);
//                Log.i("aaa","list ====== "+list.size());
//                for (int i = 0; i < list.size(); i++){
//                    if (i > 0 && (list.get(i-1).getEjml()).equals(list.get(i).getEjml())){
//                        continue;
//                    } else {
//                        minuteContent.add(list.get(i).getEjml());
//                        Log.i("aaa", "list.get(i).getEjml(): "+list.get(i).getEjml());
//
//                    }
//
//                }
//                minuteContent = removeDuplicate(minuteContent);
//
//                minuteWheel.setData(minuteContent);
//                minuteWheel.setDefault(0);
//
//                xmmc = minuteContent.get(0);
                getDataMethod(EJFL_INDEX, false);


//                secondContent.clear();
//                List<MaintenanceDiseaseBean> list2 = maintenanceDiseaseDao.queryByYjmlandEjml(xmlb,xmmc);
//                Log.i("aaa","list2 ====== "+list2.size());
//                for (int i = 0; i < list.size(); i++){
//                    if (i > 0 && (list.get(i-1).getBhmc()).equals(list.get(i).getBhmc())){
//                        continue;
//                    } else {
//                        secondContent.add(list.get(i).getBhmc());
//                        Log.i("aaa", "list.get(i).getBhmc(): "+list.get(i).getBhmc());
//
//                    }
//
//                }
//                secondContent = removeDuplicate(secondContent);
//
//                secondWheel.setData(secondContent);
//                secondWheel.setDefault(0);
//                ximmc = secondContent.get(0);
                getDataMethod(XIMUMC_INDEX, false);

                getDataMethod(DW_INDEX, false);
            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        minuteWheel.setOnSelectListener(new WheelView.OnSelectListener(){
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text))
                    return;
                xmmc = text;
                dataID = id;
//                secondContent.clear();
//                List<MaintenanceDiseaseBean> list = maintenanceDiseaseDao.queryByYjmlandEjml(xmlb,text);
//                Log.i("aaa","list ====== "+list.size());
//                for (int i = 0; i < list.size(); i++){
//                    if (i > 0 && (list.get(i-1).getBhmc()).equals(list.get(i).getBhmc())){
//                        continue;
//                    } else {
//                        secondContent.add(list.get(i).getBhmc());
//                        Log.i("aaa", "list.get(i).getBhmc(): "+list.get(i).getBhmc());
//
//                    }
//
//                }
//                secondContent = removeDuplicate(secondContent);
//
//                secondWheel.setData(secondContent);
//                secondWheel.setDefault(0);
//                ximmc = secondContent.get(0);
//                minuteWheel.setData(hourContent);

                getDataMethod(XIMUMC_INDEX, false);

                getDataMethod(DW_INDEX, false);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        secondWheel.setOnSelectListener(new WheelView.OnSelectListener(){
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text))
                    return;
                ximmc = text;
//                secondWheel.setData(hourContent);
                getDataMethod(DW_INDEX, false);
            }

            @Override
            public void selecting(int id, String text) {
            }
        });
        dw_wheel.setOnSelectListener(new WheelView.OnSelectListener(){
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text))
                    return;
                ximmc = text;
                dataID = id;
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


        //2017.6.4新增单位选项
        dw_wheel.setData(removeDuplicate(dwmcContent));
        dw_wheel.setDefault(0);

        xmlb = hourContent.get(0);
        xmmc = minuteContent.get(0);
        ximmc = secondContent.get(0);
        //2017.6.4新增单位选项
        dwmc = dwmcContent.get(0);
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
        if(!TextUtil.isListEmpty(arrayList)){
            for(int i= 0;i<arrayList.size();i++){
                String element = arrayList.get(i);
                if (!TextUtil.isEmptyString(element) && set.add(element)) {
                    newList.add(element);
                }
            }

        }
        return (ArrayList<String>) newList;
    }
    public interface CheckDiseaseDialogReturn{
        public void returnBean(List<MaintenanceDiseaseBean> bean);
    }


    /**
     * @param type
     * 0是默认的直接返回
     * 1一级类别（项目类别）
     * 2二级分类（项目名称）
     * 3细木名称（材料名称）
     * 4单位
     * @return
     */
    private final int YJFL_INDEX = 1;
    private final int EJFL_INDEX = 2;
    private final int XIMUMC_INDEX = 3;
    private final int DW_INDEX = 4;

    public void getDataMethod(int type, boolean isInit) {

        List<MaintenanceDiseaseBean> list = new ArrayList<MaintenanceDiseaseBean>();
        switch (type) {
            case YJFL_INDEX:
                if (!TextUtil.isListEmpty(hourContent)) {
                    hourContent.clear();
                }
                list = maintenanceDiseaseDao.queryAll(contentET);
                break;
            case EJFL_INDEX:
                if (!TextUtil.isListEmpty(minuteContent)) {
                    minuteContent.clear();
                }
                list = maintenanceDiseaseDao.queryByYJML(xmlb,contentET);
                break;
            case XIMUMC_INDEX:
                if (!TextUtil.isListEmpty(secondContent)) {
                    secondContent.clear();
                }
                list = maintenanceDiseaseDao.queryByYjmlandEjml(xmlb, xmmc,contentET);
                break;
            case DW_INDEX:
                if (!TextUtil.isListEmpty(dwmcContent)) {
                    dwmcContent.clear();
                }
                list = maintenanceDiseaseDao.queryByYjmlandEjmlAndBhmc(xmlb, xmmc, ximmc,contentET);
                break;
            default:
                break;
        }
        //不管动列的数据，默认的id都会是第一行，除了动最后单位一行的时候，是数据本身的id
        if (!TextUtil.isListEmpty(list)) {
            dataID = Integer.parseInt(list.get(0).getIds()+"");
        }


        Log.i("aaa", "list ====== " + list.size());
        for (int i = 0; i < list.size(); i++) {
            switch (type) {
                case YJFL_INDEX:
                    if (i > 0 && (list.get(i - 1).getYjml()).equals(list.get(i).getYjml())) {
                        continue;
                    } else {
                        hourContent.add(list.get(i).getYjml());
                        Log.i("aaa", "list.get(i).getYjml(): " + list.get(i).getYjml());

                    }
                    break;
                case EJFL_INDEX:
                    if (i > 0 && (list.get(i - 1).getEjml()).equals(list.get(i).getEjml())) {
                        continue;
                    } else {
                        minuteContent.add(list.get(i).getEjml());
                        Log.i("aaa", "list.get(i).getEjml(): " + list.get(i).getEjml());

                    }
                    break;
                case XIMUMC_INDEX:
                    if (i > 0 && (list.get(i - 1).getBhmc()).equals(list.get(i).getBhmc())) {
                        continue;
                    } else {
                        secondContent.add(list.get(i).getBhmc());
                        Log.i("aaa", "list.get(i).getBhmc(): " + list.get(i).getBhmc());

                    }
                    break;
                case DW_INDEX:
                    if (i > 0 && (list.get(i - 1).getDw()).equals(list.get(i).getDw())) {
                        continue;
                    } else {
                        dwmcContent.add(list.get(i).getDw());
                        Log.i("aaa", "list.get(i).getDw(): " + list.get(i).getDw());

                    }
                    break;
                default:
                    break;
            }

        }
        switch (type) {
            case YJFL_INDEX:
                if (!isInit) {
                    hourContent = removeDuplicate(hourContent);
                    hourWheel.setData(hourContent);
                    hourWheel.setDefault(0);

                }


                xmlb = hourContent.get(0);

                break;
            case EJFL_INDEX:
                if (!isInit) {
                    minuteContent = removeDuplicate(minuteContent);
                    minuteWheel.setData(minuteContent);
                    minuteWheel.setDefault(0);

                }
                xmmc = minuteContent.get(0);
                break;
            case XIMUMC_INDEX:
                if (!isInit) {
                    secondContent = removeDuplicate(secondContent);
                    secondWheel.setData(secondContent);
                    secondWheel.setDefault(0);

                }
                ximmc = secondContent.get(0);
                break;
            case DW_INDEX:
                if (!isInit) {
                    dwmcContent = removeDuplicate(dwmcContent);
                    dw_wheel.setData(dwmcContent);
                    dw_wheel.setDefault(0);
                }
                dwmc = dwmcContent.get(0);
                break;
            default:
                break;
        }

    }

}
