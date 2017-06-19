package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.CooperationBean;
import com.suken.bridgedetection.bean.MaintenanceLogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangshuai on 2017/5/9.
 */

public class CooperationListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    List<CooperationBean> cooperationBeens = new ArrayList<CooperationBean>();

    public CooperationListAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<CooperationBean> list){
        cooperationBeens = list;
    }
    @Override
    public int getCount() {
        return cooperationBeens.size();
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
            convertView = inflater.inflate(R.layout.cooperation_item, null);
            holder = new HolderView(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        CooperationBean bean = cooperationBeens.get(position);
        holder.cooperation_item_tv1.setText(bean.getDwmc());
        holder.cooperation_item_tv2.setText(bean.getDz());


        return convertView;
    }
    class HolderView{
        private TextView cooperation_item_tv1,
                cooperation_item_tv2;

        private LinearLayout cooperation_item_Layout;

        public HolderView(View v){
            cooperation_item_tv1 = (TextView) v.findViewById(R.id.cooperation_item_tv1);
            cooperation_item_tv2 = (TextView) v.findViewById(R.id.cooperation_item_tv2);

            cooperation_item_Layout = (LinearLayout) v.findViewById(R.id.cooperation_item_Layout);
        }
    }
}
