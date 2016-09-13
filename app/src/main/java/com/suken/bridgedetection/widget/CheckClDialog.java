package com.suken.bridgedetection.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jp.wheelview.*;
import com.jp.wheelview.WheelView;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.CatalogueByUIDBean;
import com.suken.bridgedetection.bean.CatalogueByUIDDao;
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.bean.GeteMaterialDao;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wudi on 16/8/7.
 */
public class CheckClDialog implements View.OnClickListener {

    private int minYear = 1970;  //最小年份
    private int fontSize = 13;     //字体大小
    private WheelView hourWheel, minuteWheel, secondWheel;
    public static ArrayList<String> hourContent = new ArrayList<String>();
    public static ArrayList<String> minuteContent = new ArrayList<String>();
    public static ArrayList<String> secondContent = new ArrayList<String>();
    private Context mContext;
    //    private EditText mEditText;
    GeteMaterialDao geteMaterialDao;
    private CheckClDialogReturn checkClDialogReturn;

    List<GeteMaterialBean> geteMaterialBean;

    public CheckClDialog(Context context, CheckClDialogReturn checkClDialogReturn) {
        this.mContext = context;
        this.checkClDialogReturn = checkClDialogReturn;
        geteMaterialDao = new GeteMaterialDao();
//        initContent();
    }

    public void initContent() {

        minuteContent.clear();
        secondContent.clear();
        geteMaterialBean = geteMaterialDao.queryAll();
        if (TextUtil.isListEmpty(geteMaterialBean)) {
            ToastUtil.showMessage("暂无材料数据");
            return;
        }
        Log.i("aaa", "initContent: "+geteMaterialBean.size());
        for (int i = 0; i < geteMaterialBean.size(); i++){
//            if(TextUtil.isEmptyString(geteMaterialBean.get(i).getYjml())){
//                ToastUtil.showMessage("细目数据一级分类有空！");
//                return;
//            }
            if (i > 0 && (geteMaterialBean.get(i-1).getYjml()).equals(geteMaterialBean.get(i).getYjml())){
                continue;
            } else {

                hourContent.add(geteMaterialBean.get(i).getYjml());
                Log.i("aaa", "list.get(i).getYjml(): "+geteMaterialBean.get(i).getYjml());

            }

        }
        xmlb = hourContent.get(0);
        Logger.e("aaa", "yjml==" + xmlb);

        List<GeteMaterialBean> listMC = geteMaterialDao.queryByYJML(xmlb);
        for (int i = 0; i < listMC.size(); i++){
//            if(TextUtil.isEmptyString(geteMaterialBean.get(i).getEjml())){
//                ToastUtil.showMessage("细目数据二级分类有空！");
//                return;
//            }
            if (i > 0 && (listMC.get(i-1).getEjml()).equals(listMC.get(i).getEjml())){
                continue;
            } else {
                minuteContent.add(listMC.get(i).getEjml());
                Log.i("aaa", "list.get(i).getEjml(): "+listMC.get(i).getEjml());

            }

        }
        xmmc = minuteContent.get(0);
        Logger.e("aaa", "erml==" + xmmc);

        List<GeteMaterialBean> listXM = geteMaterialDao.queryByYjmlandEjml(xmlb, xmmc);

        for (int i = 0; i < listXM.size(); i++){
            if (i > 0 && (listXM.get(i-1).getClmc()).equals(listXM.get(i).getClmc())){
                continue;
            } else {
                secondContent.add(listXM.get(i).getClmc());
                Log.i("aaa", "list.get(i).getBhmc(): "+listXM.get(i).getClmc());

            }

        }
        ximmc = secondContent.get(0);
        Logger.e("aaa", "clmc==" + xmmc);


    }

    String xmlb, xmmc;
    String ximmc;

    public void onClick(View v) {
        initContent();
        if (TextUtil.isListEmpty(geteMaterialBean)) {
            ToastUtil.showMessage("暂无材料数据");
            return;
        }
        int id = v.getId();
        Log.i("aaa ", "onClick: " + id);
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.checkcl_dialog, null);

//        hourWheel = (com.jp.wheelview.WheelView) view.findViewById(R.id.hourwheel);
//
//
//        hourWheel.setOnSelectListener(new com.jp.wheelview.WheelView.OnSelectListener() {
//            @Override
//            public void endSelect(int id, String text) {
//                if (text.equals("") || text == null)
//                    return;
//                Log.i("aaa", "text ====== " + text);
//                clmc = text;
//            }
//
//            @Override
//            public void selecting(int id, String text) {
//            }
//        });
//
////
//        hourWheel.setData(removeDuplicate(hourContent));
//        hourWheel.setDefault(0);
//        clmc = hourContent.get(0);
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
                List<GeteMaterialBean> list = geteMaterialDao.queryByYJML(text);
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
                List<GeteMaterialBean> list2 = geteMaterialDao.queryByYjmlandEjml(xmlb,xmmc);
                Log.i("aaa","list2 ====== "+list2.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getClmc()).equals(list.get(i).getClmc())){
                        continue;
                    } else {
                        secondContent.add(list.get(i).getClmc());
                        Log.i("aaa", "list.get(i).getBhmc(): "+list.get(i).getClmc());

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
                List<GeteMaterialBean> list = geteMaterialDao.queryByYjmlandEjml(xmlb,text);
                Log.i("aaa","list ====== "+list.size());
                for (int i = 0; i < list.size(); i++){
                    if (i > 0 && (list.get(i-1).getClmc()).equals(list.get(i).getClmc())){
                        continue;
                    } else {
                        secondContent.add(list.get(i).getClmc());
                        Log.i("aaa", "list.get(i).getBhmc(): "+list.get(i).getClmc());

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
//
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Log.i("aaa", "onClick: clmc ========= "+getClmc);
//                        checkClDialogReturn.returnBean(geteMaterialDao.queryByCLMC(clmc));
//
//                        Log.i("aaa", "onClick: clmc 111111========= "+clmc);
//                        dialog.cancel();
                        if (ximmc != null) {
                            checkClDialogReturn.returnBean(geteMaterialDao.queryByClon(ximmc));
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

    public interface CheckClDialogReturn {
        public void returnBean(List<GeteMaterialBean> bean);
    }
}