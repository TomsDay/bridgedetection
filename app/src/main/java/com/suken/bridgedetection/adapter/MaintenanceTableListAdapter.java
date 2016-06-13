package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suken.bridgedetection.R;

/**
 * Created by Administrator on 2016/6/12.
 */
public class MaintenanceTableListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    public MaintenanceTableListAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 50;
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
            view = inflater.inflate(R.layout.maintenancetablelist_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }

        return view;
    }
    class HolderView{
        private TextView maintenanceloglist_item_tv1,
                maintenanceloglist_item_tv2,
                maintenanceloglist_item_tv3,
                maintenanceloglist_item_tv4,
                maintenanceloglist_item_tv5,
                maintenanceloglist_item_tv6;

        public HolderView(View v){
            maintenanceloglist_item_tv1 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv1);
            maintenanceloglist_item_tv2 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv2);
            maintenanceloglist_item_tv3 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv3);
            maintenanceloglist_item_tv4 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv4);
            maintenanceloglist_item_tv5 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv5);
            maintenanceloglist_item_tv6 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv6);
        }
    }
}
