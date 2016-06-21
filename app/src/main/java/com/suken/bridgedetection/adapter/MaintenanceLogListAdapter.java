package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;

/**
 * Created by Administrator on 2016/6/21.
 */
public class MaintenanceLogListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    public MaintenanceLogListAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 10;
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
            view = inflater.inflate(R.layout.maintenanceloglist_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        holder.maintenanceloglist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(mContext, MaintenanceLogActivity.class);
                mContext.startActivity(in);

            }
        });
        return view;
    }
    class HolderView{
        private TextView maintenanceloglist_item_tv1,
                maintenanceloglist_item_tv2,
                maintenanceloglist_item_tv3,
                maintenanceloglist_item_tv4;
        private LinearLayout maintenanceloglist_layout;


        public HolderView(View v){
            maintenanceloglist_item_tv1 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv1);
            maintenanceloglist_item_tv2 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv2);
            maintenanceloglist_item_tv3 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv3);
            maintenanceloglist_item_tv4 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv4);

            maintenanceloglist_layout = (LinearLayout) v.findViewById(R.id.maintenanceloglist_layout);


        }
    }
}
