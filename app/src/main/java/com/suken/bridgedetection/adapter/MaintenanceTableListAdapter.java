package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class MaintenanceTableListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    List<MaintenanceTableBean> maintenanceTableBeanList  = new ArrayList<MaintenanceTableBean>();

    private UpLoadOnceTableData upLoadOnceTableData;
    public MaintenanceTableListAdapter(Context context,UpLoadOnceTableData upLoadOnceTableData) {
        mContext = context;
        this.upLoadOnceTableData = upLoadOnceTableData;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<MaintenanceTableBean> list){
        maintenanceTableBeanList = list;
    }
    @Override
    public int getCount() {
        return maintenanceTableBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        HolderView holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.maintenancetablelist_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        MaintenanceTableBean bean = maintenanceTableBeanList.get(position);
        holder.maintenancetablelist_item_tv1.setText(bean.getId()+"");
        holder.maintenancetablelist_item_tv2.setText(bean.getLxid()+"");
        holder.maintenancetablelist_item_tv3.setText(bean.getLxmc()+"");
        holder.maintenancetablelist_item_tv4.setText(bean.getJcsj()+"");
        holder.maintenancetablelist_item_tv5.setText(bean.getXcld()+"");
        holder.maintenancetablelist_item_tv6.setText(bean.getXcry()+"");
        holder.maintenancetablelist_item_tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLoadOnceTableData.loadData(position);
            }
        });

        return view;
    }



    class HolderView{
        private TextView maintenancetablelist_item_tv1,
                maintenancetablelist_item_tv2,
                maintenancetablelist_item_tv3,
                maintenancetablelist_item_tv4,
                maintenancetablelist_item_tv5,
                maintenancetablelist_item_tv6,
                maintenancetablelist_item_tv7;

        private LinearLayout maintenancetablelist_item_Layout;

        public HolderView(View v){
            maintenancetablelist_item_tv1 = (TextView) v.findViewById(R.id.maintenancetablelist_item_tv1);
            maintenancetablelist_item_tv2 = (TextView) v.findViewById(R.id.maintenancetablelist_item_tv2);
            maintenancetablelist_item_tv3 = (TextView) v.findViewById(R.id.maintenancetablelist_item_tv3);
            maintenancetablelist_item_tv4 = (TextView) v.findViewById(R.id.maintenancetablelist_item_tv4);
            maintenancetablelist_item_tv5 = (TextView) v.findViewById(R.id.maintenancetablelist_item_tv5);
            maintenancetablelist_item_tv6 = (TextView) v.findViewById(R.id.maintenancetablelist_item_tv6);
            maintenancetablelist_item_tv7 = (TextView) v.findViewById(R.id.maintenancetablelist_item_tv7);

            maintenancetablelist_item_Layout = (LinearLayout) v.findViewById(R.id.maintenancetablelist_item_Layout);
        }
    }
    public interface UpLoadOnceTableData{
        public void loadData(int position);
    }
}
