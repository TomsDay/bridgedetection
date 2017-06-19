package com.suken.bridgedetection.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

/**
 * Created by gaofeng on 16/5/8.
 */
public class CheckXMDialog implements View.OnClickListener {

    private int minYear = 1970;  //最小年份
    private int fontSize = 13;     //字体大小
    private WheelView hourWheel, minuteWheel, secondWheel, dw_wheel;

    private EditText cm_seach_ev;

    public static ArrayList<String> hourContent = new ArrayList<String>();
    public static ArrayList<String> minuteContent = new ArrayList<String>();
    public static ArrayList<String> secondContent = new ArrayList<String>();
    public static ArrayList<String> dwmcContent = new ArrayList<String>();
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

        minuteContent.clear();
        secondContent.clear();
        catalogueByUIDBeen = catalogueByUIDDao.queryAll();
        if (TextUtil.isListEmpty(catalogueByUIDBeen)) {
            ToastUtil.showMessage("暂无细目库数据");
            return;
        }
//        Log.i("aaa", "initContent: "+catalogueByUIDBeen.size());
//        for (int i = 0; i < catalogueByUIDBeen.size(); i++){
//            if (i > 0 && (catalogueByUIDBeen.get(i-1).getXmlb()).equals(catalogueByUIDBeen.get(i).getXmlb())){
//                continue;
//            } else {
//
//                hourContent.add(catalogueByUIDBeen.get(i).getXmlb());
//                Log.i("aaa", "list.get(i).getXmlb(): "+catalogueByUIDBeen.get(i).getXmlb());
//
//            }
//
//        }
//        xmlb = hourContent.get(0);
        getDataMethod(YJFL_INDEX, true);
        Logger.e("aaa", "xmlb==" + xmlb);

//        List<CatalogueByUIDBean> listMC = catalogueByUIDDao.queryByLB(hourContent.get(0));
//        for (int i = 0; i < listMC.size(); i++){
//            if (i > 0 && (listMC.get(i-1).getXmmc()).equals(listMC.get(i).getXmmc())){
//                continue;
//            } else {
//                minuteContent.add(listMC.get(i).getXmmc());
//                Log.i("aaa", "list.get(i).getXmmc(): "+listMC.get(i).getXmmc());
//
//            }
//
//        }
//        xmmc = minuteContent.get(0);
        getDataMethod(EJFL_INDEX, true);
        Logger.e("aaa", "xmmc==" + xmmc);


//        List<CatalogueByUIDBean> listXM = catalogueByUIDDao.queryByLBandxmmc(hourContent.get(0), minuteContent.get(0));
//
//        for (int i = 0; i < listXM.size(); i++){
//            if (i > 0 && (listXM.get(i-1).getXimmc()).equals(listXM.get(i).getXimmc())){
//                continue;
//            } else {
//                secondContent.add(listXM.get(i).getXimmc());
//                Log.i("aaa", "list.get(i).getXimmc(): "+listXM.get(i).getXimmc());
//
//            }
//
//        }
//        ximmc = secondContent.get(0);
        getDataMethod(XIMUMC_INDEX, true);
        Logger.e("aaa", "ximmc==" + ximmc);


        getDataMethod(DW_INDEX, true);
        Logger.e("aaa", "dwmc==" + dwmc);
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
    String ximmc, dwmc;

    //数据的本地id
    //不可能用id定位其他列的数据
    //id只能定位最终按确定选中的数据
    int dataID = 0;

    String contentET;

    public void onClick(View v) {
        //2017.06.19 回显，如果上次没有查到数据，那么这次就没有这个条件
        if(isNullList){
            isNullList = false;
            contentET = "";
        }

        initContent();
        if (TextUtil.isListEmpty(catalogueByUIDBeen)) {
            ToastUtil.showMessage("暂无细目库数据");
            return;
        }
        int id = v.getId();
        Log.i("aaa ", "onClick: " + id);
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.checkcm_dialog, null);

        hourWheel = (WheelView) view.findViewById(R.id.hourwheel);
        minuteWheel = (WheelView) view.findViewById(R.id.minutewheel);
        secondWheel = (WheelView) view.findViewById(R.id.secondwheel);
        dw_wheel = (WheelView) view.findViewById(R.id.dw_wheel);

        cm_seach_ev = (EditText) view.findViewById(R.id.cm_seach_ev);
        cm_seach_ev.setHint("请输入要查询细目名称的首字母");
        //2017.06.19 回显，把字母查询的到数据显示到输入框中
        if (!TextUtil.isEmptyString(contentET)) {
            cm_seach_ev.setText(contentET);
            cm_seach_ev.setSelection(contentET.length());
        }
        cm_seach_ev.setText(contentET);
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


        hourWheel.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text))
                    return;
                xmlb = text;
                dataID = id;
