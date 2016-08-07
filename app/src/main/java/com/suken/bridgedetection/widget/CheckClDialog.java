package com.suken.bridgedetection.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jp.wheelview.*;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.CatalogueByUIDBean;
import com.suken.bridgedetection.bean.CatalogueByUIDDao;
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.bean.GeteMaterialDao;
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
    private com.jp.wheelview.WheelView hourWheel;
    public static ArrayList<String> hourContent = new ArrayList<String>();
    private Context mContext;
    //    private EditText mEditText;
    GeteMaterialDao geteMaterialDao;
    private CheckClDialogReturn checkClDialogReturn;

    List<GeteMaterialBean> geteMaterialBean;

    public CheckClDialog(Context context, CheckClDialogReturn checkClDialogReturn) {
        this.mContext = context;
        this.checkClDialogReturn = checkClDialogReturn;
        geteMaterialDao = new GeteMaterialDao();
        initContent();
    }

    public void initContent() {

        geteMaterialBean = geteMaterialDao.queryAll();
        if (TextUtil.isListEmpty(geteMaterialBean)) {
            ToastUtil.showMessage("暂无细目库数据");
            return;
        }
        Log.i("aaa", "initContent: " + geteMaterialBean.size());
        for (int i = 0; i < geteMaterialBean.size(); i++) {
            hourContent.add(geteMaterialBean.get(i).getClmc());
            Log.i("aaa", "list.get(i).getXmlb(): " + geteMaterialBean.get(i).getClmc());
        }


    }

    String clmc;

    public void onClick(View v) {
        if (TextUtil.isListEmpty(geteMaterialBean)) {
            ToastUtil.showMessage("暂无材料数据");
            return;
        }
        int id = v.getId();
        Log.i("aaa ", "onClick: " + id);
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.checkcl_dialog, null);

        hourWheel = (com.jp.wheelview.WheelView) view.findViewById(R.id.hourwheel);


        hourWheel.setOnSelectListener(new com.jp.wheelview.WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                Log.i("aaa", "text ====== " + text);
                clmc = text;
            }

            @Override
            public void selecting(int id, String text) {
            }
        });

//
        hourWheel.setData(removeDuplicate(hourContent));
        hourWheel.setDefault(0);
        clmc = hourContent.get(0);
//
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("aaa", "onClick: clmc ========= "+clmc);
                        checkClDialogReturn.returnBean(geteMaterialDao.queryByCLMC(clmc));

                        Log.i("aaa", "onClick: clmc 111111========= "+clmc);
                        dialog.cancel();
                    }
                })

                .show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private ArrayList<String> removeDuplicate(ArrayList<String> arrayList) {
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