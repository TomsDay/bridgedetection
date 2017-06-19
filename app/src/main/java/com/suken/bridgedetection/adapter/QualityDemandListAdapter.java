package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.CooperationBean;
import com.suken.bridgedetection.bean.QualityDemandBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangshuai on 2017/5/9.
 */

public class QualityDemandListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    List<QualityDemandBean> qualityDemandBeen = new ArrayList<QualityDemandBean>();

    public QualityDemandListAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<QualityDemandBean> list){
        qualityDemandBeen = list;
    }
    public List<QualityDemandBean> getData(){
        return qualityDemandBeen;
    }
    @Override
    public int getCount() {
        return qualityDemandBeen.size();
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
            convertView = inflater.inflate(R.layout.quality_demand_item, null);
            holder = new HolderView(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        final QualityDemandBean bean = qualityDemandBeen.get(position);
        holder.quality_demand_item_tv1.setText(bean.getYqnr());
        holder.quality_demand_item_tv2.setText(bean.getBhzl());
        holder.quality_demand_item_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.setChecked(isChecked);
            }
        });
        holder.quality_demand_item_cb1.setChecked(bean.isChecked());


        setonItemClick(holder,bean);
        return convertView;
    }
    public void setonItemClick(final HolderView holder,final QualityDemandBean bean){
        holder.quality_demand_item_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischeck = bean.isChecked();
                bean.setChecked(!ischeck);
                holder.quality_demand_item_cb1.setChecked(!ischeck);
            }
        });
    }
    class HolderView{
        private TextView quality_demand_item_tv1,
                quality_demand_item_tv2;

        private LinearLayout quality_demand_item_Layout;
        private CheckBox quality_demand_item_cb1;

        public HolderView(View v){
            quality_demand_item_tv1 = (TextView) v.findViewById(R.id.quality_demand_item_tv1);
            quality_demand_item_tv2 = (TextView) v.findViewById(R.id.quality_demand_item_tv2);

            quality_demand_item_Layout = (LinearLayout) v.findViewById(R.id.quality_demand_item_Layout);

            quality_demand_item_cb1 = (CheckBox) v.findViewById(R.id.quality_demand_item_cb1);
        }
    }
}
