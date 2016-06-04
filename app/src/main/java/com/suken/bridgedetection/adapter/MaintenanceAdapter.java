package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suken.bridgedetection.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/4.
 */
public class MaintenanceAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> list = new ArrayList<String>();
    private LayoutInflater inflater;
    public MaintenanceAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        getData();
    }
    public void getData(){
        String str = "高速公路";
        list.add(str + "日常巡查");
        list.add(str + "养护下单");
        list.add(str + "维修保养通知单");
        list.add(str + "维修保养日志");
        list.add(str + "施工安全检查表");
        list.add(str + "验收申请");
        list.add(str + "维修保养验收");
        list.add(str + "综合查询页面");
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        HolderView holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.maintenance_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        holder.maintenance_item_name.setText(list.get(position));
        return view;
    }
    class HolderView{
        private TextView maintenance_item_name,
                maintenance_item_new,
                maintenance_item_submit;
        public HolderView(View v){
            maintenance_item_name = (TextView) v.findViewById(R.id.maintenance_item_name);
            maintenance_item_new = (TextView) v.findViewById(R.id.maintenance_item_new);
            maintenance_item_submit = (TextView) v.findViewById(R.id.maintenance_item_submit);
        }
    }
}
