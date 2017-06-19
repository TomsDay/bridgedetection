package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.activity.MaintenanceRequisitionActivity;
import com.suken.bridgedetection.activity.MaintenanceRequisitionListActivity;
import com.suken.bridgedetection.bean.WxdbhByUID;
import com.suken.bridgedetection.util.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.suken.bridgedetection.R.id.checkBox;
import static com.suken.bridgedetection.R.id.view;

/**
 * Created by liangshuai on 2017/3/11.
 */

public class MaintenanceRequisitionListAdapter extends BaseAdapter {
    private MaintenanceRequisitionListActivity mActivity;
    private LayoutInflater inflater;

    public List<WxdbhByUID> getCommitData() {
        return commitData;
    }

    private List<WxdbhByUID> commitData = new ArrayList<WxdbhByUID>();
    public MaintenanceRequisitionListAdapter(MaintenanceRequisitionListActivity content){
        mActivity = content;
        inflater = mActivity.getLayoutInflater();
    }

    List<WxdbhByUID> listBean = new ArrayList<WxdbhByUID>();

    public List<WxdbhByUID> getListBean() {
        return listBean;
    }

    public void setListBean(List<WxdbhByUID> listBean) {
        this.listBean = listBean;
    }

    @Override
    public int getCount() {
        return listBean.size();
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
            convertView = inflater.inflate(R.layout.maintenancerequisitionlist_item, null);
            holder = new HolderView(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        final WxdbhByUID bean = listBean.get(position);

        holder.maintenancerequisitionlist_item_tv1.setText(bean.getBhid());
        holder.maintenancerequisitionlist_item_tv2.setText(bean.getYhInspNo());
        holder.maintenancerequisitionlist_item_tv3.setText(bean.getBhmc());
        holder.maintenancerequisitionlist_item_tv4.setText(bean.getXcry());
        holder.maintenancerequisitionlist_item_tv5.setText(bean.getJcrq());
        holder.maintenancerequisitionlist_item_tv6.setText(bean.getLxmc());
        holder.maintenancerequisitionlist_item_tv7.setText(bean.getYhzh());
        holder.maintenancerequisitionlist_item_tv8.setText(bean.getDw());
        holder.maintenancerequisitionlist_item_tv9.setText(bean.getYgsl());

        holder.maintenancerequisitionlist_item_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean isHave = commitData.contains(bean);
                bean.setChecked(isChecked);
                if(isChecked){
                    if(!isHave){
                        commitData.add(bean);
                    }
                }else{
                    if(isHave){
                        commitData.remove(bean);
                    }
                }

            }
        });
        setClickItem(bean,holder);

        return convertView;
    }
    public void setClickItem(final WxdbhByUID bean,final HolderView holder){
        holder.maintenancerequisitionlist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked =bean.isChecked();
                if(ischecked){
                    holder.maintenancerequisitionlist_item_cb1.setChecked(false);
                }else{
                    holder.maintenancerequisitionlist_item_cb1.setChecked(true);
                }
                bean.setChecked(!ischecked);
            }
        });
    }

    class HolderView{

        CheckBox maintenancerequisitionlist_item_cb1;
        TextView maintenancerequisitionlist_item_tv1,
                maintenancerequisitionlist_item_tv2,
                maintenancerequisitionlist_item_tv3,
                maintenancerequisitionlist_item_tv4,
                maintenancerequisitionlist_item_tv5,
                maintenancerequisitionlist_item_tv6,
                maintenancerequisitionlist_item_tv7,
                maintenancerequisitionlist_item_tv8,
                maintenancerequisitionlist_item_tv9;

        LinearLayout maintenancerequisitionlist_layout;

        public HolderView(View v){
            maintenancerequisitionlist_item_tv1 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv1);
            maintenancerequisitionlist_item_tv2 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv2);
            maintenancerequisitionlist_item_tv3 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv3);
            maintenancerequisitionlist_item_tv4 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv4);
            maintenancerequisitionlist_item_tv5 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv5);
            maintenancerequisitionlist_item_tv6 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv6);
            maintenancerequisitionlist_item_tv7 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv7);
            maintenancerequisitionlist_item_tv8 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv8);
            maintenancerequisitionlist_item_tv9 = (TextView) v.findViewById(R.id.maintenancerequisitionlist_item_tv9);

            maintenancerequisitionlist_layout = (LinearLayout) v.findViewById(R.id.maintenancerequisitionlist_layout);

            maintenancerequisitionlist_item_cb1 = (CheckBox) v.findViewById(R.id.maintenancerequisitionlist_item_cb1);

        }


    }
}
