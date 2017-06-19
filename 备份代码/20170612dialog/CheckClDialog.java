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

import com.jp.wheelview.WheelView;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.bean.GeteMaterialDao;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wudi on 16/8/7.
 */
public class CheckClDialog implements View.OnClickListener {

    private int minYear = 1970;  //最小年份
    private int fontSize = 13;     //字体大小
    private WheelView hourWheel, minuteWheel, secondWheel, dw_wheel;
    private EditText cc_seach_ev;
    public static ArrayList<String> hourContent = new ArrayList<String>();
    public static ArrayList<String> minuteContent = new ArrayList<String>();
    public static ArrayList<String> secondContent = new ArrayList<String>();
    public static ArrayList<String> dwmcContent = new ArrayList<String>();
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
        dwmcContent.clear();

        geteMaterialBean = geteMaterialDao.queryAll();
        if (TextUtil.isListEmpty(geteMaterialBean)) {
            ToastUtil.showMessage("暂无材料数据");
            return;
        }

//        Log.i("aaa", "initContent: "+geteMaterialBean.size());
//        for (int i = 0; i < geteMaterialBean.size(); i++){
////            if(TextUtil.isEmptyString(geteMaterialBean.get(i).getYjml())){
////                ToastUtil.showMessage("细目数据一级分类有空！");
////                return;
////            }
//            if (i > 0 && (geteMaterialBean.get(i-1).getYjml()).equals(geteMaterialBean.get(i).getYjml())){
//                continue;
//            } else {
//
//                hourContent.add(geteMaterialBean.get(i).getYjml());
//                Log.i("aaa", "list.get(i).getYjml(): "+geteMaterialBean.get(i).getYjml());
//
//            }
//
//        }
//        xmlb = hourContent.get(0);
        getDataMethod(YJFL_INDEX, true);
        Logger.e("aaa", "yjml==" + xmlb);
//        List<GeteMaterialBean> listMC = geteMaterialDao.queryByYJML(xmlb);
//        for (int i = 0; i < listMC.size(); i++){
////            if(TextUtil.isEmptyString(geteMaterialBean.get(i).getEjml())){
////                ToastUtil.showMessage("细目数据二级分类有空！");
////                return;
////            }
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
        Logger.e("aaa", "erml==" + xmmc);

//        List<GeteMaterialBean> listXM = geteMaterialDao.queryByYjmlandEjml(xmlb, xmmc);
//
//        for (int i = 0; i < listXM.size(); i++){
//            if (i > 0 && (listXM.get(i-1).getClmc()).equals(listXM.get(i).getClmc())){
//                continue;
//            } else {
//                secondContent.add(listXM.get(i).getClmc());
//                Log.i("aaa", "list.get(i).getBhmc(): "+listXM.get(i).getClmc());
//
//            }
//
//        }
//        ximmc = secondContent.get(0);
        getDataMethod(XIMUMC_INDEX, true);
        Logger.e("aaa", "clmc==" + ximmc);

//        List<GeteMaterialBean> listdw = geteMaterialDao.queryByYjmlandEjmlXimmc(xmlb, xmmc,ximmc);
//
//        for (int i = 0; i < listdw.size(); i++){
//            if (i > 0 && (listdw.get(i-1).getDw()).equals(listdw.get(i).getDw())){
//                continue;
//            } else {
//                dwmcContent.add(listXM.get(i).getDw());
//                Log.i("aaa", "list.get(i).getBhmc(): "+listXM.get(i).getDw());
//
//            }
//
//        }
//       Logger.e("aaa","dwmcContent的长度："+dwmcContent.size());
//        if(!TextUtil.isListEmpty(dwmcContent)){
//            dwmc = dwmcContent.get(0);
//            Logger.e("aaa", "dwmc==" + dwmc);
//        }
        getDataMethod(DW_INDEX, true);
        Logger.e("aaa", "dwmc==" + dwmc);


    }

    String xmlb, xmmc;
    String ximmc, dwmc;
    //数据的本地id
    //不可能用id定位其他列的数据
    //id只能定位最终按确定选中的数据
    int dataID = 0;

    String contentET;

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
        dw_wheel = (WheelView) view.findViewById(R.id.dw_wheel);
        cc_seach_ev = (EditText) view.findViewById(R.id.cc_seach_ev);

        cc_seach_ev.addTextChangedListener(new TextWatcher() {
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
                if (TextUtil.isEmptyString(text)) {
                    return;
                }
                dataID = id;
                xmlb = text;
                Log.i("aaa", "text ====== " + text);
//                minuteContent.clear();
//                List<GeteMaterialBean> list = geteMaterialDao.queryByYJML(text);
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
//                minuteWheel.setData(minuteContent);
//                minuteWheel.setDefault(0);
//                xmmc = minuteContent.get(0);
                getDataMethod(EJFL_INDEX, false);


//                secondContent.clear();
//                List<GeteMaterialBean> list2 = geteMaterialDao.queryByYjmlandEjml(xmlb,xmmc);
//                Log.i("aaa","list2 ====== "+list2.size());
//                for (int i = 0; i < list2.size(); i++){
//                    if (i > 0 && (list2.get(i-1).getClmc()).equals(list2.get(i).getClmc())){
//                        continue;
//                    } else {
//                        secondContent.add(list2.get(i).getClmc());
//                        Log.i("aaa", "list.get(i).getBhmc(): "+list2.get(i).getClmc());
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

                //2017.6.4新增单位选项
//                dwmcContent.clear();
//                List<GeteMaterialBean> listdw = geteMaterialDao.queryByYjmlandEjmlXimmc(xmlb, xmmc,ximmc);
//                Log.i("aaa","list2 ====== "+listdw.size());
//                for (int i = 0; i < listdw.size(); i++){
//                    if (i > 0 && (listdw.get(i-1).getDw()).equals(listdw.get(i).getDw())){
//                        continue;
//                    } else {
//                        dwmcContent.add(listdw.get(i).getDw());
//                        Log.i("aaa", "list.get(i).getDw(): "+list.get(i).getDw());
//
//                    }
//
//                }
//                dwmcContent = removeDuplicate(dwmcContent);
//
//                dw_wheel.setData(dwmcContent);
//                dw_wheel.setDefault(0);
//                dwmc = dwmcContent.get(0);
                getDataMethod(DW_INDEX, false);

            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        minuteWheel.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text)) {
                    return;
                }
                dataID = id;
                xmmc = text;
