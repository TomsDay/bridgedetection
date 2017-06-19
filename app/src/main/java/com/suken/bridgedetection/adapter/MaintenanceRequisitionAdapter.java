package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.UpkeepdiseaseListBean;
import com.suken.bridgedetection.bean.WxdbhByUID;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangshuai on 2017/5/9.
 */

public class MaintenanceRequisitionAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    List<UpkeepdiseaseListBean> upkeepdiseaseListBeen = new ArrayList<UpkeepdiseaseListBean>();

    public MaintenanceRequisitionAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<UpkeepdiseaseListBean> list){
        upkeepdiseaseListBeen = list;
    }
    public List<UpkeepdiseaseListBean> getData(){
        return upkeepdiseaseListBeen;
    }
    @Override
    public int getCount() {
        return upkeepdiseaseListBeen.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.requisition_item, null);
            holder = new HolderView(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        final UpkeepdiseaseListBean bean = upkeepdiseaseListBeen.get(position);
        holder.maintenancerequisition_fx_tv.setText(bean.getFx());
        holder.maintenancerequisition_zh_tv.setText(bean.getYhzh());

        holder.maintenancerequisition_bhmc_tv.setText(bean.getBhmc());
        holder.maintenancerequisition_bhxq_tv.setText(bean.getBhwz());
        holder.maintenancerequisition_dw_tv.setText(bean.getDw());
        holder.maintenancerequisition_ygsl_tv.setText(bean.getYgsl());


        return convertView;
    }
    class HolderView{
        private TextView maintenancerequisition_fx_tv,
                maintenancerequisition_bhmc_tv,
                maintenancerequisition_bhxq_tv,
                maintenancerequisition_dw_tv,
                maintenancerequisition_ygsl_tv,
                maintenancerequisition_zh_tv;

        private LinearLayout quality_demand_item_Layout;
        private CheckBox quality_demand_item_cb1;

        public HolderView(View v){
            maintenancerequisition_fx_tv = (TextView) v.findViewById(R.id.maintenancerequisition_fx_tv);
            maintenancerequisition_zh_tv = (TextView) v.findViewById(R.id.maintenancerequisition_zh_tv);

            maintenancerequisition_bhmc_tv = (TextView) v.findViewById(R.id.maintenancerequisition_bhmc_tv);
            maintenancerequisition_bhxq_tv = (TextView) v.findViewById(R.id.maintenancerequisition_bhxq_tv);
            maintenancerequisition_dw_tv = (TextView) v.findViewById(R.id.maintenancerequisition_dw_tv);
            maintenancerequisition_ygsl_tv = (TextView) v.findViewById(R.id.maintenancerequisition_ygsl_tv);

            quality_demand_item_Layout = (LinearLayout) v.findViewById(R.id.quality_demand_item_Layout);

            quality_demand_item_cb1 = (CheckBox) v.findViewById(R.id.quality_demand_item_cb1);
        }
    }
}
