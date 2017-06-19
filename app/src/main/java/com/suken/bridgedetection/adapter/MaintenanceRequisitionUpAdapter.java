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
import com.suken.bridgedetection.bean.UploadUpkeepnoticeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangshuai on 2017/5/10.
 */

public class MaintenanceRequisitionUpAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    List<UploadUpkeepnoticeBean> uploadUpkeepnoticeBeens = new ArrayList<UploadUpkeepnoticeBean>();

    public MaintenanceRequisitionUpAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<UploadUpkeepnoticeBean> list){
        uploadUpkeepnoticeBeens = list;
    }
    public List<UploadUpkeepnoticeBean> getData(){
        return uploadUpkeepnoticeBeens;
    }
    @Override
    public int getCount() {
        return uploadUpkeepnoticeBeens.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.reequisition_up_item, null);
            holder = new HolderView(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        UploadUpkeepnoticeBean bean = uploadUpkeepnoticeBeens.get(position);
        holder.requisition_up_item_tx1.setText(bean.getId()+"");
        holder.requisition_up_item_tx2.setText(bean.getWxlx().equals("2")?"外包":"自养");
        holder.requisition_up_item_tx3.setText(bean.getWxbmmc());
        holder.requisition_up_item_tx4.setText(bean.getWxks());
        holder.requisition_up_item_tx5.setText(bean.getWxjs());
        holder.requisition_up_item_tx6.setText(bean.getQfry());
        holder.requisition_up_item_tx7.setText(bean.getQfrq());
        holder.requisition_up_item_tx8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadRequisitionData.upload(position);
            }
        });




        return convertView;
    }
    class HolderView{
        private TextView requisition_up_item_tx1,
                requisition_up_item_tx2,
                requisition_up_item_tx3,
                requisition_up_item_tx4,
                requisition_up_item_tx5,
                requisition_up_item_tx6,
                requisition_up_item_tx7,
                requisition_up_item_tx8;

        private LinearLayout requisition_up_item_Layout;

        public HolderView(View v){
            requisition_up_item_tx1 = (TextView) v.findViewById(R.id.requisition_up_item_tx1);
            requisition_up_item_tx2 = (TextView) v.findViewById(R.id.requisition_up_item_tx2);
            requisition_up_item_tx3 = (TextView) v.findViewById(R.id.requisition_up_item_tx3);
            requisition_up_item_tx4 = (TextView) v.findViewById(R.id.requisition_up_item_tx4);
            requisition_up_item_tx5 = (TextView) v.findViewById(R.id.requisition_up_item_tx5);
            requisition_up_item_tx6 = (TextView) v.findViewById(R.id.requisition_up_item_tx6);
            requisition_up_item_tx7 = (TextView) v.findViewById(R.id.requisition_up_item_tx7);
            requisition_up_item_tx8 = (TextView) v.findViewById(R.id.requisition_up_item_tx8);

            requisition_up_item_Layout = (LinearLayout) v.findViewById(R.id.requisition_up_item_Layout);

        }
    }
    UploadRequisitionData uploadRequisitionData;
    public void setUploadRequisitionData(UploadRequisitionData uploadRequisitionData){
        this.uploadRequisitionData = uploadRequisitionData;
    }
    public interface UploadRequisitionData{
        public void upload(int position);
    }
}