//                Log.i("aaa","text ====== "+text);
//                minuteContent.clear();
//                List<CatalogueByUIDBean> list = catalogueByUIDDao.queryByLB(text);
//                Log.i("aaa","list ====== "+list.size());
//                for (int i = 0; i < list.size(); i++){
//                    if (i > 0 && (list.get(i-1).getXmmc()).equals(list.get(i).getXmmc())){
//                        continue;
//                    } else {
//                        minuteContent.add(list.get(i).getXmmc());
//                        Log.i("aaa", "list.get(i).getXmmc(): "+list.get(i).getXmmc());
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
//                List<CatalogueByUIDBean> list2 = catalogueByUIDDao.queryByLBandxmmc(xmlb,xmmc);
//                Log.i("aaa","list2 ====== "+list2.size());
//                for (int i = 0; i < list.size(); i++){
//                    if (i > 0 && (list.get(i-1).getXimmc()).equals(list.get(i).getXimmc())){
//                        continue;
//                    } else {
//                        secondContent.add(list.get(i).getXimmc());
//                        Log.i("aaa", "list.get(i).getXimmc(): "+list.get(i).getXimmc());
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

        minuteWheel.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text))
                    return;
                xmmc = text;
                dataID = id;
//                secondContent.clear();
//                List<CatalogueByUIDBean> list = catalogueByUIDDao.queryByLBandxmmc(xmlb,text);
//                Log.i("aaa","list ====== "+list.size());
//                for (int i = 0; i < list.size(); i++){
//                    if (i > 0 && (list.get(i-1).getXimmc()).equals(list.get(i).getXimmc())){
//                        continue;
//                    } else {
//                        secondContent.add(list.get(i).getXimmc());
//                        Log.i("aaa", "list.get(i).getXimmc(): "+list.get(i).getXimmc());
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

        secondWheel.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text))
                    return;
                ximmc = text;
                dataID = id;
//                secondWheel.setData(hourContent);
                getDataMethod(DW_INDEX, false);
            }

            @Override
            public void selecting(int id, String text) {
            }
        });
        dw_wheel.setOnSelectListener(new WheelView.OnSelectListener() {
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
                            checkXMDDialogReturn.returnBean(catalogueByUIDDao.queryByXMMC(ximmc));
                        }
                        dialog.cancel();
                    }
                })

                .show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private ArrayList<String> removeDuplicate(ArrayList<String> arrayList) {
        Set set = new HashSet();
        List newList = new ArrayList();
        if (!TextUtil.isListEmpty(arrayList)) {
            for (int i = 0; i < arrayList.size(); i++) {
                String element = arrayList.get(i);
                if (!TextUtil.isEmptyString(element) && set.add(element)) {
                    newList.add(element);
                }
            }

        }
        return (ArrayList<String>) newList;
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

    //没有数据的时候退出 true （默认为false）
    boolean isNullList;

    public void getDataMethod(int type, boolean isInit) {

        List<CatalogueByUIDBean> list = new ArrayList<CatalogueByUIDBean>();
        switch (type) {
            case YJFL_INDEX:
                if (!TextUtil.isListEmpty(hourContent)) {
                    hourContent.clear();
                }
                list = catalogueByUIDDao.queryAll(contentET);
                break;
            case EJFL_INDEX:
                if (!TextUtil.isListEmpty(minuteContent)) {
                    minuteContent.clear();
                }
                list = catalogueByUIDDao.queryByLB(xmlb, contentET);
                break;
            case XIMUMC_INDEX:
                if (!TextUtil.isListEmpty(secondContent)) {
                    secondContent.clear();
                }
                list = catalogueByUIDDao.queryByLBandxmmc(xmlb, xmmc, contentET);
                break;
            case DW_INDEX:
                if (!TextUtil.isListEmpty(dwmcContent)) {
                    dwmcContent.clear();
                }
                list = catalogueByUIDDao.queryByLBandXmmcAndXimmc(xmlb, xmmc, ximmc, contentET);
                break;
            default:
                break;
        }
        if (TextUtil.isListEmpty(list)) {
            isNullList = true;
            ToastUtil.showMessage("查询信息无数据，请从新输入！");
            return;
        } else {
            if(isNullList){
                isNullList = false;
            }
            //不管动列的数据，默认的id都会是第一行，除了动最后单位一行的时候，是数据本身的id
            dataID = Integer.parseInt(list.get(0).getIds() + "");
        }

        Log.i("aaa", "list ====== " + list.size());
        for (int i = 0; i < list.size(); i++) {
            switch (type) {
                case YJFL_INDEX:
                    if (i > 0 && (list.get(i - 1).getXmlb()).equals(list.get(i).getXmlb())) {
                        continue;
                    } else {
                        hourContent.add(list.get(i).getXmlb());
                        Log.i("aaa", "list.get(i).getXmlb(): " + list.get(i).getXmlb());

                    }
                    break;
                case EJFL_INDEX:
                    if (i > 0 && (list.get(i - 1).getXmmc()).equals(list.get(i).getXmmc())) {
                        continue;
                    } else {
                        minuteContent.add(list.get(i).getXmmc());
                        Log.i("aaa", "list.get(i).getXmmc(): " + list.get(i).getXmmc());

                    }
                    break;
                case XIMUMC_INDEX:
                    if (i > 0 && (list.get(i - 1).getXimmc()).equals(list.get(i).getXimmc())) {
                        continue;
                    } else {
                        secondContent.add(list.get(i).getXimmc());
                        Log.i("aaa", "list.get(i).getXimmc(): " + list.get(i).getXimmc());

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

    public interface CheckXMDDialogReturn {
        public void returnBean(List<CatalogueByUIDBean> bean);
    }
}