//                secondContent.clear();
//                List<GeteMaterialBean> list = geteMaterialDao.queryByYjmlandEjml(xmlb,text);
//                Log.i("aaa","list ====== "+list.size());
//                for (int i = 0; i < list.size(); i++){
//                    if (i > 0 && (list.get(i-1).getClmc()).equals(list.get(i).getClmc())){
//                        continue;
//                    } else {
//                        secondContent.add(list.get(i).getClmc());
//                        Log.i("aaa", "list.get(i).getBhmc(): "+list.get(i).getClmc());
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

                //2017.6.4新增单位选项
//                dwmcContent.clear();
//                List<GeteMaterialBean> listdw = geteMaterialDao.queryByYjmlandEjmlXimmc(xmlb, xmmc,ximmc);
//                Log.i("aaa","list2 ====== "+listdw.size());
//                for (int i = 0; i < listdw.size(); i++){
//                    if (i > 0 && (listdw.get(i-1).getDw()).equals(listdw.get(i).getDw())){
//                        continue;
//                    } else {
//                        dwmcContent.add(listdw.get(i).getDw());
//                        Log.i("aaa", "list.get(i).getDw(): "+list.get(i).getDw());
//
//                    }
//
//                }
//                dwmcContent = removeDuplicate(dwmcContent);
//
//                dw_wheel.setData(dwmcContent);
//                dw_wheel.setDefault(0);
//                dwmc = dwmcContent.get(0);
                getDataMethod(DW_INDEX, false);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        secondWheel.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text)) {
                    return;
                }
                dataID = id;
                ximmc = text;
//                secondWheel.setData(hourContent);
                //2017.6.4新增单位选项
//                dwmcContent.clear();
//                List<GeteMaterialBean> listdw = geteMaterialDao.queryByYjmlandEjmlXimmc(xmlb, xmmc,ximmc);
//                Log.i("aaa","list2 ====== "+listdw.size());
//                for (int i = 0; i < listdw.size(); i++){
//                    if (i > 0 && (listdw.get(i-1).getDw()).equals(listdw.get(i).getDw())){
//                        continue;
//                    } else {
//                        dwmcContent.add(listdw.get(i).getDw());
//                        Log.i("aaa", "list.get(i).getDw(): "+listdw.get(i).getDw());
//
//                    }
//
//                }
//                dwmcContent = removeDuplicate(dwmcContent);
//
//                dw_wheel.setData(dwmcContent);
//                dw_wheel.setDefault(0);
//                dwmc = dwmcContent.get(0);
                getDataMethod(DW_INDEX, false);
            }

            @Override
            public void selecting(int id, String text) {
            }
        });
        dw_wheel.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (TextUtil.isEmptyString(text)) {
                    return;
                }
                dataID = id;
                dwmc = text;
//
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

        List<GeteMaterialBean> list = new ArrayList<GeteMaterialBean>();
        switch (type) {
            case YJFL_INDEX:
                if (!TextUtil.isListEmpty(hourContent)) {
                    hourContent.clear();
                }
                list = geteMaterialDao.queryAll(contentET);
                break;
            case EJFL_INDEX:
                if (!TextUtil.isListEmpty(minuteContent)) {
                    minuteContent.clear();
                }
                list = geteMaterialDao.queryByYJML(xmlb,contentET);
                break;
            case XIMUMC_INDEX:
                if (!TextUtil.isListEmpty(secondContent)) {
                    secondContent.clear();
                }
                list = geteMaterialDao.queryByYjmlandEjml(xmlb, xmmc,contentET);
                break;
            case DW_INDEX:
                if (!TextUtil.isListEmpty(dwmcContent)) {
                    dwmcContent.clear();
                }
                list = geteMaterialDao.queryByYjmlandEjmlXimmc(xmlb, xmmc, ximmc,contentET);
                break;
            default:
                break;
        }
        //不管动列的数据，默认的id都会是第一行，除了动最后单位一行的时候，是数据本身的id
        if (!TextUtil.isListEmpty(list)) {
            dataID = list.get(0).getIds();
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
                    if (i > 0 && (list.get(i - 1).getClmc()).equals(list.get(i).getClmc())) {
                        continue;
                    } else {
                        secondContent.add(list.get(i).getClmc());
                        Log.i("aaa", "list.get(i).getClmc(): " + list.get(i).getClmc());

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

    public interface CheckClDialogReturn {
        public void returnBean(List<GeteMaterialBean> bean);
    }
}